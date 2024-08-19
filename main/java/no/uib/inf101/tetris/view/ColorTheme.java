package no.uib.inf101.tetris.view;
import java.awt.*;

public interface ColorTheme {

    /** 
     * Henter bakgrunsfargen. PS! fargen kan ikke være gjennomsiktig, men kan være null dersom
     * man ønsker å benytte standard bakgrunssfarge fra Java.
     * @return type <code>Color</code>
     */
    Color getBackgroundColor();
    
    /**
     * Henter fargen på rammen. Retur verdien bør ikke være <code>null</code>,
     * men det er mulig å benytte en helt gjennomsiktig farge (new Color(0, 0, 0, 0))
     * hvis man ikke ønsker å ha en egen ramme rundt rutene.
     * @return type <code>Color</code>
     */
    Color getFrameColor();

    /**
     * Funksjon som henter ut fargen til cellen. Returverdien kan IKKE være <code>null</code>.
     * @param character
     * @return farge av typen Color
     */
    Color getCellColor(char c);

   /**
     * returnerer fargen som er brukt for å vise GAME OVER teksten når spillet er over
     * @return Color
     */
    Color getGameOverTextColor();
     /**
     * returnerer fargen som er brukt for å vise GAME OVER teksten når spillet er over
     * @return Color
     */
    Color getWelcomeTextColor();
    /** returnerer fargen som skal dekke hele skjermen når spillet er over
     * @return Color
    */
    Color getOverlayColor();

    

} 