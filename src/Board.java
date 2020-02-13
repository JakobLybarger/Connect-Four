/** Simulates a connect four board. */
public class Board {
    // Data Fields
    // board dimensions
    public static final int ROWS = 6;
    public static final int COLS = 7;

    Cell[][] cells; // board of rows by cols of Cell instances
    int currentRow, currentCol;

    // Constructor
    /** Initializes the board and all the cells within. */
    public Board(){
        cells = new Cell[ROWS][COLS];
        for(int row = 0; row < ROWS; ++row){
            for(int col = 0; col < COLS; ++col){
                cells[row][col] = new Cell(row, col);
            }
        }
    }

    /** Initialize the contents of the game board. */
    public void init(){
        for(int row = 0; row < ROWS; ++row){
            for(int col = 0; col < COLS; ++col){
                cells[row][col].clear();
            }
        }
    }

    /** Check to see if the game is a draw
     *  @return true if it is a draw
     */
    public boolean isDraw(){
        for(int row  = 0; row < ROWS; ++row){
            for(int col = 0; col < COLS; ++col){
                if(cells[row][col].content == Seed.EMPTY){
                    return false;
                }
            }
        }
        return true;
    }

    /** Check to see if the player with "theSeed" has won after placing
     *  at current position
     *  @param theSeed the player
     *  @return true if the player with "theSeed" has won
     *  @throws ArrayIndexOutOfBoundsException
     */
    public boolean hasWon(Seed theSeed){
        int count = 0;
        //  Check for 4-in-a-line on rows
        for(int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (cells[row][col].content == theSeed) {
                    ++count;
                    if (count == 4) {
                        return true;
                    }
                }
                else {
                    count = 0;
                }
            }
        }

        //  Check for 4-in-a-line on columns
        for(int col = 0; col < COLS; ++col) {
            for (int row = 0; row < ROWS; ++row) {
                if (cells[row][col].content == theSeed) {
                    ++count;
                    if (count == 4) {
                        return true;
                    }
                }
                else{
                    count = 0;
                }
            }
        }

        // first algorithm for diagonals; Top-left to bottom-right
        for(int row = 0; row < ROWS - 3; row++){
            for(int col = 0; col < COLS; col++){
                try {
                    if (cells[row][col].content == theSeed && cells[row + 1][col + 1].content == theSeed && cells[row + 2][col + 2].content == theSeed && cells[row + 3][col + 3].content == theSeed) {
                        return true;
                    }
                }
                catch(ArrayIndexOutOfBoundsException ignored){
                }
            }
        }


        // second algorithm for diagonals; Top-right to bottom-left
        for(int col = COLS; col > COLS - 5; col--){
            for(int row = 0; row < ROWS; row++){
                try{
                    if(cells[row][col].content == theSeed && cells[row+1][col-1].content == theSeed && cells[row+2][col-2].content == theSeed && cells[row+3][col-3].content == theSeed){
                        return true;
                    }
                }
                catch(ArrayIndexOutOfBoundsException ignored){
                }
            }
        }

        return false;
    }

    /** Prints out the board. */
    public void paint(){
        for(int row = 0; row < ROWS; ++row){
            for(int col = 0; col < COLS; ++col){
                cells[row][col].paint();
                if(col < COLS - 1){
                    System.out.print("|");
                }
            }
            System.out.println();
            if(row < ROWS - 1){
                System.out.println("---------------------------");
            }
        }
    }
}
