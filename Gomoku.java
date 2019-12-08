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

/**
 * Creates the game Gomoku
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
   * Constructor that initializes the dimensions of the game
   */
  /*public Gomoku(int rows, int columns) {
   this.rows = rows;
   this.columns = columns;
   gameBoard = new int[rows][columns];
   }*/
  
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
   * Overrides the start method of Application to create the GUI with one button in the center of the main window.
   * @param primaryStage the JavaFX main window
   */
  public void start(Stage primaryStage) {
    setRows(19);
    setColumns(19);
    
    initBoards();
    
    GridPane gridPane = new GridPane();
    
    for(int i = 0; i < buttonBoard.length; i++) {
      for(int j = 0; j < buttonBoard[i].length; j++) {
        
        buttonBoard[i][j] = new Button();
        buttonBoard[i][j].setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(0), new Insets(1,1,1,1))));
        buttonBoard[i][j].setMaxSize(35, 35);
        buttonBoard[i][j].setMinSize(35, 35);
        buttonBoard[i][j].setOnAction(new buttonClick());
        
        gridPane.add(buttonBoard[i][j], i, j);
      }
    }
    
    Scene scene = new Scene(gridPane);
    
    primaryStage.setTitle("Gomoku");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  /**
   * Reads the array as inputs come in
   */
  public int[][] readArr() {
    for(int i = 0; i < gameBoard.length; i++) {
      for(int j = 0; j < gameBoard[i].length; j++) {
        System.out.print(gameBoard[i][j] + " ");
      }
      System.out.println();
    }
    return gameBoard;
  }
  
  private class buttonClick implements EventHandler<ActionEvent> {
    
    
    public void handle(ActionEvent event) {
      Button button = (Button)event.getSource();
      int row = GridPane.getRowIndex(button);
      int column = GridPane.getColumnIndex(button);
      button.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(0), new Insets(1,1,1,1)), new BackgroundFill(color, new CornerRadii(18), new Insets(4,4,4,4))));
      if (getColor().equals(Color.BLACK)) {
        gameBoard[row][column] = 1;
        setColor(Color.WHITE);
      }
      else {
        gameBoard[row][column] = -1;
        setColor(Color.BLACK);
      }
      button.setDisable(true);
      button.setOpacity(100);
      CheckArrays c = new CheckArrays();
      c.numberInLine(getArr(), row, column, 5);
      //readArr();
    }
  }
  
  /**
   * The method the launch the program
   * @param args The command line arguments that will be passed onto the JavaFX application
   */
  public static void main(String[] args) {
    /*if (args.length > 2) {
     Gomoku newGame = new Gomoku();
     newGame.setRows(Integer.parseInt(args[1]));
     newGame.setColumns(Integer.parseInt(args[2]));
     }
     else {
     Gomoku newGame = new Gomoku();
     newGame.setRows(19);
     newGame.setColumns(19);
     }*/
    Application.launch();
  }
  
}