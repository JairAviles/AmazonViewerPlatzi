package mx.jairaviles.amazonviewer.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Hereda de {@link Film}
 * Implementa de {@link IVisualizable}
 * */
public class Movie extends Film implements IVisualizable {

    private int id;
    private int timeViewed;

    public Movie(String title, String genre, String creator, int duration, short year) {
        super(title, genre, creator, duration);
        setYear(year);
    }

    public void showData() {
       System.out.println("Title: " + getTitle());
       System.out.println("Genre: " + getGenre());
       System.out.println("Year: "  + getYear());

    }

    public int getId() {
        return id;
    }

    public int getTimeViewed() {
        return timeViewed;
    }

    public void setTimeViewed(int timeViewed) {
        this.timeViewed = timeViewed;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Date startToSee(Date startDate) {
        return startDate;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void stopToSee(Date startDate, Date endDate) {
        if (endDate.getTime() > startDate.getTime()) {
            setTimeViewed((int)(endDate.getTime() - startDate.getTime()));
        } else {
            setTimeViewed(0);
        }
    }

    @Override
    public String toString() {
        return  "Title: " + getTitle() +
                "\t Viewed:" + isViewed();
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void view() {
        setViewed(true);
        Date dateI = startToSee(new Date());

        for (int i =0; i < 100000; i++) {
            System.out.println("......");
        }

        stopToSee(dateI, new Date());
        System.out.println();
        System.out.println("Viste: " + toString());
        System.out.println("Por: " + getTimeViewed() + " milisegundos");
    }

    public static ArrayList<Movie> makeMovieList() {
        ArrayList<Movie> movies = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            movies.add(new Movie("Movie" + i, "Genre" + i, "Creator" + i, 120 + i, (short)(2017 + i)));
        }
        return movies;
    }

}
