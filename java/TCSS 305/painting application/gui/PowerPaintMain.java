/*
 * TCSS - 305: Assignment 5 - PowerPaint
 */

package gui;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Starts PowerPaint by instantiating and starting PowerPaintGUI.
 * 
 * @author Josh Atherton
 * @version 1
 */
public final class PowerPaintMain {
    /**
     * Private constructor to prevent this class from being instantiated.
     */
    private PowerPaintMain() {
        throw new IllegalStateException();
    }
    
    /**
     * Set the look and feel for the GUI program.
     */
    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
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
     * The main method invokes PowerPaint GUI. Command line arguments are ignored.
     * 
     * @param theArgs command line input
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setLookAndFeel();
                new PowerPaintGUI().start();
            }
        });
    }
}
