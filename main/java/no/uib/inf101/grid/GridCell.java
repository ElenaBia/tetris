package no.uib.inf101.grid;

/**
 * Er en type som definerer celler i griden. 
    @param pos
    @param value
*/
public record GridCell<E>(CellPosition pos, E value) {
    
}
