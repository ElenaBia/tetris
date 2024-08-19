package no.uib.inf101.tetris.TetrisModel;

import javax.swing.CellEditor;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;

/**
 * Objekter i denne klassen representerer et tetrisbrett.
 * TetrisBoard-objekter derfor er et Grid<Character>-objekter,
 *  og arver alle metodene i Grid-klassen.
 */
public class TetrisBoard extends Grid<Character> {

    //kontruktør
    public TetrisBoard(int row,int col){
        //gjør et call til superkonstruktøren fra Grid
        super(row, col,'-');
        //'-' representerer at Tetris-brettet er tomt i den gitte posisjonen.
    }
    public String prettyString(){
            int row =this.rows();
            int col = this.cols();
            String str ="";
            for (int i = 0; i < row; i++) {
                for (int y = 0; y < col; y++) {
                    CellPosition pos = new CellPosition(i, y);
                    Character val = this.get(pos);
                    str+=val.toString();
                }if (i!=row-1){// Legger til linjeskift
                    str+="\n";}
            }
            return str;
    }
/**
 * metode som fjerner rader
 * @return antall fjernet rader
 */
    public int removeFullRows() {
        int removedRows = 0;
        int bottomRow = this.rows() - 1;
        int currentRow = bottomRow;
        //så lenge vi er på brettet
        while (currentRow >= 0) {
            //sjekker om raden er full
            if (isRowFull(currentRow)) { 
                //kopierer radene oppover
                for (int row = currentRow; row > 0; row--) {
                    copyRow(row - 1, row);
                }
                // Setter blank top rad
                setRowToValue(0, '-');
                removedRows++;
            } else {
                // Move to the next row
                currentRow--;
            }
        }
        return removedRows;
    }

    public boolean isRowFull(int row) {
        for (int col = 0; col < this.cols(); col++) {
            if (this.get(new CellPosition(row, col)) == '-') {
                return false;
            }
        }
        return true;
    }
    public void setRowToValue(int row, Character val){
        for(int col= 0; col < this.cols(); col++){
            this.set(new CellPosition(row,col), val);
        }
    }
    /**
     * Kopierer alle verdiene fra én rad inn i en annen.
     * @param sourceRow      Raden som skal kopieres fra.
     * @param destinationRow Raden som skal kopieres til.
     */
    public void copyRow(int sourceRow, int destinationRow) {
        for (int col = 0; col < this.cols(); col++) {
            CellPosition previousPosition = new CellPosition(destinationRow, col);
            CellPosition initialPosition = new CellPosition(sourceRow, col);
            char value = this.get(initialPosition);
            this.set(previousPosition, value);
        }
    }

  
}


