package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */

    private Object control;
    private final JFrame = new JFrame("2nd graphical interface");
    private SimpleGUIWithFileChooser(final Controller control) {
       frame.setDefaultCloserOperation(JFrame.EXIT_ON_CLOSE); 
       // Panel configuration
       final JPanel panel2 = new JPanel();
       panel2.setLayout(new BorderLayout());
       // Text area
       final JTextArea text = new JTextArea();
       // Save button
       final JButton save = new JButton("Save");
       save.addActionListener((ActionListener) new ActionListener() {
           public void actionPerformed(final ActionEvent event) {
               try {
                   control.save(text.getText());
               } catch (IOException e) {
                   JOptionPane.showMessageDialog(null, e.getMessage(), "An error occurred", JOptionPane.ERROR_MESSAGE);
               }
           }
       });
    }
    panel2.add(text, BorderLayout.NORTH);
    panel2.add(save, BorderLayout.SOUTH);
    
    final JTextField filepath = new JTextField(((Object) control).getCurrentFilePath());
    filepath.setEditable(false);
    final JButton chooseFile = new JButton("Browse...");
    chooseFile.addActionListener(new ActionListener() {
        public void actionPerformed(final ActionEvent e) {
            final JFileChooser fc = new JFileChooser("Choose where to save");
            fc.setSelectedFile(control.getCurrentFile());
            final int result = fc.showSaveDialog(frame);
            switch (result) {
            case JFileChooser.APPROVE_OPTION:
                final File newDest = fc.getSelectedFile();
                control.setDestination(newDest);
                filepath.setText(newDest.getPath());
                break;
            case JFileChooser.CANCEL_OPTION:
                break;
            default:
                JOptionPane.showMessageDialog(frame, result, "Meh!", JOptionPane.ERROR_MESSAGE);
            }
        }
    });
    
    final JPanel upperPanel = new JPanel();
    upperPanel.setLayout(new BorderLayout());
    upperPanel.add(filepath, BorderLayout.CENTER);
    upperPanel.add(chooseFile, BorderLayout.LINE_END);
    panel2.add(upperPanel, BorderLayout.NORTH);

    
    frame.setContentPane(panel2)
    final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    final int sw = (int) screen.getWidth();
    final int sh = (int) screen.getHeight();
    frame.setSize(sw / 4, sh / 4);
    frame.setLocationByPlatform(true);
}

private void display() {
    frame.setVisible(true);
}

/**
 * @param a
 *            unused
 */
public static void main(final String... a) {
    final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
    gui.display();
}
}
