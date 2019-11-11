package algorithm.wenhan_homework;

import java.util.Scanner;

public class MatrixMOOP{

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        //Take input of number of rows
        System.out.print("Please enter the number of rows of the matrix to be created [max 4, min 1]:");
        int rows= input.nextInt();
        //Validate the number of rows
        while((rows > 4) || (rows < 1)){
            System.out.println("\nInvalid number of rows.");
            System.out.print("Please enter the number of rows of the matrix to be created [max 4, min 1]:");
            rows = input.nextInt();
        }
        // Take input of number of columns
        System.out.print("Please enter the number of columns of the matrix to be created [max 4, min 1]:");
        int cols = input.nextInt();
        // Validate number of columns
        while ((cols > 4) || (cols < 1)){
            System.out.println("\nInvalid number of columns.");
            System.out.print("Please enter the number of columns of the matrix to be created [max 4, min 1]:");
            cols = input.nextInt();
        }
        System.out.printf("Your matrix has %d rows, %d columns.\n",rows,cols);
        //Creating a matrix based on user's input
        Matrix myMatrix = new Matrix(rows,cols);

        myMatrix.printMatrix();
        System.out.println();

        displayMenu();

        String choice = input.next().toUpperCase();
        while (!choice.equals("Q")){
            if(choice.equals("P")){

                myMatrix.printMatrix();
            }else if (choice.equals("R")){

                myMatrix.reverseRows().printMatrix();
            }else if (choice.equals("S")){

                myMatrix.columnSum().printMatrix();
            }else if (choice.equals("T")){

                myMatrix.transpose().printMatrix();
            }else if (choice.equals("AR")){

                myMatrix.add(myMatrix.reverseRows()).printMatrix();
            }else if (choice.equals("AN")) {

                System.out.print("Please enter a number to be added to the matrix:");
                Matrix inc = new Matrix (myMatrix.mat.length, myMatrix.mat[0].length);
                int numToAdd = input.nextInt();
                inc.setNum(numToAdd);

                inc.add().printMatrix();
            }else{
                System.out.println("Invalid choice.");
            }
            displayMenu();
            choice = input.next().toUpperCase();
        }
        System.out.println("\nQuiting the program...Bye!\n");

    }
    //a method to display the operation menu
    public static void displayMenu(){
        System.out.println("P Print matirx  - Print the contents of the matrix");
        System.out.println("R Reverse rows  - Reverse all elements in every row of the matrix");
        System.out.println("S columnSum   - Calculate the sum of the values in each column");
        System.out.println("T transpose   - Rows become columns (and vice versa)");
        System.out.println("AR addReverse   - Add reverse of matrix to original matrix");
        System.out.println("AN addNum       - Add a number to each element of the original matrix");
        System.out.println("Q quit          - Exit the program");
        System.out.print("What would you like to do? Enter P, R, S, T, AR, AN or Q to quit:");
    }
}

class Matrix{
    public int[][] mat;

    //constructor
    public Matrix(int row, int col){
        // Initialize the matrix with the input dimentions
        mat = new int[row][col];

        int start = 0;
        //Generate a matrix based on start 0
        for (int i = 0; i < row; i++){
            for (int j = 0; j< col; j++){
                mat[i][j] = start;
                start += 1;
            }
            start += (10 - col);
        }
    }

    //print the matrix method
    public void printMatrix(){
        // Print the matrix generated
        System.out.println();
        for (int i = 0; i < mat.length; i++){
            // Loop through all elements of current row
            for (int j = 0; j < mat[0].length; j++){
                System.out.printf("%4d", mat[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public Matrix transpose(){
        Matrix tmat = new Matrix(mat[0].length, mat.length);
        for (int i = 0; i < mat.length; i++){
            for (int j = 0; j < mat[0].length; j++){
                tmat.mat[j][i] = mat[i][j];
            }
        }
        return tmat;
    }

    public Matrix columnSum(){

        Matrix cmat = new Matrix(1,mat[0].length);

        for (int i = 0; i < mat.length; i++){
            for (int j = 0; j < mat[0].length; j++){
                cmat.mat[0][j] += this.mat[i][j];
            }
        }
        return cmat;
    }

    public Matrix reverseRows(){
        Matrix rmat = new Matrix (mat.length,mat[0].length);
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++) {
                rmat.mat[i][j] = this.mat[i][mat[0].length - j - 1];
            }
        }
        return rmat;
    }

    public Matrix add(Matrix theMat){
        Matrix sum = new Matrix (mat.length,mat[0].length);
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++) {
                sum.mat[i][j] = theMat.mat[i][j] + this.mat[i][j];
            }
        }
        return sum;
    }

    private int numToAdd;

    public void setNum(int numToAdd) {
        this.numToAdd = numToAdd;
    }

    public Matrix add(){

        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++) {
                mat[i][j] = mat[i][j] + numToAdd;
            }
        }
        return this;
    }

}