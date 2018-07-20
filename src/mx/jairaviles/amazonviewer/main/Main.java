package mx.jairaviles.amazonviewer.main;

import mx.jairaviles.amazonviewer.model.*;
import mx.jairaviles.makereport.Report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    private static ArrayList<Movie> movies = Movie.makeMovieList();
    private static ArrayList<Serie> series = Serie.makeSeriesList();

    public static void main(String[] args) {
        showMenu();
    }

    public static void showMenu() {
        int exit = 0;
        do {

            Scanner sc = new Scanner(System.in);

            System.out.println("Welcome to Amazon Viewer!\n");
            System.out.println("Select the number of the desired option");
            System.out.println("1. Movies");
            System.out.println("2. Series");
            System.out.println("3. Books");
            System.out.println("4. Magazines");
            System.out.println("5. Report");
            System.out.println("6. Today's Report");
            System.out.println("0. Exit");

            int option = sc.nextInt();

            switch (option) {
                case 1:
                    showMovies();
                    break;
                case 2:
                    showSeries();
                    break;
                case 3:
                    showBooks();
                    break;
                case 4:
                    showMagazines();
                    break;
                case 5:
                    makeReport();
                    break;
                case 6:
                    makeReport(new Date());
                    break;
                case 0:
                    System.out.println("0. Bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Try again!");
                    break;
            }



        } while (exit != 0);
    }

    public static void showMovies() {
        int exit = 1;
        do {
            System.out.println();
            System.out.println(":: MOVIES ::");
            System.out.println();

            for (Movie movie : movies) {
                System.out.println(movie.toString());
            }

            System.out.println("0. Regresar al Menu \n");
            Scanner sc = new Scanner(System.in);
            int response = Integer.valueOf(sc.nextLine());

            if (response == 0 || response > movies.size()) {
                showMenu();
            } else {

                Movie selectedMovie = movies.get(response - 1);
                selectedMovie.view();
            }


        } while(exit != 0);
    }

    public static void showSeries() {
        int exit = 1;

        do {
            System.out.println();
            System.out.println(":: SERIES ::");
            System.out.println();

            for (Serie serie : series) {
                System.out.println(serie.toString());
            }

            System.out.println("0. Regresar al Menu \n");
            Scanner sc = new Scanner(System.in);
            int response = Integer.valueOf(sc.nextLine());

            if (response == 0) {
                showMenu();
            }

            if (response > 0) {
                showChapters(series.get(response-1).getChapters());
            }

        } while(exit != 0);
    }

    public static void showBooks() {
        int exit = 1;
        ArrayList<Book> books = Book.makeBookList();
        do {
            System.out.println();
            System.out.println(":: BOOKS ::");
            System.out.println();

            for (Book book : books) {
                System.out.println(book.toString());
            }

            System.out.println("0. Regresar al Menu \n");
            Scanner sc = new Scanner(System.in);
            int response = Integer.valueOf(sc.nextLine());

            if (response == 0 || response > books.size()) {
                showMenu();
            } else {
                Book selectedBook = books.get(response - 1);
                selectedBook.view();
            }


        } while(exit != 0);
    }

    public static void showMagazines() {
        int exit = 0;
        do {
            System.out.println();
            System.out.println(":: MAGAZINES ::");
            System.out.println();
        } while(exit != 0);
    }

    public static void showChapters(ArrayList<Chapter> chaptersOfSerieSelected) {
        int exit = 0;

        do {
            System.out.println();
            System.out.println(":: CHAPTERS ::");
            System.out.println();


            for (int i = 0; i < chaptersOfSerieSelected.size(); i++) { //1. Chapter 1
                System.out.println(i+1 + ". " + chaptersOfSerieSelected.get(i).getTitle() + " Visto: " + chaptersOfSerieSelected.get(i).isViewed());
            }

            System.out.println("0. Regresar al Menu");
            System.out.println();

            //Leer Respuesta usuario
            Scanner sc = new Scanner(System.in);
            int response = Integer.valueOf(sc.nextLine());

            if(response == 0) {
                showSeries();
            }

            Chapter chapterSelected = chaptersOfSerieSelected.get(response-1);
            chapterSelected.view();
        } while(exit !=0);
    }

    public static void makeReport() {
        Report report = new Report();
        report.setNameFile("Report");
        report.setExtension("txt");
        report.setTitle(":: MOVIES WATCHED ::\n");
        String contentReport = "";

        for (Movie movie : movies) {
            if (movie.getIsViewed()) {
                contentReport += movie.toString() + "\n";
            }
        }
        report.setContent(contentReport);
        report.makeReport();

    }

    public static void makeReport(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = df.format(date);
        Report report = new Report();
        report.setNameFile("Report-" + dateString);
        report.setExtension("txt");
        report.setTitle(":: MOVIES WATCHED ::\n");
        String contentReport = "";

        for (Movie movie : movies) {
            if (movie.getIsViewed()) {
                contentReport += movie.toString() + "\n";
            }
        }
        report.setContent(contentReport);
        report.makeReport();

    }

}
