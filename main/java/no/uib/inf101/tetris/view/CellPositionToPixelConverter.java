package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;

import java.awt.geom.Rectangle2D;





public class CellPositionToPixelConverter{
  //instans variabler
  Rectangle2D box;
  GridDimension gd;
  double margin;

  //constructor
  public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin){
    this.box=box;
    this.gd=gd;
    this.margin=margin;
  }

  /**
   * vet hvordan regne ut posisjonen til én rute i rutenettet.
   * Oversetter koordinater i rutenettet til et rektangel med posisjon og størrelse 
   * beskrevet som piksler til bruk på et lerret.
   * @param cp type: CellPosition
  * @return en rektangel for cellen
   */

  public Rectangle2D getBoundsForCell(CellPosition cp) {
    //finner rad og kolonne
    double col = cp.col(); 
    double row = cp.row(); 
    //regner ut hvor brei cellene skal være 
    double cellWidth= (box.getWidth()-margin*(gd.cols()+1))/gd.cols();
    //x koordinaten til cellen
    double cellX =box.getX() + margin*(col+1)+((col)*cellWidth);
    //finner høyden
    double cellHeight = (box.getHeight()-margin*(gd.rows()+1))/gd.rows();
    //finner y kooridnatene
    double cellY = box.getY()+margin*(row+1)+((row)*cellHeight);
    Rectangle2D rect =new Rectangle2D.Double(cellX, cellY, cellWidth, cellHeight);
    //returnerer en rektangel

    return rect;

  }



  

}
