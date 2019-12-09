import org.junit.*;
import static org.junit.Assert.*;

/**
 * Tests the Gomoku game and Array Checker
 * @author Hiep Nguyen
 */
public class GomokuTest {
  
  /**
   * Tests the numberInLine method
   */
  @Test
  public void testNumberInLine() {
    // Test Many && Test Middle
    int[][] test = { {1,1,1,1,0,1,1,1,1}, {0,0,0,0,1,1,0,0,0}, {0,0,0,0,1,0,1,0,0}, {0,0,0,0,1,0,0,1,0}, {0,0,0,0,1,0,0,0,1} };
    CheckArrays c = new CheckArrays();
    c.setPiece(1);
    // Test Many
    assertEquals(4, c.numberInLine(test, 0, 4, 5));
    int[][] test4 = { {1,0,0,0,0,0,0,0,1}, {0,1,0,0,0,0,0,1,0}, {0,0,1,0,0,0,1,0,0}, {0,0,0,1,0,1,0,0,0}, {0,0,0,0,0,0,0,0,0} };
    assertEquals(2, c.numberInLine(test4, 4, 4, 5));
    // Test 0 && Test First && Test Last
    int[][] test2 = { {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0} };
    assertEquals(0, c.numberInLine(test2, 0, 0, 5));
    assertEquals(0, c.numberInLine(test2, 0, 8, 5));
    assertEquals(0, c.numberInLine(test2, 4, 0, 5));
    assertEquals(0, c.numberInLine(test2, 4, 8, 5));
    // Test 1
    int[][] test3 = { {0,0,0,0,0,0,0,0,0}, {0,0,0,0,1,0,0,0,0}, {0,0,0,0,1,0,0,0,0}, {0,0,0,0,1,0,0,0,0}, {0,0,0,0,1,0,0,0,0} };
    assertEquals(1, c.numberInLine(test3, 0, 4, 5));
  }
  
  @Test
  public void testIsOpen() {
    int[][] test = { {0,1,0,0,0,0,0,1,0}, {1,0,0,1,1,1,0,1,1}, {0,0,0,1,0,1,0,0,0}, {0,0,0,1,1,1,0,0,0}, {0,0,0,0,0,0,0,0,0} };
    CheckArrays c = new CheckArrays();
    c.setPiece(0);
    // Test Middle
    assertEquals(false, c.isOpen(test, 2, 4));
    
    // Test First && Test Last
    assertEquals(true, c.isOpen(test, 0, 0));
    assertEquals(false, c.isOpen(test, 0, 8));
    assertEquals(true, c.isOpen(test, 4, 0));
    assertEquals(true, c.isOpen(test, 4, 8));
    
    // Test 0 && Test 1
    int[][] test2 = { {0,1,0,0,0,0,0,1,0}, {1,1,0,1,1,1,0,0,0}, {0,0,0,1,0,1,0,0,0}, {0,0,0,1,1,1,0,0,0}, {0,0,0,0,0,0,0,0,0} };
    assertEquals(false, c.isOpen(test2, 0, 0));
    assertEquals(true, c.isOpen(test2, 0, 8));
    
    // Test Many
    int[][] test3 = { {1,0,1,0,0,0,0,1,0}, {1,1,1,1,1,1,0,0,0}, {0,0,0,1,0,1,0,0,0}, {0,0,0,1,1,1,0,0,0}, {0,0,0,0,0,0,0,0,0} };
    assertEquals(false, c.isOpen(test2, 0, 1));
    assertEquals(false, c.isOpen(test2, 2, 4));
    assertEquals(true, c.isOpen(test2, 4, 8));
  }
  
}