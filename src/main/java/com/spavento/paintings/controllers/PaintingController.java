package com.spavento.paintings.controllers;

import com.spavento.paintings.exceptions.MyFileNotFoundException;
import com.spavento.paintings.exceptions.ResourceNotFoundException;
import com.spavento.paintings.models.DBImage;
import com.spavento.paintings.models.Painting;
import com.spavento.paintings.repository.DBImageRepository;
import com.spavento.paintings.repository.PaintingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/painting")
public class PaintingController {
    @Autowired
    private PaintingRepository paintingRepository;
    @Autowired
    private DBImageRepository dbImageRepository;

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Painting> getAllPaintings() {
        return paintingRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Painting> getPaintingById(@PathVariable(value = "id") Long paintingId)
            throws ResourceNotFoundException {
        Painting painting = paintingRepository.findById(paintingId)
                .orElseThrow(() -> new ResourceNotFoundException("Painting not found for this id :: " + paintingId));
        return ResponseEntity.ok().body(painting);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Painting> updatePainting(
            @PathVariable(value = "id") Long paintingId,
            @Valid @RequestBody Painting paintingDetails
    ) throws ResourceNotFoundException {
        Painting painting = paintingRepository.findById(paintingId)
                .orElseThrow(() -> new ResourceNotFoundException("Painting not found for this id :: " + paintingId));

        painting.setTitle(paintingDetails.getTitle());
        painting.setDescription(paintingDetails.getDescription());
        painting.setPrice(paintingDetails.getPrice());
        painting.setStatus(paintingDetails.getStatus());
        painting.setCategory(paintingDetails.getCategory());
        final Painting updatedPainting = paintingRepository.save(painting);
        return ResponseEntity.ok(updatedPainting);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deletePainting(@PathVariable(value = "id") Long paintingId)
            throws ResourceNotFoundException {
        Painting painting = paintingRepository.findById(paintingId)
                .orElseThrow(() -> new ResourceNotFoundException("Painting not found for this id :: " + paintingId));

        paintingRepository.delete(painting);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    /**
     * Save painting
     * @return Painting
     */
    @PostMapping(value = "/savePainting")
    @PreAuthorize("hasRole('ADMIN')")
    public Painting savePainting(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") Float price,
            @RequestParam("status") String status,
            @RequestParam("category") String category
    ) {
        Painting painting = new Painting(title, description, price, status, category);
        return paintingRepository.save(painting);
    }

    /**
     * Save images
     */
    @PostMapping(value = "/saveImages")
    @PreAuthorize("hasRole('ADMIN')")
    public void saveImages(
            @RequestParam("paintingId") Long paintingId,
            @RequestParam("images") MultipartFile[] images
    ) throws IOException {
        for (MultipartFile image: images) {
            DBImage db_image = new DBImage(
                    paintingId,
                    image.getOriginalFilename(),
                    image.getContentType(),
                    image.getBytes()
            );
            dbImageRepository.save(db_image);
        }
    }

    @GetMapping(value = "/getImages/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<DBImage> getImages(@PathVariable(value = "id") Long paintingId) {
        return dbImageRepository.findAllByPaintingId(paintingId);
    }

    @DeleteMapping("/deleteImage/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteImage(@PathVariable(value = "id") Long imageId) {
        DBImage dbImage = dbImageRepository.findById(imageId)
                .orElseThrow(() -> new MyFileNotFoundException("Image not found for this id :: " + imageId));

        dbImageRepository.delete(dbImage);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
