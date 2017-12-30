/*
 * TCSS 305 - Assignment 6: Tetris
 */
package view;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import java.awt.EventQueue;
import java.util.Properties;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main method to run Tetris.
 * 
 * @author Josh Atherton
 * @version 1
 * 
 * The look and feel for this program is provided by using JTatto
 * http://www.jtattoo.net/
 */
public final class TetrisMain {
    
    /** private constructor of TetrisMain to prevent instantiation. */
    private TetrisMain() {
        throw new IllegalStateException();
    }
    
    /**
     * Set the look and feel for the GUI program.
     * The code to create this look and feel was written by JTattoo.
     * Check out their website and other themes by going to jtattoo.net
     */
    private static void setLookAndFeel() {
        try {
            final Properties props = new Properties();
            props.put("logoString", "Tetris");
            HiFiLookAndFeel.setCurrentTheme(props);
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        } catch (final UnsupportedLookAndFeelException e) {
            System.out.println("UnsupportedLookAndFeelException");
        } catch (final ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        } catch (final InstantiationException e) {
            System.out.println("InstantiationException");
        } catch (final IllegalAccessException e) {
            System.out.println("IllegalAccessException");
        } 
    }
    
    /**
     * The main method, invokes the Tetris GUI. Command line arguments are
     * ignored.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setLookAndFeel();
                new TetrisGUI().setup();
            }
        });
    }
}
