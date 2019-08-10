package mx.jairaviles.amazonviewer.model;

import mx.jairaviles.amazonviewer.dao.MovieDAO;
import mx.jairaviles.amazonviewer.util.AmazonUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Hereda de {@link Film}
 * Implementa de {@link IVisualizable}
 * */
public class Movie extends Film implements IVisualizable, MovieDAO {

    private int id;
    private int timeViewed;
    private  String viewed_date;

    public Movie() {}
    public Movie(String title, String genre, String creator, int duration, short year, String viewed_date) {
        super(title, genre, creator, duration);
        setYear(year);
        setViewed_date(viewed_date);
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

    public String getViewed_date() {
        return viewed_date;
    }

    public void setViewed_date(String viewed_date) {
        this.viewed_date = viewed_date;
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
        try {
            return  "Title: " + getTitle() +
                    "\t Viewed:" + isViewed() +
                    "\t Date Viewed: " + AmazonUtil.formatDate(getViewed_date());
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
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
