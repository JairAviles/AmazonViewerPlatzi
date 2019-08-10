package mx.jairaviles.amazonviewer.model;


/**
 * <h1>Film</h1>
 * Clase padre abstracta
 * <p>
 *     Esta clase es la base de la familia de Films.
 *     Contiene el metodo abstracto
 *     {@code view()} que es obligatorio implementar
 * </p>
 *
 * @author Jair-MAC
 * @version 1.1
 * @since 2018
 *
 * */
public abstract class Film {

    private String title;
    private String genre;
    private String creator;
    private int duration;
    private short year;
    private boolean viewed;

    public Film() {}
    public Film(String title, String genre, String creator, int duration) {
        this.title = title;
        this.genre = genre;
        this.creator = creator;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public String isViewed() {
        String result = "No";

        if (viewed == true) {
            result = "Yes";
        }

        return result;
    }

    public boolean getIsViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    /**
     * {@code view()} abstracto obligatorio de implementar
     * */
    public abstract void view();
}
