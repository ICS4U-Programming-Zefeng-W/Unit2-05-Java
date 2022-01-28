/*
 * This program generates all the magic squares with a magical number equal to 15 
 * with a recursive function.
 *
 * By Zefeng Wang
 * Created on January 26, 2022
*/

// Import Arrays Module
import java.util.Arrays;

class MagicSquares {
  
  private static final int MAGICNUM = 15;
  private static final int LENGTH = 3;

  public static void main(String[] args) {
    
    // Create arrau to store the digits for the magic square
    final int[] digits = new int[9];
    for (int i = 0; i < digits.length; i++) {
      digits[i] = i + 1;
    }
    final int[] numbers = new int[9];
    genSquare(digits, numbers, 0);
  }

  // Checks to see if an array has a magic square
  public static boolean isMagic(int[] square) {
    
    // Copy the contents of square to a 2D array called array
    int[][] array = new int[3][3];
    array[0] = Arrays.copyOfRange(square, 0, 3);
    array[1] = Arrays.copyOfRange(square, 3, 6);
    array[2] = Arrays.copyOfRange(square, 6, 9);
     
    if (square.length == 9) {
      
      // Checks to see if the diagonals of the array are the same
      int majDi = 0;
      int minDi = 0;

      for (int i = 0; i < LENGTH; i++) {
        majDi += array[i][i];
        minDi += array[i][LENGTH - 1 - i];
      }

      if (majDi != minDi) {
        return false;
      }

      // Checks to see if each row and column of the array are equal
      for (int i = 0; i < LENGTH; i++) {
        int rowSum = 0;
        int colSum = 0;

        for (int j = 0; j < LENGTH; j++) {
          rowSum += array[i][j];
          colSum += array[j][i];
        }
        
        if (rowSum != colSum || colSum != majDi) {
          return false;
        }
      }

      return true;
    }

    return false;
  }

  // Generates a magic square recursively
  public static void genSquare(final int[] square, final int[] currentSquare, final int index) {
    for (int i = 0; i < square.length; i++) {
      if (currentSquare[i] == 0) {
        square[index] = i + 1;
        currentSquare[i] = 1;

        if (index < square.length - 1) {
          genSquare(square, currentSquare, index + 1);
        } else if (isMagic(square)) {
          printMagicSquare(square);
        }
        currentSquare[i] = 0;
      }
    }
  }

  // Formats and Outputs each magic square
  public static void printMagicSquare(final int[] outputSquare) {

    final String newLine = "\n*****";
    final String spaces = " ";

    // prints inputted array in a magic square format
    System.out.println(newLine);
    for (int count = 0; count < outputSquare.length; count++) {
      if (count == 3 || count == 6) {
        System.out.println();
        System.out.print(outputSquare[count] + spaces);
      } else {
        System.out.print(outputSquare[count] + spaces);
      }
    }
    System.out.println(newLine);
  }
}
