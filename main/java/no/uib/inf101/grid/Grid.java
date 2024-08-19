package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/* 

- Begynn med å opprette alle metoder som kreves av grensesnittet, men som i første 
omgang returner "dummy" verdier (e.g. 0 eller `null` alt ettersom). Dette kan gjøres 
ved «quick fix» eller lignende.
- Se på javadoc-kommentarene i IGrid og se på hvordan testene i `GridTest` er skrevet
 for å se hvilke metoder og konstruktører du trenger å implementere.
*/

public class Grid<E> implements IGrid<E>{

// Creating the instance variables
  int row; 
  int col;
  //list for the grid
  List<List<E>> grid;


  // Create a class constructor for the Main class
  public Grid(int row, int col, E value) {
    this.row=row;
    this.col=col;
    //now we create the list and assign it to the grid
    this.grid = new ArrayList<>();

    //creating the grid
    for(int i=0;i<row;i++){
      List<E> colList = new ArrayList<>();
      for (int x=0;x<col;x++){
        colList.add(value);
      }
      grid.add(colList);
    }
    
  } 

  //konstruktør
  public Grid(int row, int col) {
    this(row, col, null);
  } 

  @Override
  public int rows() {
      return row;
  }

  @Override
  public int cols() {
      return col;
  }

  @Override
  public Iterator<GridCell<E>> iterator() {
    //lage en forløkke går gjennom alle grid cellsene
    //de skal ha gridcell objekter og så skal man returnere den listen
    ArrayList<GridCell<E>> cells = new ArrayList<>();
    int r_index =0;
    //går gjennom rows
    for (List<E> r:grid){ 
      //går gjennom cols
      int c_index =0;
      for(E c: r){
        CellPosition pos = new CellPosition(r_index, c_index);
        E val = this.get(pos);
        //oppretter ny objekt
        GridCell<E> gridCell = new GridCell<E>(pos,  val);
        //legger til cellen
        cells.add(gridCell);
        c_index++;
      }
      r_index++;
    }
    return cells.iterator();
  }

  @Override
  public void set(CellPosition pos, E value) {
      //pakke det ut
      int col = pos.col();
      int row = pos.row();
      //sette verdien i 
      grid.get(row).set(col, value);    
  }


  @Override
  public E get(CellPosition pos) {
      //pakke det ut
      int col = pos.col();
      int row = pos.row();
      //gets value
      E value = grid.get(row).get(col);
      return value;
  }

  @Override
  public boolean positionIsOnGrid(CellPosition pos) {
      //pakke det ut
      int col = pos.col();
      int row = pos.row();
      return (row >=0 && row < grid.size()) && (col >=0 &&  col< grid.get(0).size());
  }
  
}
