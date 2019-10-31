package algorithm.Evelynne;

import java.util.Scanner;

/**
 *
 */
class MatrixObject {
	public int rows;
	public int cols;
	public int [][] m1;
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
		MatrixObject m1 = new MatrixObject(rows,cols);
        m1.printMatrix();
		System.out.println();

		displayMenu();
		String choice = input.next().toUpperCase();
		MatrixObject rmat, tmat;
		while (!choice.equals("Q")){
			if(choice.equals("P")){
                m1.printMatrix();
			}else if (choice.equals("R")){
                rmat = m1.reverseRows();
                rmat.printMatrix();
			}else if (choice.equals("S")){
                m1.columnSum().printMatrix();
			}else if (choice.equals("T")){
                m1.transpose().printMatrix();
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
		System.out.println("S columnSum  	- Calculate the sum of the values in each column");
		System.out.println("T transpose  	- Rows become columns (and vice versa)");
		System.out.println("Q quit          - Exit the program");
		System.out.print("What would you like to do? Enter P, R, S, T, or Q to quit:");
	}

	public MatrixObject(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        m1 = new int[rows][cols];

        int start = 0;
        //Generate a matrix based on a random number in 1 to 10
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m1[i][j] = start;
                start += 1;
            }
            start += (10 - cols);
        }
    }

    public MatrixObject(int[][] m) {
        this.rows = m.length;
        this.cols = m[0].length;
        this.m1 = m;
    }

	//print the matrix method 
	public void printMatrix(){
		// Print the matrix generated
		System.out.println();

		for (int i = 0; i < rows; i++){
            // Loop through all elements of current row 
            for (int j = 0; j < cols; j++){
                System.out.printf("%4d", m1[i][j]);
    		} 
    		System.out.println();
    	}
    	System.out.println();
	}
	public MatrixObject transpose(){
		int[][] tmat = new int[cols][rows];
		for (int i = 0; i < rows; i++){ 
            for (int j = 0; j < cols; j++){
                tmat[j][i] = this.m1[i][j];
            }
        }
        return new MatrixObject(tmat);
	}
	public MatrixObject columnSum(){
		int[][] cmat = new int[1][cols];
		for (int i = 0; i < rows; i++){ 
            for (int j = 0; j < cols; j++){
            	cmat[0][j] += this.m1[i][j];
            }
        }
        return new MatrixObject(cmat);
	}

	public MatrixObject reverseRows(){

		int[][] rmat = new int [rows][cols];
		for(int i = 0; i < rows; i++){
    		for(int j = 0; j < cols; j++) {
                rmat[i][j] = this.m1[i][cols - j - 1];
    		}
    	}
    	return new MatrixObject(rmat);
	}
	
}	