package algorithm.Evelynne;

import java.util.Scanner;

public class Matrix {
    public static void main (String[] args) {

        Scanner input = new Scanner(System.in);
        //Take input of number of rows
        System.out.print("Please enter the number of rows of the matrix to be created [max 4, min 1]:");
        int rows = input.nextInt();
        //Validate the number of rows
        while ((rows > 4) || (rows < 1)) {
            System.out.println("\nInvalid number of rows.");
            System.out.print("Please enter the number of rows of the matrix to be created [max 4, min 1]:");
            rows = input.nextInt();
        }
        // Take input of number of columns
        System.out.print("Please enter the number of columns of the matrix to be created [max 4, min 1]:");
        int cols = input.nextInt();
        // Validate number of columns
        while ((cols > 4) || (cols < 1)) {
            System.out.println("\nInvalid number of columns.");
            System.out.print("Please enter the number of columns of the matrix to be created [max 4, min 1]:");
            cols = input.nextInt();
        }
        System.out.printf("Your matrix has %d rows, %d columns.\n", rows, cols);

        // Initialize the matrix with the input dimentions
        int[][] myMatrix = new int[rows][cols];
        int start = 0;
        //Generate a matrix based on a random number in 1 to 10
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                myMatrix[i][j] = start;
                start += 1;
            }
            start += (10 - cols);
        }

        printMatrix(myMatrix);
        System.out.println();
        displayMenu();
        String choice = input.next().toUpperCase();
        while (!choice.equals("Q")) {
            if (choice.equals("P")) {
                printMatrix(myMatrix);
            } else if (choice.equals("R")) {
                printMatrix(reverseRows(myMatrix));
            } else if (choice.equals("S")) {
                printMatrix(columnSum(myMatrix));
            } else if (choice.equals("T")) {
                printMatrix(transpose(myMatrix));
            } else {
                System.out.println("Invalid choice.");
            }
            displayMenu();
            choice = input.next().toUpperCase();
        }
        System.out.println("\nQuiting the program...Bye!\n");
    }

    public static void printMatrix (int[][] matrix) {
        // Print the matrix generated
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            // Loop through all elements of current row 
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void displayMenu () {
        System.out.println("P Print matirx  - Print the contents of the matrix");
        System.out.println("R Reverse rows  - Reverse all elements in every row of the matrix");
        System.out.println("S columnSum  	- Calculate the sum of the values in each column");
        System.out.println("T transpose  	- Rows become columns (and vice versa)");
        System.out.println("Q quit          - Exit the program");
        System.out.print("What would you like to do? Enter P, R, S, T, or Q to quit:");
    }

    public static int[][] transpose (int[][] matrix) {
        int[][] newMatrix = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                newMatrix[j][i] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    public static int[][] columnSum (int[][] matrix) {
        int[][] colSum = new int[1][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                colSum[0][j] += matrix[i][j];
            }
        }
        return colSum;
    }

    public static int[][] reverseRows (int[][] matrix) {
        int[][] revRow = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                revRow[i][j] = matrix[i][matrix[i].length - j - 1];
            }
        }
        return revRow;
    }

}