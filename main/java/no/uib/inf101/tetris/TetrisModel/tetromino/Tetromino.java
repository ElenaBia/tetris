package no.uib.inf101.tetris.TetrisModel.tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.TetrisModel.GameState;

public class Tetromino implements Iterable<GridCell<Character>>{
    
    //konstanter 
    private final char famSymbol;
    public final CellPosition tetrominoUpperLeftCornerPosOnGrid;
    private final boolean[][] shape; //lager en array
    //konstruktør
    private Tetromino(char familySymbol,  boolean[][] shapeTetromino,  CellPosition position){
        this.famSymbol =familySymbol;
        this.shape = shapeTetromino;
        this.tetrominoUpperLeftCornerPosOnGrid=position;
    }
    /**
     * Statisk metode for å lage nye Tetromino objekter.
     * @param char
     * @return Tetromino - objekt
     */
    static Tetromino newTetromino(char character){
        //standard start posisjon
        CellPosition startPosition = new CellPosition(0, 0);
        Tetromino tetromino;
        //sjekker og lager fasongen
       switch(character) {
            case 'T':
            boolean[][] shapeT = new boolean[][] {
                { false,  false, false },
                { true, true, true },
                { false, true, false }
            };
            tetromino= new Tetromino(character, shapeT,startPosition);
            break;
            case 'S':
            boolean[][] shapeS = new boolean[][] {
                { false,  false, false },
                { false, true, true },
                { true, true, false }
            };
            tetromino= new Tetromino(character, shapeS,startPosition);
            break;
            case 'L':
            boolean[][] shapeL = new boolean[][] {
                { false,  false, false },
                { true, true, true },
                { true, false, false }
            };
            tetromino=  new Tetromino(character, shapeL,startPosition);
            break;
            case 'J':
            boolean[][] shapeJ = new boolean[][] {
                { false,  false, false },
                { true, true, true },
                { false, false, true }
            };
            tetromino=  new Tetromino(character, shapeJ,startPosition);
            break;
            case 'Z':
            boolean[][] shapeZ = new boolean[][] {
                { false,  false, false },
                { true, true, false },
                { false, true, true }
            };
            tetromino= new Tetromino(character, shapeZ,startPosition);
            break;
            case 'I':
            boolean[][] shapeI = new boolean[][] {
                { false, false,false,false},
                { true,true,true,true },
                { false,false,false,false},
                { false,false,false,false}
            };
            tetromino=  new Tetromino(character, shapeI,startPosition);
            break;
            case 'O':
            boolean[][] shapeO = new boolean[][] {
                { false, false,false,false},
                { false, true, true ,false},
                { false, true, true ,false},
                { false, false,false,false}
            };
            tetromino=  new Tetromino(character, shapeO,startPosition);
            break;
            default:
                throw new IllegalArgumentException(
                "Not valid family symbol");
        }
         // Shift the Tetromino to the top center of the grid dimension
        return tetromino;
    }   
/**
     * Metoden oppretter en kopi av objektet, men posisjonen er annerledes pga. forflyttning.
     * @param deltaRow
     * @param deltaCol
     * @return Tetromino obj
     */
     public Tetromino shiftedBy(int deltaRow, int deltaCol){
        //finner ny posisjon
        CellPosition newPosition = new CellPosition(tetrominoUpperLeftCornerPosOnGrid.row()+deltaRow, tetrominoUpperLeftCornerPosOnGrid.col()+deltaCol);
        //lager ny tetromino med den nye posisjonen
        Tetromino movedTetromino = new Tetromino(famSymbol, shape, newPosition);
        return movedTetromino;
    }
    /**
     * Metoden opprette en flyttet kopi som er sentrert rundt midterste eller de to midterste kolonnene i et rutenett
     *  med de gitte dimensjonene. Slik at øverste reelle rute i brikken kommer på rad 0.
     * @param gd - GridDimension
     * @return Tetromino objekt
     */
    public Tetromino shiftedToTopCenterOf(GridDimension gd) {
        int centerCol = gd.cols() / 2;
        int startCol = centerCol - (shape[0].length / 2);
        CellPosition centeredPosition = new CellPosition(-1, startCol);
        Tetromino centeredTetromino = new Tetromino(famSymbol, shape, centeredPosition);
        return centeredTetromino;
    }
 
    @Override
    public Iterator<GridCell<Character>> iterator() {
        //liste som skal inneholde cellene til en tetromino
        ArrayList<GridCell<Character>> validShapePosition = new ArrayList<>();
        //finner første rad tetrominoet er på
        int rowBoardPlacement =tetrominoUpperLeftCornerPosOnGrid.row();
        //looper gjennom fasongens 2d array
        for( boolean[] r:shape){
            //finner første column tetrominoet er på
            int columnBoardPlace =tetrominoUpperLeftCornerPosOnGrid.col();
            for( boolean c: r){
                if(c==true){
                    //finner posisjon på brettet
                    CellPosition brickPosition = new CellPosition(rowBoardPlacement, columnBoardPlace);
                    //lager celle ut av den nye posisjonen
                    GridCell<Character> brick =new GridCell<Character>(brickPosition,famSymbol);
                    validShapePosition.add(brick);
                }
                columnBoardPlace++;
            }
            rowBoardPlacement++;
        }
        return validShapePosition.iterator();
    }

 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Tetromino))
            return false;
        Tetromino other = (Tetromino) obj;
        return famSymbol == other.famSymbol && Arrays.deepEquals(shape, other.shape)
                && Objects.equals(tetrominoUpperLeftCornerPosOnGrid, other.tetrominoUpperLeftCornerPosOnGrid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(famSymbol, Arrays.deepHashCode(shape), tetrominoUpperLeftCornerPosOnGrid);
    }


    /**
     * @return 
     * @return rotated tetromino 
     */
    public Tetromino rotate(){
        int rows = shape.length;
        int cols = shape[0].length;
        boolean[][] rotatedShape = new boolean[cols][rows]; 
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Calculate the corresponding position in the original shape after rotation
                int originalRow = cols - 1 - c;
                int originalCol = r; 
                // Copy the value from the original shape to the rotated shape
                rotatedShape[r][c] = shape[originalRow][originalCol];
            }
        }
        return new Tetromino(famSymbol,rotatedShape,tetrominoUpperLeftCornerPosOnGrid);
        
    }
  

}
