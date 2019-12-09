
/**
 * Checks an array
 * @author Hiep Nguyen
 */
public class CheckArrays {
  
  /**
   * Checks the current piece
   */
  private int piece = -1;
  
  /**
   * Keeps track of the amount of consecutive pieces in a row
   */
  private int counter = 0;
  
  /**
   * Checks if adjacent spots are open
   * @param arr represents the board game
   * @param row represents the row of the clicked button
   * @param column represents the column of the clicked button
   * @return true or false depending on if there are open adjacent spots on the board
   */
  public boolean isOpen(int[][] arr, int row, int column) {
    
    //setPiece(0);
    
    int takenSpaces = checkUp(arr, row, column) + checkDown(arr, row, column) + checkLeft(arr, row, column) + checkRight(arr, row, column)
               + checkUpperRight(arr, row, column) + checkUpperLeft(arr, row, column) + checkBottomLeft(arr, row, column) + checkBottomRight(arr, row, column); // stores the amount of spaces taken, calculated
    if (row > 0 && column > 0 && row < arr.length && column < arr[row].length && takenSpaces < 8) { // if the piece isn't on an edge, if it doesn't return 8 from the helper methods, it has an open space
      return true;
    }
    else if (row == 0 || row == arr.length - 1 && takenSpaces < 5 && column != 0) { // if the piece is on an edge, it has an open space if the helper methods return a value less than 5
      return true;
    }
    else if (column == 0 || column == arr[row].length - 1 && takenSpaces < 5 && row != 0) { // if the piece is on an edge, it has an open space if the helper methods return a value less than 5
      return true;
    }
    else if ((row == 0 && column == 0) || (row == 0 && column == arr[row].length - 1) || (row == arr.length - 1 && column == 0) || (row == arr.length - 1 && column == arr[row].length - 1) && takenSpaces < 3) { // if the piece is in a corner,it has an open space if the helper methods return a value less than 3
      return true;
    }
    else {
      return false;
    }
  }
  
  
  /**
   * Checks the values in all directions of the inserted point and decides if they're are consecutive matches
   * @param arr represents the board game
   * @param row represents the row of the clicked button
   * @param column represents the column of the clicked button
   * @param wins represents the amount of buttons in a row needed for a win
   * @return the number of lines that have consecutive matches
   */
  public int numberInLine(int[][] arr, int row, int column, int wins) {
    int matchedLines = 0; // stores the number of lines that match to avoid OverLine rule
    setCounter(0); // resets the counter to count new matching lines
    if (checkUp(arr, row, column) == wins - 1) { // checks above the point for matches
      matchedLines++;
    }
    setCounter(0);
    if (checkDown(arr, row, column) == wins - 1) { // checks below the point for matches
      matchedLines++;
    }
    setCounter(0);
    if (checkRight(arr, row, column) == wins - 1) { // checks to the right of the point for matches
      matchedLines++;
    }
    setCounter(0);
    if (checkLeft(arr, row, column) == wins - 1) { // checks to the left of the point for matches
      matchedLines++;
    }
    setCounter(0);
    if (checkUpperRight(arr, row, column) == wins - 1) { // checks the upper right diagonal of the point for matches
      matchedLines++;
    }
    setCounter(0);
    if (checkUpperLeft(arr, row, column) == wins - 1) { // checks the upper left diagonal of the point for matches
      matchedLines++;
    }
    setCounter(0);
    if (checkBottomRight(arr, row, column) == wins - 1) { // checks the bottom right diagonal of the point for matches
      matchedLines++;
    }
    setCounter(0);
    if (checkBottomLeft(arr, row, column) == wins - 1) { // checks the bottom left diagonal of the point for matches
      matchedLines++;
    }
    setCounter(0);
    return matchedLines;
  }
  
  /**
   * Setter method for the piece to check
   * @param piece represents the new piece the game should search for
   */
  public void setPiece(int piece) {
    this.piece = piece;
  }
  
  /**
   * Getter method for the pieces to check
   * @return address in memory of the piece
   */
  public int getPiece() {
    return piece;
  }
  
  /**
   * Getter method for the counter
   * @return the amount counted
   */
  public int getCounter() {
    return counter;
  }
  
  /**
   * Setter method for the counter
   * @param counter stores a new amount in counter
   */
  public void setCounter(int counter) {
    this.counter = counter;
  }
  
