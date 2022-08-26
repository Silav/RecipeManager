package views.base;

import views.Dietician;
import views.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Person {

    private static final Dietician dietician = new Dietician();
    private static final User user = new User();

    public void printBannerLoop() {
        try {
            Scanner userType = new Scanner(System.in);
            System.out.println("Please choose an user type:");
            System.out.println("[1] User");
            System.out.println("[2] Dietician");

            System.out.print("Chosen User : ");
            switch (userType.nextInt()) {
                case 1:
                    user.displayUserMenu();
                    break;
                case 2:
                    dietician.displayDieticianMenu();
                    break;

                default:
                    System.out.println("Wrong Input");
                    printBannerLoop();

            }
        }catch (InputMismatchException e){
            System.out.println("Wrong Input");
            printBannerLoop();
        }

    }

}
