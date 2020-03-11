package com.spavento.paintings.payload.response;

import com.spavento.paintings.models.DBImage;
import com.spavento.paintings.models.Painting;

public class PaintingWithImageResponse {
    private Painting painting;
    private DBImage image;

    public PaintingWithImageResponse(Painting painting, DBImage image) {
        this.painting = painting;
        this.image = image;
    }

    public Painting getPainting() {
        return painting;
    }
    public void setPainting(Painting painting) {
        this.painting = painting;
    }

    public DBImage getImage() {
        return image;
    }
    public void setImage(DBImage image) {
        this.image = image;
    }
}
