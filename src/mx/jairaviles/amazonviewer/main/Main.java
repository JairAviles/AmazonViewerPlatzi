package mx.jairaviles.amazonviewer.main;

import mx.jairaviles.amazonviewer.model.*;
import mx.jairaviles.amazonviewer.util.AmazonUtil;
import mx.jairaviles.makereport.Report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * <h1>AmazonViewer</h1>
 * AmazonViewer es un programa que permite visualizar
 * peliculas, series con sus respectivos capitulos.
 * Ademas de leer libros y revistas. Te permite generar
 * reportes generales y del dia.
 * <p>
 *     Existen algunas reglas, como que todos los elementos
 *     pueden ser visualizados.
 * </p>
 *
 * @author Jair-MAC
 * @version 1.1
 * @since 2018
 *
 * */

public class Main {

    private static ArrayList<Movie> movies = Movie.makeMovieList();
    private static ArrayList<Serie> series = Serie.makeSeriesList();
    private static ArrayList<Book> books = Book.makeBookList();

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

            AtomicInteger atomicInteger = new AtomicInteger(1);
            movies.forEach(m -> System.out.println(atomicInteger.getAndIncrement() + ".\t" + m.toString()));
            System.out.println("0. Back to Menu \n");

            int response = AmazonUtil.validateUserResponseMenu(0, movies.size());

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

            AtomicInteger atomicInteger = new AtomicInteger(1);
            series.forEach(s -> System.out.println(atomicInteger.getAndIncrement() + ".\t" + s.toString()));

            System.out.println("0. Back to Menu \n");
            Scanner sc = new Scanner(System.in);
            int response = Integer.valueOf(sc.nextLine());

            if (response == 0) {
                showMenu();
            }

            if (response > 0) {
                showChapters(series.get(response-1).getChapters());
            }

        } while (exit != 0);
    }

    public static void showBooks() {
        int exit = 1;
        ArrayList<Book> books = Book.makeBookList();
        do {
            System.out.println();
            System.out.println(":: BOOKS ::");
            System.out.println();

            AtomicInteger atomicInteger = new AtomicInteger(1);
            books.forEach(b -> System.out.println(atomicInteger.getAndIncrement() + ".\t" + b.toString()));

            System.out.println("0. Back to Menu");
            int response = AmazonUtil.validateUserResponseMenu(0, books.size());

            if (response == 0 || response > books.size()) {
                showMenu();
            } else {
                Book selectedBook = books.get(response - 1);
                selectedBook.view();
            }
        } while(exit != 0);
    }

    public static void showMagazines() {
        ArrayList<Magazine> magazines = Magazine.makeMagazineList();
        int exit = 1;
        do {
            System.out.println();
            System.out.println(":: MAGAZINES ::");
            System.out.println();


            AtomicInteger atomicInteger = new AtomicInteger(1);
            magazines.forEach(m -> System.out.println(atomicInteger.getAndIncrement() + ".\t" + m.toString()));

            System.out.println("0. Back to Menu");
            System.out.println("Magazines size: " + magazines.size());

            int response = AmazonUtil.validateUserResponseMenu(0, magazines.size());

            if (response == 0 || response > magazines.size()) {
                showMenu();
            } else {
                Magazine selectedMagazine = magazines.get(response - 1);
                System.out.println(selectedMagazine.view());
            }

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

            System.out.println("0. Back to Menu");
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
        StringBuilder contentReport = new StringBuilder();

        movies.stream()
                .filter(m -> m.getIsViewed())
                .forEach(m -> contentReport.append(m.toString() + "\n"));

        series.stream()
                .forEach(s -> s.getChapters().stream()
                    .filter(c -> c.getIsViewed())
                        .forEach(c -> contentReport.append(c.toString() + "\n"))
                );

        books.stream()
                .filter(b -> b.isRead())
                .forEach(b -> contentReport.append(b.toString() + "\n"));

        report.setContent(contentReport.toString());
        report.makeReport();

    }

    public static void makeReport(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = df.format(date);
        Report report = new Report();
        report.setNameFile("Report-" + dateString);
        report.setExtension("txt");
        report.setTitle(":: MOVIES WATCHED ::\n");
        StringBuilder contentReport = new StringBuilder();

        movies.stream()
                .filter(m -> m.getIsViewed())
                .forEach(m -> contentReport.append(m.toString() + "\n"));

        report.setContent(contentReport.toString());
        report.makeReport();

    }

}
