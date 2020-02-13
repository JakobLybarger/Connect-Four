import java.util.Scanner;

public class GameMain {

    private Board board;
    private GameState currentState;
    private Seed currentPlayer;

    private static Scanner input = new Scanner(System.in);

    private GameMain(){
        board = new Board();

        board.paint();

        // Initialize the game-board and current status
        initGame();

        // Play game once. Players X and O move alternately
        do{
            playerMove(currentPlayer);
            board.paint();
            updateGame(currentPlayer);

            // print message if game-over
            if(currentState == GameState.B_WIN){
                System.out.println("'Blue' won! Bye!");
            }
            else if(currentState == GameState.R_WIN){
                System.out.println("'Red' won! Bye!");
            }
            else if(currentState == GameState.DRAW){
                System.out.println("It's a Draw! Bye!");
            }

            // Switch player
            currentPlayer = (currentPlayer == Seed.BLUE) ? Seed.RED : Seed.BLUE;
        }while(currentState == GameState.PLAYING);
    }

    /** Initialize the board contents and the current status. */
    private void initGame(){
        board.init();
        currentPlayer = Seed.BLUE;
        currentState = GameState.PLAYING;
    }

    /** The player with "theSeed" makes one move, with input validation
     *  Update Cell's content, Board's currentRow and currentCol
     *  @param theSeed The current player
     */
    private void playerMove(Seed theSeed){
        boolean validInput = false; // for validating input

        do{
            if(theSeed == Seed.BLUE){
                System.out.println("Player 'Blue', enter your move (col[1-7]): ");
            }
            else{
                System.out.println("Player 'Red', enter your move (col[1-7]): ");
            }

            int row;
            int col = input.nextInt() - 1;

            // have player choose column for move and row farthest down will become occupied
            if (col >= 0 && col < Board.COLS){
                for(row = Board.ROWS - 1; row >= 0; row--) {
                    if (board.cells[row][col].content == Seed.EMPTY) {
                        board.cells[row][col].content = theSeed;
                        board.currentRow = row;
                        board.currentCol = col;
                        validInput = true;
                        break;
                    }
                }
            }
            else{
                System.out.println("That move was not valid. Try again...");
            }
        }while(!validInput);
    }

    /** Update the currentState after the player with "theSeed" has moved
     *  @param theSeed the current player*/
    private void updateGame(Seed theSeed){
        if(board.hasWon(theSeed)){
            currentState = (theSeed == Seed.BLUE) ? GameState.B_WIN : GameState.R_WIN;
        }
        else if(board.isDraw()){
            currentState = GameState.DRAW;
        }
        // Otherwise, no change to current state (still GameState.PLAYING)
    }

    // The entry main() method
    public static void main(String[] args){
        String playAgain;
        do {
            new GameMain();
            System.out.println();
            System.out.println("Enter 'Y' or 'y' to play another game or anything else to exit: ");
            playAgain = input.next();
        }while(playAgain.startsWith("Y") || playAgain.startsWith("y"));
    }
}
