import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.shape.Circle;
import java.lang.NumberFormatException;

/**
 * Creates the game Gomoku
 * @author Hiep Nguyen
 */
public class Gomoku extends Application {
  
  /**
   * Stores the current gameBoard and what pieces have been initialized
   */
  private int[][] gameBoard;
  
  /**
   * Stores the buttons for the board
   */
  private Button[][] buttonBoard;
  
  /**
   * Stores the amount of rows in the game
   */
  private int rows;
  
  /**
   * Stores the amount of columns in the game
   */
  private int columns;
  
  /**
   * Stores the color of the next piece
   */
  private Color color = Color.BLACK;
  
  /**
   * Stores the amount of pieces necessary to win the game
   */
  private int win;
  
  
  /**
   * Getter method for the amount of rows
   * @return the amount of rows
   */
  public int getRows() {
    return rows;
  }
  
  /**
   * Setter method for the amount of rows
   * @param rows represents the amount of rows in the board game
   */
  public void setRows(int rows) {
    this.rows = rows;
  }
  
  /**
   * Getter method for the amount of columns
   * @return the amount of columns
   */
  public int getColumns() {
    return columns;
  }
  
  /**
   * Setter method for the amount of columns
   * @param columns represents the amount of columns in the board game
   */
  public void setColumns(int columns) {
    this.columns = columns;
  }
  
  /**
   * Retrieve the address in memory of the current color
   * @return the current color
   */
  public Color getColor() {
    return color;
  }
  
  /**
   * Change the current color
   * @param color changes the current colors to the inscribed color
   */
  public void setColor(Color color) {
    this.color = color;
  }
  
  /**
   * Initializes the int array representing the game and button board
   */
  
  public void initBoards() {
    gameBoard = new int[getRows()][getColumns()];
    buttonBoard = new Button[getRows()][getColumns()];
  }
  
  /**
   * Getter method for the int board
   * @return the array containing the values on the board
   */
  public int[][] getArr() {
    return gameBoard;
  }
  
  /**
   * Getter method for the button board
   * @return the array containing the buttons on the board
   */
  public Button[][] getButtonArr() {
    return buttonBoard;
  }
  
  /**
   * Getter method for the wins
   * @return the amount of pieces necessary to win the game
   */
  public int getWin() {
    return win;
  }
  
  /**
   * When a winner has been decided, the buttons are disabled
   */
  public void disableArray() {
    for(int i = 0; i < getRows(); i++) {
      for(int j = 0; j < getColumns(); j++) {
        getButtonArr()[i][j].setDisable(true);
      }
    }
  }
  
  /**
   * Setter method for the amount of wins
   * @param win represents the amount of pieces needed to win the game
   */
  public void setWin(int win) {
    this.win = win;
  }
  
