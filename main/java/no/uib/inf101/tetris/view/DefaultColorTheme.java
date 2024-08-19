package no.uib.inf101.tetris.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme{

    @Override
    public Color getBackgroundColor() {
       //return Color.lightGray;
       return null;
    }

    @Override
    public Color getFrameColor() {
       // return Color.BLACK;
       return new Color(0,0,0,0);
    }

    @Override
    public Color getCellColor(char c) {
        Color color = switch(c) {
            /*
            * Assistance provided by ChatGPT, an AI language model developed by OpenAI.
            * ChatGPT provided guidance and assistance in developing "cute color palette".
            * For more information, visit: https://openai.com/chatgpt
            */
            case 'Z' -> Color.decode("#FFFF99"); 
            case 'S' -> Color.decode("#FFB6C1");
            case 'I' -> Color.decode("#87CEEB");
            case 'T' -> Color.decode("#9EE90");
            case 'O' -> Color.decode("#FF6347"); 
            case 'y' -> Color.decode("#40E0D0");
            case 'J' -> Color.decode("#9370DB"); 
            case '-' -> Color.BLACK;
            case 'L' ->Color.decode("#FFA500");
            default -> throw new IllegalArgumentException(
                "No available color for '" + c + "'");
        };
        return color;
          
    }


    @Override
    public Color getGameOverTextColor(){
        return new Color(255, 0, 0, 255);
    }
    
    @Override
    public Color getOverlayColor(){
        return new Color(0, 0, 0, 200);
        
    }

    @Override
    public Color getWelcomeTextColor() {
        return new Color(255, 255, 153);
    }

    
}
