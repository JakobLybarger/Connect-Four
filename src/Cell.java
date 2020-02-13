/**
* The Cell class models each individual cell of the game board
*/
public class Cell {

    Seed content;
    int row, col;

    public Cell(int row, int col){
        this.row = row;
        this.col = col;
        clear(); // clear content
    }

    /** Clears the cell content to Empty. */
    public void clear(){
        content = Seed.EMPTY;
    }

    /** Paints the cell contents. */
    public void paint(){
        switch(content){
            case RED:
                System.out.print(" R ");
                break;
            case BLUE:
                System.out.print(" B ");
                break;
            case EMPTY:
                System.out.print("   ");
                break;
        }
    }
}
