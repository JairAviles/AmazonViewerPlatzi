package mx.jairaviles.amazonviewer.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Hereda de {@link Publication}
 * */
public class Magazine extends Publication {

    private int id;

    public Magazine(int id, String title, Date editionDate, String editorial, String[] authors) {
        super(title, editionDate, editorial, authors);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String detailMagazine = getId() + ". " + getTitle() + " edition date " + getEditionDate() +
                " editorial " + getEditorial();
        return  detailMagazine;
    }

    public String view() {
        String detailMagazine = "\n :: MAGAZINE ::" +
                "\n Title: " +  getTitle() +
                "\n Editorial: " + getEditorial() +
                "\n Edition Date: " + getEditionDate() + "\n Authors: ";
        for (int i = 0; i < getAuthors().length; i++) {
            detailMagazine += "\t" + getAuthors()[i] + " ";
        }
        return detailMagazine;
    }

    public static ArrayList<Magazine> makeMagazineList() {
        ArrayList<Magazine> magazines = new ArrayList<>();
        String[] authors = new String[3];

        for (int i = 0; i < authors.length; i++) {
            authors[i] = "author" + i;
        }
        for (int i = 1; i <= 5; i++) {
            magazines.add(new Magazine(i,"Magazine " + i, new Date(), "Editorial " + i, authors));
        }
        return magazines;
    }

}
