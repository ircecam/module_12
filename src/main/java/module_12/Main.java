package module_12;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        menuBar();

    }
    public static void menuBar(){
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        boolean isActive = true;
        while(isActive && choice != 3){
            System.out.println("What exercise do you want to test?\n" +
                    "1 - Exercise 1\n"+
                    "2 - Exercise 2\n"+
                    "3 - Exit\n"+
                    "Your choice: ");
            choice = scanner.nextInt();
            switch (choice){
                case 1: solveEx1();
                case 2: solveFizzBuzz();
                case 3: isActive = false;
            }
        }


    }
    public static void solveEx1(){
        System.out.println("Exercise 1");
        TimeTester timeTester = new TimeTester();
        try {
            timeTester.getTimeFromStartOfProgram();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void solveFizzBuzz(){
        System.out.println("Exercise 2");
        FizzBuzzProblem fizzBuzzProblem = new FizzBuzzProblem(30);
        try {
            fizzBuzzProblem.solve();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
