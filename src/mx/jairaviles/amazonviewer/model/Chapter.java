package mx.jairaviles.amazonviewer.model;

import java.util.ArrayList;

/**
 * Hereda de {@link Movie}
 * @see Film
 */
public class Chapter extends Movie {

    private int id;
    private int seasonNumber;
    private Serie serie;

    public Chapter(String title, String genre, String creator, int duration, short year, int seasonNumber, Serie serie) {
        super(title, genre, creator, duration, year, "");
        this.seasonNumber = seasonNumber;
        this.setSerie(serie);
    }

    public int getId() {
        return id;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return  "\n :: CHAPTER ::" +
                "\n Title: " + getTitle() +
                "\n Year: " + getYear() +
                "\n Creator: " + getCreator() +
                "\n Duration: " + getDuration();
    }

    public static ArrayList<Chapter> makeChaptersList(Serie serie) {
        ArrayList<Chapter> chapters = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            chapters.add(new Chapter("Chapter" + i, "Genre" + i, "Creator" + i, 120 + i, (short)(2017 + i), i, serie));
        }

        return chapters;
    }

    @Override
    public void view() {
        super.view();
        ArrayList<Chapter> chapters = getSerie().getChapters();
        int chapterViewedCounted = 0;

        for (Chapter chapter : chapters) {
            if (chapter.getIsViewed()) {
                chapterViewedCounted++;
            }
        }

        if (chapterViewedCounted == chapters.size()) {
            getSerie().view();
        }
    }
}
