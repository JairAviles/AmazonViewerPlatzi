package mx.jairaviles.amazonviewer.model;

import mx.jairaviles.amazonviewer.util.AmazonUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Book extends Publication implements IVisualizable {

    private int id;
    private String isbn;
    private boolean read;
    private int timeRead;

    private ArrayList<Page> pages;

    public Book(int id, String title, Date editionDate, String editorial, String[] authors, String isbn, ArrayList<Page> pages) {
        super(title, editionDate, editorial, authors);
        this.id = id;
        this.isbn = isbn;
        this.pages = pages;
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

    public ArrayList<Page> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Page> pages) {
        this.pages = pages;
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
        String detailBook = getId() + ". " + getTitle() + " Read " + isRead();
        return  detailBook;
    }

    public String getBookDetail() {
        String detailBook = "\n :: BOOK ::" +
                "\n Title: " + getTitle() +
                "\n Editorial: " + getEditorial() +
                "\n Edition Date: " + getEditionDate() +
                "\n Authors: ";
        for (int i = 0; i < getAuthors().length; i++) {
            detailBook += "\t" + getAuthors()[i] + " ";
        }
        return  detailBook;
    }

    public void view() {
        setRead(true);
        Date dateI = startToSee(new Date());

        int i = 0;
        do {
            System.out.println("..........");
            System.out.println("Page" + getPages().get(i).getNumber());
            System.out.println(getPages().get(i).getContent());
            System.out.println("..........");

            if (i != 0) {
                System.out.println("1. Previous Page");
            }

            System.out.println("2. Next Page");
            System.out.println("0. Close Book");
            System.out.println();

            int response = AmazonUtil.validateUserResponseMenu(0, 2);

            if (response == 2) {
                i++;
            } else if (response == 1 ) {
                i--;
            } else {
                break;
            }

        } while (i < getPages().size());

        stopToSee(dateI, new Date());
        System.out.println();
        System.out.println("Read" + getBookDetail());
        System.out.println("For:" + getTimeRead() + " miliseconds");
    }

    public static ArrayList<Book> makeBookList() {
        ArrayList<Book> books = new ArrayList<>();
        String[] authors = new String[3];
        for (int i = 0; i <3; i++) {
            authors[i] = "author " + i;
        }

        ArrayList<Page> pages = new ArrayList<>();
        int page = 0;
        for (int i = 0; i < 3; i++) {
            page = i+1;
            pages.add(new Book.Page(page, "Page content: " + page));
        }

        for (int i = 1; i <= 5; i++) {
            books.add(new Book(i, "Book" + i, new Date(), "Editorial" + i, authors, "ISBN" + i, pages));
        }

        return books;
    }

    public static class Page {
        private  int id;
        private int number;
        private String content;

        public Page(int number, String content) {
            this.number = number;
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
