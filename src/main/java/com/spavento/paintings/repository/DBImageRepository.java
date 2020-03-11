package com.spavento.paintings.repository;

import com.spavento.paintings.models.DBImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DBImageRepository extends JpaRepository<DBImage, Long> {
    List<DBImage> findAllByPaintingId(Long paintingId);
    DBImage findFirstByPaintingId(Long paintingId);
}
