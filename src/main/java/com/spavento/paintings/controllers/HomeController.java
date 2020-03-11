package com.spavento.paintings.controllers;

import com.spavento.paintings.models.DBImage;
import com.spavento.paintings.models.Painting;
import com.spavento.paintings.payload.response.PaintingWithImageResponse;
import com.spavento.paintings.repository.DBImageRepository;
import com.spavento.paintings.repository.PaintingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/home")
public class HomeController {
    @Autowired
    private PaintingRepository paintingRepository;
    @Autowired
    private DBImageRepository dbImageRepository;

    @GetMapping("/paintings")
    public List<PaintingWithImageResponse> getPaintingList() {
        List<PaintingWithImageResponse> response = new ArrayList<>();
        List<Painting> paintings = paintingRepository.findAll();
        for (Painting painting : paintings) {
            DBImage image = dbImageRepository.findFirstByPaintingId(painting.getId());
            response.add(new PaintingWithImageResponse(painting, image));
        }
        return response;
    }
}