  /**
   * Checks above the point for matches
   * @param arr represents the game board
   * @param row represents the current row of the button
   * @param column represents the current column of the button
   * @return the amount of matching pieces
   */
  public int checkUp(int[][] arr, int row, int column) {
    
    int check = getPiece();
    
    // recursively checks if each value above matches the original piece
    if(row > 0) {
      if (arr[row - 1][column] == check) {
        setCounter(getCounter() + 1);
        checkUp(arr, row - 1, column);
      }
    }
    return getCounter();
  }
  
  /**
   * Checks below the point for matches
   * @param arr represents the game board
   * @param row represents the current row of the button
   * @param column represents the current column of the button
   * @return the amount of matching pieces
   */
  public int checkDown(int[][] arr, int row, int column) {
    
    int check = getPiece();
    
    // recursively checks if each value below matches the original piece
    if(row < arr.length - 1) {
      if (arr[row + 1][column] == check) {
        setCounter(getCounter() + 1);
        checkDown(arr, row + 1, column);
      }
    }
    return getCounter();
  }
  
  /**
   * Checks left of the point for matches
   * @param arr represents the game board
   * @param row represents the current row of the button
   * @param column represents the current column of the button
   * @return the amount of matching pieces
   */
  public int checkLeft(int[][] arr, int row, int column) {
    
    int check = getPiece();
    int x;
    
    // recursively checks if each value to the left matches the original piece
    if(column > 0) {
      if(arr[row][column - 1] == check) {
        x = arr[row][column - 1];
        setCounter(getCounter() + 1);
        checkLeft(arr, row, column - 1);
      }
    }
    return getCounter();
  }
  
  /**
   * Checks right of the point for matches
   * @param arr represents the game board
   * @param row represents the current row of the button
   * @param column represents the current column of the button
   * @return the amount of matching pieces
   */
  public int checkRight(int[][] arr, int row, int column) {
    
    int check = getPiece();
    
    // recursively checks if each value to the right matches the original piece
    if(column < arr[row].length - 1) {
      if(arr[row][column + 1] == check) {
        setCounter(getCounter() + 1);
        checkRight(arr, row, column + 1);
      }
    }
    return getCounter();
  }
  
  /**
   * Checks diagonally up-right of the point for matches
   * @param arr represents the game board
   * @param row represents the current row of the button
   * @param column represents the current column of the button
   * @return the amount of matching pieces
   */
  public int checkUpperRight(int[][] arr, int row, int column) {
    
    int check = getPiece();
    
    // recursively checks if each value to the upper right matches the original piece
    if(row > 0) {
      if (column < arr[row - 1].length - 1) {
        if(arr[row - 1][column + 1] == check) {
          setCounter(getCounter() + 1);
          checkUpperRight(arr, row - 1, column + 1);
        }
      }
    }
    return getCounter();
  }
  
  /**
   * Checks diagonally up-left of the point for matches
   * @param arr represents the game board
   * @param row represents the current row of the button
   * @param column represents the current column of the button
   * @return the amount of matching pieces
   */
  public int checkUpperLeft(int[][] arr, int row, int column) {
    
    int check = getPiece();
    
    // recursively checks if each value to the upper left matches the original piece
    if(row > 0 && column > 0) {
      if(arr[row - 1][column - 1] == check) {
        setCounter(getCounter() + 1);
        checkUpperLeft(arr, row - 1, column - 1);
      }
    }
    return getCounter();
  }
  
  /**
   * Checks diagonally bottom-right of the point for matches
   * @param arr represents the game board
   * @param row represents the current row of the button
   * @param column represents the current column of the button
   * @return the amount of matching pieces
   */
  public int checkBottomRight(int[][] arr, int row, int column) {
    
    int check = getPiece();
    
    // recursively checks if each value to the bottom right matches the original piece
    if(row < arr.length - 1) {
      if (column < arr[row + 1].length - 1) {
        if(arr[row + 1][column + 1] == check) {
          setCounter(getCounter() + 1);
          checkBottomRight(arr, row + 1, column + 1);
        }
      }
    }
    return getCounter();
  }
  
  /**
   * Checks diagonally bottom-left of the point for matches
   * @param arr represents the game board
   * @param row represents the current row of the button
   * @param column represents the current column of the button
   * @return the amount of matching pieces
   */
  public int checkBottomLeft(int[][] arr, int row, int column) {
    
    int check = getPiece();
    
    // recursively checks if each value to the bottom left matches the original piece
    if(row < arr.length - 1 && column > 0) {
      if(arr[row + 1][column - 1] == check) {
        setCounter(getCounter() + 1);
        checkBottomLeft(arr, row + 1, column - 1);
      }
    }
    return getCounter();
  }
  
}