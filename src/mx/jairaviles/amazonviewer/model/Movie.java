package mx.jairaviles.amazonviewer.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Movie extends Film implements IVisualized {

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

    @Override
    public Date startToSee(Date startDate) {
        return startDate;
    }

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

    public static ArrayList<Movie> makeMovieList() {
        ArrayList<Movie> movies = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            movies.add(new Movie("Movie" + i, "Genre" + i, "Creator" + i, 120 + i, (short)(2017 + i)));
        }
        return movies;
    }

}
