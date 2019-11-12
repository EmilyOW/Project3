import java.io.File;
import java.util.Scanner;

/*Emily Cirbo
 * CS 2300 Project 3
 * Our goal is to write a program that does Gaussian elimination to diagonalize a matrix in order to find the vector of scaling
 * once we have diagonalized the matrix, we just have to plug numbers and chug
 */

public class Project3 {

	public static void main(String[] args) throws Exception{
		File firstFile = new File("sysmat1.txt");
		File secondFile = new File("sysmat2.txt");
		File firstVec = new File("prodVec1.txt");
		File secondVec = new File("prodVec2.txt");
		
		Scanner readFirst = new Scanner(firstFile);
		Scanner readSecond = new Scanner(secondFile);
		Scanner readVec1 = new Scanner(firstVec);
		Scanner readVec2 = new Scanner(secondVec);
		
		int numRows = readFirst.nextInt();
		int numCols = numRows;
		
		float[][] array1 = new float[numRows][numCols];
		
		//populate the original system matrix
		for(int i=0; i<numRows;i++) {
			for(int j=0; j<numCols;j++) {
				array1[i][j] = readFirst.nextFloat();
			}
		}
		
		int temp = readVec1.nextInt();
		float[] prodArray = new float[temp];
		
		//populate the product vector array
		for(int i=0; i<temp;i++) {
			prodArray[i] = readVec1.nextFloat();
		}
		
		float[][] solArray = new float[numRows][numCols+1];
		
		//first loop to populate the solution vector with the system array
		for(int i=0; i<numRows; i++) {
			 for(int j=0; j<numCols; j++) {
				solArray[i][j] = array1[i][j];
			 }
		}
		
		//populate the last column with the solution vector
		for(int i=0; i<numRows; i++) {
			solArray[i][numCols] = prodArray[i];
		}
		System.out.println("Original solution matrix:");
		//print the solution array
		printArray(solArray);
		
		//swap Row 0 and 1
		swapRow(solArray,0,1);			
		elimRow(solArray,0,1);		
		System.out.println("\nFirst eliminated updated array:");
		printScaled(solArray);
		
		elimRow(solArray,0,2);
		System.out.println("\nSecond eliminated updated array:");
		printScaled(solArray);
		
		elimRow(solArray,1,2);
		System.out.println("\nThird eliminated updated array:");
		printScaled(solArray);
		
		scaleMatrix(solArray,(float) .0001);
		System.out.println("\nMatrix scaled by .0001");
		printScaled(solArray);
		
		//create the solution array and just move backwards through it
		float[] solVector = new float[3];
		solVector[2] = solArray[2][3]/solArray[2][2];
		
		scaleMatrix(solArray, (float) 1000);
		solVector[1] = (solArray[1][3] - solArray[1][2]*solVector[2])/solArray[1][1];
		
		solVector[0] = (solArray[0][3] - solArray[0][2]*solVector[1] - solArray[1][2]*solVector[2])/solArray[0][0];
		
		System.out.println("\nSolution Vector:");
		for(int i=0; i<solVector.length;i++) {
			System.out.println(solVector[i]);
		}
		
		//begin doing the same with the second array
		int numRows2 = readSecond.nextInt();
		int numCols2 = numRows2;
		
		float[][] array2 = new float[numRows2][numCols2];
		
		//populate the original system matrix
		for(int i=0; i<numRows2;i++) {
			for(int j=0; j<numCols2;j++) {
				array2[i][j] = readSecond.nextFloat();
			}
		}
		
		int temp2 = readVec2.nextInt();
		float[] prodArray2 = new float[temp2];
		
		//populate the product vector array
		for(int i=0; i<temp2;i++) {
			prodArray2[i] = readVec2.nextFloat();
		}
		
		float[][] solArray2 = new float[numRows2][numCols2+1];
		
		//first loop to populate the solution vector with the system array
		for(int i=0; i<numRows2; i++) {
			 for(int j=0; j<numCols2; j++) {
				solArray2[i][j] = array2[i][j];
			 }
		}
		
		//populate the last column with the solution vector
		for(int i=0; i<numRows2; i++) {
			solArray2[i][numCols2] = prodArray2[i];
		}
		
		System.out.println("\nOriginal solution matrix 2:");
		printArray(solArray2);
		
		//swap row 0 and 9
		swapRow(solArray2,0,9);
		//eliminate rows 1-9 now
		for(int i=1; i<solArray2.length; i++) {			
			elimRow(solArray2,0,i);
		}
		System.out.println("\nRow 1 eliminated:");
		printScaled(solArray2);
		
		//swap and elim row 2 now
		swapRow(solArray2,1,3);
		for(int i=2; i<solArray2.length; i++) {			
			elimRow(solArray2,1,i);
		}
		scaleMatrix(solArray2,(float) .001);
		System.out.println("\nRow 2 eliminated:");
		printScaled(solArray2);
		
		//swap and elim row 3 now
		for(int i=3; i<solArray2.length; i++) {			
			elimRow(solArray2,2,i);
		}
		System.out.println("\nRow 3 eliminated:");
		printScaled(solArray2);
		
		//swap and elim row 4 now
		swapRow(solArray2,3,6);
		for(int i=4; i<solArray2.length; i++) {			
			elimRow(solArray2,3,i);
		}
		scaleMatrix(solArray2,(float) .001);
		System.out.println("\nRow 4 eliminated:");
		printScaled(solArray2);
		
		//swap and elim row 5 now
		for(int i=5; i<solArray2.length; i++) {			
			elimRow(solArray2,4,i);
		}
		scaleMatrix(solArray2,(float) .0001);
		System.out.println("\nRow 5 eliminated:");
		printScaled(solArray2);
		
		//swap and elim row 6
		swapRow(solArray2,5,9);
		for(int i=6; i<solArray2.length; i++) {			
			elimRow(solArray2,5,i);
		}
		System.out.println("\nRow 6 eliminated:");
		printScaled(solArray2);
		
		//swap and elim row 7 now
		for(int i=7; i<solArray2.length; i++) {			
			elimRow(solArray2,6,i);
		}
		scaleMatrix(solArray2,(float) .0001);
		System.out.println("\nRow 7 eliminated:");
		printScaled(solArray2);
		
		//swap and elim row 8
		swapRow(solArray2,7,9);
		for(int i=8; i<solArray2.length; i++) {			
			elimRow(solArray2,7,i);
		}
		System.out.println("\nRow 8 eliminated:");
		printScaled(solArray2);
		
		//swap and elim row 9 now
		for(int i=9; i<solArray2.length; i++) {			
			elimRow(solArray2,8,i);
		}
		System.out.println("\nRow 9 eliminated:");
		printScaled(solArray2);
		
		//create and print the solution vector
		float[] solVector2 = new float[10];
		
		solVector2[9] = solArray2[9][10]/solArray2[9][9];
		
		solVector2[8] = (solArray2[8][10] - solArray2[8][9]*solVector2[9])/solArray2[8][8];
		
		solVector2[7] = (solArray2[7][10] - solArray2[7][9]*solVector2[9] - solArray2[7][8]*solVector2[8])/solArray2[7][7];
		
		solVector2[6] = (solArray2[6][10] - solArray2[6][9]*solVector2[9] - solArray2[6][8]*solVector2[8] - solArray2[6][7]*solVector2[7])/solArray2[6][6];
		
		//any place we scaled the matrix, we need to rescale it
		scaleMatrix(solArray2,(float) 1000);
		solVector2[5] = (solArray2[5][10] - solArray2[5][9]*solVector2[9] - solArray2[5][8]*solVector2[8] - solArray2[5][7]*solVector2[7]- solArray2[5][6]*solVector2[6])/solArray2[5][5];
		
		scaleMatrix(solArray2,(float) 1000);
		solVector2[4] = (solArray2[4][10] - solArray2[4][9]*solVector2[9] - solArray2[4][8]*solVector2[8] - solArray2[4][7]*solVector2[7]- solArray2[4][6]*solVector2[6]- solArray2[4][5]*solVector2[5])/solArray2[4][4];
		
		scaleMatrix(solArray2,(float) 100);
		solVector2[3] = (solArray2[3][10] - solArray2[3][9]*solVector2[9] - solArray2[3][8]*solVector2[8] - solArray2[3][7]*solVector2[7]- solArray2[3][6]*solVector2[6]- solArray2[3][5]*solVector2[5]- solArray2[3][4]*solVector2[4])/solArray2[3][3];
		
		solVector2[2] = (solArray2[2][10] - solArray2[2][9]*solVector2[9] - solArray2[2][8]*solVector2[8] - solArray2[2][7]*solVector2[7]- solArray2[2][6]*solVector2[6]- solArray2[2][5]*solVector2[5]- solArray2[2][4]*solVector2[4]- solArray2[2][3]*solVector2[3])/solArray2[2][2];
		
		scaleMatrix(solArray2,(float) 100);
		solVector2[1] = (solArray2[1][10] - solArray2[1][9]*solVector2[9] - solArray2[1][8]*solVector2[8] - solArray2[1][7]*solVector2[7]- solArray2[1][6]*solVector2[6]- solArray2[1][5]*solVector2[5]- solArray2[1][4]*solVector2[4]- solArray2[1][3]*solVector2[3]- solArray2[1][2]*solVector2[2])/solArray2[1][1];
		
		solVector2[0] = (solArray2[0][10] - solArray2[0][9]*solVector2[9] - solArray2[0][8]*solVector2[8] - solArray2[0][7]*solVector2[7]- solArray2[0][6]*solVector2[6]- solArray2[0][5]*solVector2[5]- solArray2[0][4]*solVector2[4]- solArray2[0][3]*solVector2[3]- solArray2[0][2]*solVector2[2]- solArray2[0][1]*solVector2[1])/solArray2[0][0];
		
		System.out.println("\nSolution Vector:");
		for(int i=0; i<solVector2.length;i++) {
			System.out.println(solVector2[i]);
		}
	}//main
	
