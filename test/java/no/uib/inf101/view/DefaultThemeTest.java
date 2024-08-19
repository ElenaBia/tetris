package no.uib.inf101.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import no.uib.inf101.tetris.view.ColorTheme;
import no.uib.inf101.tetris.view.DefaultColorTheme;


public class DefaultThemeTest {

    @Test
    public void sanityDefaultColorThemeTest() {
       ColorTheme colors = new DefaultColorTheme();
        assertEquals(null, colors.getBackgroundColor());
        assertEquals(new Color(0,0,0,0), colors.getFrameColor());
        assertEquals(Color.BLACK, colors.getCellColor('-'));
        assertThrows(IllegalArgumentException.class, () -> colors.getCellColor('\n'));
    }

}
