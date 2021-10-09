package tictactoe;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        String[] arr = new String[9];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            arr[i] = "_";
        }

        printArray(arr);

        String[][] newArray = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newArray[i][j] = arr[(i * 3) + j];
            }
        }
        int counter = 0;
        int a;
        int b;
        boolean flag = true;
        while (flag && (!xWins(arr) || !oWins(arr) || !draw(arr))) {
            System.out.print("Enter the coordinates: ");
            flag = true;
            try {
                a = scanner.nextInt();
                b = scanner.nextInt();
                scanner.nextLine();

                flag = true;
                try {
                    if (!newArray[a - 1][b - 1].equals("_")) {
                        System.out.println("This cell is occupied! Choose another one!");
                        flag = true;
                    } else {
                        counter++;
                        if (counter % 2 != 0) {
                            newArray[a - 1][b - 1] = "X";
                        } else {
                            newArray[a - 1][b - 1] = "O";
                        }
                        for (int i = 0; i < arr.length; i++) {
                            arr[i] = newArray[i/3][i%3];
                        }
                        printArray(arr);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    flag = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.next();
                flag = true;
            } if(xWins(arr) || oWins(arr) || draw(arr)) {
                flag = false;
            }
        }


        if (xWins(arr) && !impossible(arr)) {
            System.out.println("X wins");
        }

        if (oWins(arr) && !impossible(arr)) {
            System.out.println("O wins");
        }

        if(impossible(arr)) {
            System.out.println("Impossible");
        }

        if(draw(arr)) {
            System.out.println("Draw");
        }

        if(gameNotFinished(arr)) {
            System.out.println("Game not finished");
        }

    }
    public static void printArray (String[] array) {
        System.out.print("---------");
        System.out.println();
        for (int i = 0; i < array.length; i++) {

            if (i == 0 || i == 3 || i == 6) {

                System.out.print("| ");
            }
            System.out.print(array[i] + " ");
            if (i == 2 || i == 5 || i == 8) {
                System.out.print("|");

                System.out.println();

            }
        }
        System.out.print("---------");
        System.out.println();
    }

    public static boolean xWins(String[] arr) {

        if ((arr[0].equals("X") && arr[1].equals("X") && arr[2].equals("X")) ||
                (arr[1].equals("X") && arr[4].equals("X") && arr[7].equals("X")) ||
                (arr[2].equals("X") && arr[5].equals("X") && arr[8].equals("X")) ||
                (arr[0].equals("X") && arr[3].equals("X") && arr[6].equals("X")) ||
                (arr[3].equals("X") && arr[4].equals("X") && arr[5].equals("X")) ||
                (arr[6].equals("X") && arr[7].equals("X") && arr[8].equals("X")) ||
                (arr[0].equals("X") && arr[4].equals("X") && arr[8].equals("X")) ||
                (arr[2].equals("X") && arr[4].equals("X") && arr[6].equals("X"))) {
            return true;
        }
        return false;
    }

    public static boolean oWins(String[] arr) {

        if ((arr[0].equals("O") && arr[1].equals("O") && arr[2].equals("O")) ||
                (arr[0].equals("O") && arr[3].equals("O") && arr[6].equals("O")) ||
                (arr[1].equals("O") && arr[4].equals("O") && arr[7].equals("O")) ||
                (arr[2].equals("O") && arr[5].equals("O") && arr[8].equals("O")) ||
                (arr[3].equals("O") && arr[4].equals("O") && arr[5].equals("O")) ||
                (arr[6].equals("O") && arr[7].equals("O") && arr[8].equals("O")) ||
                (arr[0].equals("O") && arr[4].equals("O") && arr[8].equals("O")) ||
                (arr[2].equals("O") && arr[4].equals("O") && arr[6].equals("O"))) {
            return true;
        }
        return false;
    }

    public static boolean impossible (String[] arr) {
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < 9; i++) {
            if (arr[i].equals("X")) {
                count1++;
            } else if (arr[i].equals("O")) {
                count2++;
            }
        }
        if (Math.abs(count2 - count1) >= 2 || ((xWins(arr)) && oWins(arr))) {
            return true;
        }
        return false;
    }

    public static boolean gameNotFinished (String[] arr) {
        for (String s : arr) {
            if (s.equals("_") && !xWins(arr) && !oWins(arr) && !impossible(arr)) {
                return true;
            }
        }
        return false;
    }
    public static boolean draw (String[] arr) {
        for (String s : arr) {
            if (!s.equals("_") && !xWins(arr) && !oWins(arr) && !impossible(arr) && !gameNotFinished(arr)) {
                return true;

            }
        }
        return false;
    }
}
