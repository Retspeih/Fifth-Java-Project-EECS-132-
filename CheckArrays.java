
/**
 * Checks an array
 */
public class CheckArrays {
  
  private int piece = -1;
  private int counter = 0;
  
  /**
   * Checks if a position on an array has an adjacent element open
   */
  public boolean isOpen(int[][] arr, int row, int column) {
    int check = 0;
    if (row > 0 && column > 0 && row < arr.length && column < arr[row].length) {
      if (check == arr[row - 1][column - 1] || check == arr[row][column - 1] || check == arr[row + 1][column - 1] ||
          check == arr[row][column - 1] || check == arr[row][column + 1] || check == arr[row + 1][column - 1] ||
          check == arr[row + 1][column] || check == arr[row + 1][column + 1]) {
        System.out.println("true");
        return true;
      }
    }
    System.out.println("false");
    return false;
  }
  
  public int numberInLine(int[][] arr, int row, int column, int wins) {
    int matchedLines = 0;
    setCounter(0);
    if (checkUp(arr, row, column) == wins - 1) {
      matchedLines++;
      setCounter(0);
    }
    if (checkDown(arr, row, column) == wins - 1) {
      matchedLines++;
      setCounter(0);
    }
    /*if (checkRight(arr, row, column) == wins - 1) {
     matchedLines++;
     setCounter(0);
     }
     if (checkLeft(arr, row, column) == wins - 1) {
     matchedLines++;
     }*/
    System.out.println(matchedLines);
    return matchedLines;
  }
  
  public void setPiece(int piece) {
    this.piece = piece;
  }
  
  public int getPiece() {
    return piece;
  }
  
  public int getCounter() {
    return counter;
  }
  
  public void setCounter(int counter) {
    this.counter = counter;
  }
  
  public int checkUp(int[][] arr, int row, int column) {
    
    int check = getPiece();
    
    if(row > -1) {
      if (arr[row - 1][column] == check) {
        setCounter(getCounter() + 1);
        checkUp(arr, row - 1, column);
      }
    }
    return getCounter();
  }
  
  public int checkDown(int[][] arr, int row, int column) {
    
    int check = getPiece();
    
    if(row < arr.length) {
      if (arr[row + 1][column] == check) {
        setCounter(getCounter() + 1);
        checkDown(arr, row + 1, column);
      }
    }
    return getCounter();
  }
  
  public int checkLeft(int[][] arr, int row, int column) {
    
    int check = getPiece();
    
    if(column > -1 && arr[row][column] == check) {
      setCounter(getCounter() + 1);
      checkLeft(arr, row, column - 1);
    }
    return getCounter();
  }
  
  public int checkRight(int[][] arr, int row, int column) {
    
    int check = getPiece();
    
    if(column < arr[row].length && arr[row][column] == check) {
      setCounter(getCounter() + 1);
      checkRight(arr, row, column + 1);
    }
    return getCounter();
  }
  
  
}