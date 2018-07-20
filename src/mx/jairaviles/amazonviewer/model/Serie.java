package mx.jairaviles.amazonviewer.model;

import java.util.ArrayList;

public class Serie extends Film {

    private int id;
    private int seasonQuantity;
    private ArrayList<Chapter> chapters;

    public Serie(String title, String genre, String creator, int duration, int seasonQuantity) {
        super(title, genre, creator, duration);
        this.seasonQuantity = seasonQuantity;
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
            Serie serie = new Serie("Serie "+i, "genero "+i, "creador "+i, 1200, 5);
            serie.setChapters(Chapter.makeChaptersList(serie));
            series.add(serie);
        }

        return series;
    }

    @Override
    public void view() {

    }
}
