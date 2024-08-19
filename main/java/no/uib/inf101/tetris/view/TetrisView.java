package no.uib.inf101.tetris.view;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JPanel;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.TetrisModel.GameState;
import no.uib.inf101.tetris.view.DefaultColorTheme;

/**
 * Utvider JPanel.
 * For å tegne brettet trenger TetrisView å vite:
    <ul> 
    <li>hvor mange rader det er på brettet</li>
    <li> hvor mange kolonner det er på brettet</li>
    <li>for hver flis, hvilken farge den skal ha</li> 
 */
public class TetrisView extends JPanel{
  
  ViewableTetrisModel TetrisModel;
  ColorTheme color;
  int score;
  int level;
  //tegner først en rect for framen
  final int OUTERMARGIN = 20;
  final int INNERMARGIN =2;


  // Konstruktør 
  public TetrisView(ViewableTetrisModel vTetrisModel) {
      this.color = new DefaultColorTheme();
      this.setBackground(color.getBackgroundColor());
      this.TetrisModel = vTetrisModel;
      this.setFocusable(true);
      this.setPreferredSize(new Dimension(400, 500));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    drawGame(g2d);
    
    if (TetrisModel.getGameState()==GameState.WELCOME_SCREEN) {
      drawWelcomeScreen(g2d);
    }
    if (TetrisModel.getGameState()==GameState.GAME_OVER) {
        drawGameOver(g);
    }

    // Viser score
    drawScore(g2d);
    //viser level
    drawLevel(g2d);
   
  }

/**
 * metode som tegner scoren i hjørnet
 * @param g
 */
private void drawScore(Graphics2D g){
  int score = TetrisModel.getScore();
  String text = "Score: "+ score;
  g.setFont(new Font("Arial", Font.BOLD, 30));
  
  // REgenr posisjonen
  FontMetrics fontMetrics = g.getFontMetrics();
  int textWidth = fontMetrics.stringWidth(text);
  int textHeight = fontMetrics.getHeight();
  int posX = getWidth() - textWidth - 10;
  int posY = textHeight + 10; 

  // Tegner boksen
  int boxWidth = textWidth + 10;
  int boxHeight = textHeight + 4;
  //faregen på boksen
  g.setColor(color.getOverlayColor()); 
  g.fillRect(posX - 5, posY - textHeight + 2, boxWidth, boxHeight);
  g.drawRect(posX - 5, posY - textHeight + 2, boxWidth, boxHeight);
  
  //faregen på teksten
  g.setColor(Color.WHITE); 
  //tegner teskten
  g.drawString(text, posX, posY);
  updateScore(score);
}
/**
 * metode som tegner levelen i motsatt hjørnet
 * @param g
 */
private void drawLevel(Graphics2D g){
  
  int level = TetrisModel.getLevel();
  String text = "Level: "+ level;
  g.setFont(new Font("Arial", Font.BOLD, 30));
  
  // REgenr posisjonen
  FontMetrics fontMetrics = g.getFontMetrics();
  int textWidth = fontMetrics.stringWidth(text);
  int textHeight = fontMetrics.getHeight();
  int posX = 10;
  int posY =textHeight + 10; 

  // Tegner boksen
  int boxWidth = textWidth + 10;
  int boxHeight = textHeight + 4;
  //faregen på boksen
  g.setColor(color.getOverlayColor()); 
  g.fillRect(posX - 5, posY - textHeight + 2, boxWidth, boxHeight);
  g.drawRect(posX - 5, posY - textHeight + 2, boxWidth, boxHeight);
  
  //faregen på teksten
  g.setColor(Color.WHITE); 
  //tegner teskten
  g.drawString(text, posX, posY);
  updateLevel(level);
}
  
/**
 * metode som tegner game over skjermen
 * @param g
 */
private void drawGameOver(Graphics g) {
    g.setColor(color.getOverlayColor());
    g.fillRect(0, 0, getWidth(), getHeight());
    g.setFont(new Font("Arial", Font.BOLD, 40));
    g.setColor(color.getGameOverTextColor());
    Inf101Graphics.drawCenteredString(g, "Game Over", 0, 0, getWidth(), getHeight());

}
  
/**
 * tegner start skjerm, dette ble direkte kopiert fra Chat.
 * kilde: //chat.openai.com/
 * @param g2d
 */
private void drawWelcomeScreen(Graphics2D g2d) {
  // Fill background with overlay color
  g2d.setColor(color.getOverlayColor());
  g2d.fillRect(0, 0, getWidth(), getHeight());
  
  // Set font and draw big title
  g2d.setFont(new Font("Arial", Font.BOLD, 50));
  String title = "Tetris";
  int titleX = -100;
  int titleY = 0;
  Color[] pastelColors = {Color.decode("#FFFF99"), Color.decode("#FFB6C1"), Color.decode("#ADD8E6"), Color.decode("#90EE90")}; 
  for (int i = 0; i < title.length(); i++) {
    g2d.setColor(pastelColors[i % pastelColors.length]);
    Inf101Graphics.drawCenteredString(g2d, String.valueOf(title.charAt(i)), titleX + i * 40, titleY, getWidth(), getHeight());
  }  
  // Set smaller font and draw message
  g2d.setFont(new Font("Arial", Font.BOLD, 20));
  g2d.setColor(color.getWelcomeTextColor());
  String message = "Press space to begin <3";
  Inf101Graphics.drawCenteredString(g2d, message, 0, 50, getWidth(), getHeight());
}
  /**
   * Tegner selve spill-elementene i Tetris.
   * @param g2
   */
  private void drawGame(Graphics2D g2){
    //lengden og bredden av griden
    double width = getWidth() - 2*OUTERMARGIN;
    double height = getHeight() - 2*OUTERMARGIN;
    //lager formen til griden
    Rectangle2D rect = new Rectangle2D.Double(OUTERMARGIN,OUTERMARGIN,width,height);
    g2.setColor(color.getFrameColor());
    //setter inn rektanglet
    g2.fill(rect);
    //lager ny objekt
    CellPositionToPixelConverter converter =new CellPositionToPixelConverter(rect, TetrisModel.getDimension() , INNERMARGIN);
    drawCells(g2, TetrisModel.getTilesOnBoard(), converter, color);
    // Tegner den fallende brikken på toppen av brettet
    drawCells(g2,TetrisModel.getAccessibleCellsOfFallingPiece(),converter,color);
  }

  /**
   * metode som tegner på nytt score når den oppdateres
   */
  private void updateScore(int newScore) {
    this.score = newScore;
    repaint(); 
}
 /**
   * metode som tegner på nytt levelen når den oppdateres
   */
private void updateLevel(int newLevel) {
  this.level = newLevel;
  repaint(); 
}
  /**
   * Tegner en samling med ruter, for eksempel rutene på Tetris-brettet.
   * @param g2
   * @param iterator
   * @param convert
   * @param color
   */
  private static void drawCells(Graphics2D g2, Iterable<GridCell<Character>> iterator, CellPositionToPixelConverter convert,ColorTheme color ){
    for(GridCell<Character> cell: iterator){
      Rectangle2D cellRectangle = convert.getBoundsForCell(cell.pos());
      g2.setColor(color.getCellColor(cell.value()));
      g2.fill(cellRectangle);
    }
  }


  
}