  /** 
   * Overrides the start method of Application to create the GUI with one button in the center of the main window.
   * @param primaryStage the JavaFX main window
   */
  public void start(Stage primaryStage) {
    
    try {
    if (getParameters().getRaw().size() > 2) { // if the command line arguments are greater than 2, read each value into the win, rows, and columns
      setWin(Integer.parseInt(getParameters().getRaw().get(0)));
      setRows(Integer.parseInt(getParameters().getRaw().get(1)));
      setColumns(Integer.parseInt(getParameters().getRaw().get(2)));
    }
    else if (getParameters().getRaw().size() == 2) { // if the command line arguments are exactly 2, set win to 5 and set rows and columns to the arguments
      setWin(5);
      setRows(Integer.parseInt(getParameters().getRaw().get(0)));
      setColumns(Integer.parseInt(getParameters().getRaw().get(1)));
    }
    else if (getParameters().getRaw().size() == 1) { // if the command line arguments are exactly 1, set win to the first argument and set rows and columns to a default of 19
      setWin(Integer.parseInt(getParameters().getRaw().get(0)));
      setRows(19);
      setColumns(19);
    }
    else { // if there are no command line arguments, the wins, rows, and columns are set to default values of 5, 19, and 19 respectively
      setWin(5);
      setRows(19);
      setColumns(19);
    }
    
    initBoards(); // initialize the size of the arrays
    
    GridPane gridPane = new GridPane();
    // runs through the rows of the array
    for(int i = 0; i < buttonBoard.length; i++) {
      // runs through the columns of the array
      // initializes each button in the array and adds them to the grid pane
      for(int j = 0; j < buttonBoard[i].length; j++) {
        
        buttonBoard[i][j] = new Button();
        buttonBoard[i][j].setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(0), new Insets(1,1,1,1))));
        buttonBoard[i][j].setMaxSize(44, 44);
        buttonBoard[i][j].setMinSize(44, 44);
        buttonBoard[i][j].setOnAction(new buttonClick());
        
        gridPane.add(buttonBoard[i][j], i, j);
      }
    }
    
    Scene scene = new Scene(gridPane); // adds the gridPane to the scene
    
    primaryStage.setTitle("Gomoku"); // name of the game
    primaryStage.setScene(scene); // sets the appropriate scene
    primaryStage.show(); // shows the scene
  }
    catch (NumberFormatException e) { // Catches if the inputted values are unparseable Strings
      System.out.println("Input appropriate integer values into the command line");
    }
  }
  
  private class buttonClick implements EventHandler<ActionEvent> {
    
    
    public void handle(ActionEvent event) {
      Button button = (Button)event.getSource(); // stores the button that was pressed
      CheckArrays f = new CheckArrays(); // used to check arrays
      int row = GridPane.getRowIndex(button); // stores the row of the pressed button
      int column = GridPane.getColumnIndex(button); // stores the column of the pressed button
      
      if (getColor().equals(Color.BLACK)) {
        getArr()[row][column] = 1;
        f.setPiece(1); // piece that is being searched for matches
        if(getWin() == 0 || getWin() == 1) {
          disableArray();
          System.out.println("Try putting realistc winning values sir");
        }
        if(f.numberInLine(getArr(), row, column, getWin()) == 1) { // Declares black the winner and disables the board
          f.isOpen(getArr(), row, column);
          button.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(0), new Insets(1,1,1,1)), new BackgroundFill(getColor(), new CornerRadii(18), new Insets(4,4,4,4))));
          System.out.println("Black Wins!!!");
          disableArray();
        }
        else if(f.numberInLine(getArr(), row, column, getWin()) > 1 || f.numberInLine(getArr(), row, column, getWin() - 1) > 1) { // Prevents breaking the OverLine rule
          ;
        }
        else { // Activate the button
          button.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(0), new Insets(1,1,1,1)), new BackgroundFill(getColor(), new CornerRadii(18), new Insets(4,4,4,4))));
          getArr()[row][column] = 1;
          setColor(Color.WHITE);
          button.setDisable(true); // Prevents the button from being pressed again
        }
      }
      else {
        f.setPiece(-1); // Piece that is being searched for matches
        if(f.numberInLine(getArr(), row, column, getWin()) == 1) { // Declares white the winner and disables the board
          f.isOpen(getArr(), row, column);
          button.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(0), new Insets(1,1,1,1)), new BackgroundFill(getColor(), new CornerRadii(18), new Insets(4,4,4,4))));
          System.out.println("White Wins!!!");
          disableArray();
        }
        else if(f.numberInLine(getArr(), row, column, getWin()) > 1 || f.numberInLine(getArr(), row, column, getWin() - 1) > 1) { // Prevents breaking the OverLine rule
          ;
        }
        else { // Activate the button
          button.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(0), new Insets(1,1,1,1)), new BackgroundFill(getColor(), new CornerRadii(18), new Insets(4,4,4,4))));
          getArr()[row][column] = -1;
          setColor(Color.BLACK);
          button.setDisable(true); // Prevents the button from being pressed again
        }
      }
      
      button.setOpacity(100); // Stops the button from "graying out" when disabled
    }
  }
  
  /**
   * The method the launch the program
   * @param args The command line arguments that will be passed onto the JavaFX application
   */
  public static void main(String[] args) {
    Application.launch(args);
  }
  
}