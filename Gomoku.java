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

/**
 * Creates the game Gomoku
 */
public class Gomoku extends Application {
  
  /**
   * Stores the current gameBoard and what pieces have been initialized
   */
  private int[][] gameBoard;
  
  /**
   * Stores the amount of rows in the game
   */
  private int rows;
  
  /**
   * Stores the amount of columns in the game
   */
  private int columns;
  
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
   * Getter method for the amount of columns
   * @return the amount of columns
   */
  public int getColumns() {
    return columns;
  }
  
  /** 
   * Overrides the start method of Application to create the GUI with one button in the center of the main window.
   * @param primaryStage the JavaFX main window
   */
  public void start(Stage primaryStage) {
    Button[][] buttonBoard = new Button[getRows()][getColumns()];
    
    GridPane gridPane = new GridPane();
    
    Scene scene = new Scene(gridPane);
    
    primaryStage.setTitle("Gomoku");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  /**
   * The method the launch the program
   * @param args The command line arguments that will be passed onto the JavaFX application
   */
  public static void main(String[] args) {
    Application.launch(args);
  }
  
}