	//method to print the Array every time something is update
	public static void printArray(float[][] array) {
		for(int i=0; i<array.length; i++) {
			for(int j=0; j<array[0].length; j++) {
				System.out.print(array[i][j]);
				System.out.print("\t");
			}
			System.out.print("\n");
		}
	}
	
	//method to print the scaled array
	public static void  printScaled(float[][] array) {
		for(int i=0; i<array.length; i++) {
			for(int j=0; j<array[0].length; j++) {
				System.out.printf("%.2f",array[i][j]);
				System.out.print("\t");
			}
			System.out.print("\n");
		}
	}
	
	//method to swap 2 rows
	public static void swapRow(float[][] array, int firstRow, int secondRow) {
		for(int i=0; i<array[0].length; i++) {
			float tempSwap = array[firstRow][i];
			array[firstRow][i] = array[secondRow][i];
			array[secondRow][i] = tempSwap;
		}
	}
	
	//method to scale each row by the row we want to eliminate
	public static void elimRow(float[][] array, int rowKept, int elimRow) {
		//first step is to scale each value in the row by the constant
		//make sure to save the scale values first so they don't change when we update each row
		float scale0 = array[elimRow][rowKept];
		float scale1 = array[rowKept][rowKept];
			
		for(int i=0; i<array[0].length; i++) {				
			//scale the kept row value by the eliminated row value
			float temp = array[rowKept][i] * scale0;
			array[elimRow][i] = array[elimRow][i] * -scale1;
					
			//add row 0 to row 1 and update row 1
			array[elimRow][i] = temp + array[elimRow][i];
		}
	}

	//method to scale the output of each matrix
	public static void scaleMatrix(float[][] array, float scale) {
		for(int i=0; i<array.length;i++) {
			for(int j=0; j<array[0].length; j++) {
				array[i][j] = array[i][j] * scale;
			}
		}
	}
}
