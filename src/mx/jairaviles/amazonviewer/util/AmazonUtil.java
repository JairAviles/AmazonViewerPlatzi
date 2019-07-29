package mx.jairaviles.amazonviewer.util;

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
}
