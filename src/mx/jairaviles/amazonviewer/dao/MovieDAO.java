package mx.jairaviles.amazonviewer.dao;

import mx.jairaviles.amazonviewer.db.IDBConnection;
import mx.jairaviles.amazonviewer.model.Movie;
import mx.jairaviles.amazonviewer.util.AmazonUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static  mx.jairaviles.amazonviewer.db.DataBase.*;

public interface MovieDAO extends IDBConnection {

    default Movie setMoviewViewed(Movie movie) {
        String currentDate = AmazonUtil.getCurrentDate();
        String insertMovieQuery = "INSERT INTO " + TVIEWED +
                " ( " + TVIEWED_ID_MATERIAL + ", " +
                TVIEWED_ID_ELEMENT + ", " +
                TVIEWED_ID_USER + ", " +
                TVIEWED_VIEW_DATE + " ) " +  "VALUES (" +
                ID_TMATERIALS[0] + "," + movie.getId() + "," +
                TUSER_IDUSUARIO + ", " + currentDate + ")";
        movie.setViewed_date(currentDate);
        return movie;
    }

    default ArrayList<Movie> read() {
        ArrayList<Movie> movies = new ArrayList<>();
        try (Connection connection = connectDB()) {
            String query = "SELECT * FROM " + TMOVIE;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                Movie movie = new Movie(
                        rs.getString(TMOVIE_TITLE),
                        rs.getString(TMOVIE_GENRE),
                        rs.getString(TMOVIE_CREATOR),
                        Integer.valueOf(rs.getString(TMOVIE_DURATION)),
                        Short.valueOf(rs.getString(TMOVIE_YEAR)),
                        ""
                );
                movie.setId(Integer.valueOf(rs.getString(TMOVIE_ID)));
                movie.setViewed(getMovieViewed(
                        preparedStatement,
                        connection,
                        movie.getId()));

                String viewedQuery = "SELECT * FROM " + TVIEWED +
                        " WHERE " + TVIEWED_ID_USER + " = " + TUSER_IDUSUARIO +
                        " AND " + TVIEWED_ID_MATERIAL + " = 1 " +
                        " AND " + TVIEWED_ID_ELEMENT + " = " + movie.getId();
                PreparedStatement preparedStatement2 = connection.prepareStatement(viewedQuery);
                ResultSet rs2 = preparedStatement2.executeQuery();

                while(rs2.next()) {
                    movie.setViewed_date(rs2.getString(TVIEWED_VIEW_DATE));
                }

                movies.add(movie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    private boolean getMovieViewed(PreparedStatement preparedStatement, Connection connection, int id_movie) {
        boolean viewed = false;
        String query = "SELECT * FROM " + TVIEWED +
                " WHERE " + TVIEWED_ID_MATERIAL + "= ?" +
                " AND " + TVIEWED_ID_ELEMENT + "= ?" +
                " AND " + TVIEWED_ID_USER + "= ?";
        ResultSet rs = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ID_TMATERIALS[0]);
            preparedStatement.setInt(2, id_movie);
            preparedStatement.setInt(3, TUSER_IDUSUARIO);

            rs = preparedStatement.executeQuery();
            viewed = rs.next();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return viewed;
    }

}
