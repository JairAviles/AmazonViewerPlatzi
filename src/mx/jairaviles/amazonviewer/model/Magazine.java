package mx.jairaviles.amazonviewer.model;

import java.util.Date;

/**
 * Hereda de {@link Publication}
 * */
public class Magazine extends Publication {

    private int id;

    public Magazine(String title, Date editionDate, String editorial, String[] authors) {
        super(title, editionDate, editorial, authors);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
