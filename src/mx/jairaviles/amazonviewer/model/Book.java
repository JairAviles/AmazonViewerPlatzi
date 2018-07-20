package mx.jairaviles.amazonviewer.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Book extends Publication implements IVisualized {

    private int id;
    private String isbn;
    private boolean read;
    private int timeRead;

    public Book(String title, Date editionDate, String editorial, String[] authors, String isbn) {
        super(title, editionDate, editorial, authors);
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public int getTimeRead() {
        return timeRead;
    }

    public void setTimeRead(int timeRead) {
        this.timeRead = timeRead;
    }

    @Override
    public Date startToSee(Date startDate) {
        return startDate;
    }

    @Override
    public void stopToSee(Date startDate, Date endDate) {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(startDate);
        end.setTime(endDate);
        if (end.get(Calendar.SECOND) > start.get(Calendar.SECOND)) {
            setTimeRead(end.get(Calendar.SECOND) - start.get(Calendar.SECOND));
        } else {
            setTimeRead(0);
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", read=" + read +
                ", timeRead=" + timeRead +
                '}';
    }

    public void view() {
        setRead(true);
        Date dateI = startToSee(new Date());

        for (int i = 0; i < 1000000; i++) {
            System.out.println("......");
        }

        stopToSee(dateI, new Date());
        System.out.println();
        System.out.println("Leiste" + toString());
    }

    public static ArrayList<Book> makeBookList() {
        ArrayList<Book> books = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            books.add(new Book("Book" + i, new Date(), "Editorial" + i, null, "ISBN" + i));
        }

        return books;
    }

}
