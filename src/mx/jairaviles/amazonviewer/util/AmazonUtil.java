package mx.jairaviles.amazonviewer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AmazonUtil {

    public static int validateUserResponseMenu(int min, int max) {
        Scanner sc = new Scanner(System.in);

        while(!sc.hasNextInt()) {
            sc.next();
            System.out.println("Invalid option!");
            System.out.println("Try again");
        }

        int response;
        for(response = sc.nextInt(); response < min || response > max; response = sc.nextInt()) {
            System.out.println("Invalid Option!");
            System.out.println("Try again!");

            while(!sc.hasNextInt()) {
                sc.next();
                System.out.println("Invalid Option!");
                System.out.println("Try again!");
            }
        }

        System.out.println("Option selected: " + response + "\n");
        return response;
    }

    public static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateFormatted = simpleDateFormat.format(date);

        return dateFormatted;
    }

    public static String formatDate(String date) throws ParseException {
        return date != null && date.length() > 0 ?
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date).toString() : "";
    }

}
