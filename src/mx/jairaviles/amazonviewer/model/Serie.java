package mx.jairaviles.amazonviewer.model;

import java.util.ArrayList;

public class Serie extends Film {

    private int id;
    private int seasonQuantity;
    private ArrayList<Chapter> chapters;

    public Serie(String title, String genre, String creator, int duration, int seasonQuantity) {
        super(title, genre, creator, duration);
        this.seasonQuantity = seasonQuantity;
        this.chapters = Chapter.makeChapterList();
    }

    public int getId() {
        return id;
    }

    public int getSeasonQuantity() {
        return seasonQuantity;
    }

    public void setSeasonQuantity(int seasonQuantity) {
        this.seasonQuantity = seasonQuantity;
    }

    public ArrayList<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<Chapter> chapters) {
        this.chapters = chapters;
    }

    @Override
    public String toString() {
        return "Title: " + getTitle() +
                "\t Genre:" + getGenre() +
                "\t Year:" + getYear() +
                "\t Creator:" + getCreator() +
                "\t Duration:" + getDuration() +
                "\t Viewed:" + isViewed() ;
    }

    public static ArrayList<Serie> makeSeriesList() {
        ArrayList<Serie> series = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            series.add(new Serie("Serie" + i, "Genre" + i, "Creator" + i, 120 + i, i +1));
        }

        return series;
    }

}
