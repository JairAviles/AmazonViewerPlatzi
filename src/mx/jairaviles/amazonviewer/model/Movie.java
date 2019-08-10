package mx.jairaviles.amazonviewer.model;

import mx.jairaviles.amazonviewer.dao.MovieDAO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Hereda de {@link Film}
 * Implementa de {@link IVisualizable}
 * */
public class Movie extends Film implements IVisualizable, MovieDAO {

    private int id;
    private int timeViewed;

    public Movie() {}
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

    public void setId(int id) {
        this.id = id;
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
        System.out.println("Watched: " + toString());
        System.out.println("For: " + getTimeViewed() + " miliseconds");
    }

    public static ArrayList<Movie> makeMovieList() {
        Movie movie = new Movie();
        return movie.read();
    }

}
