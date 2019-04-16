package gui;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import javax.swing.*;

public class GameWindow extends JInternalFrame implements VetoableChangeListener
{
    private final GameVisualizer m_visualizer;

    public GameWindow()
    {
        super("Игровое поле", true, true, true, true);
        m_visualizer = new GameVisualizer();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
        addVetoableChangeListener(this);
    }

    public void windowClosing(WindowEvent we) {
        System.out.println("закрытие");
        String ObjButtons[] = {"Yes", "No"};
        int PromptResult = JOptionPane.showOptionDialog(null,
                "Are you sure you want to exit?", "Online Examination System",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                ObjButtons, ObjButtons[1]);
        if (PromptResult == 0) {
            System.exit(0);
        }
    }

    public void vetoableChange(PropertyChangeEvent pce)
            throws PropertyVetoException {
        if (pce.getPropertyName().equals(IS_CLOSED_PROPERTY)) {
            boolean changed = (Boolean) pce.getNewValue();
            if (changed) {
                int option = JOptionPane.showOptionDialog(this, "Close " +
                                getTitle() + "?",
                        "Close Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, null, null);
                if (option != JOptionPane.YES_OPTION) {
                    throw new PropertyVetoException("Cancelled",null);
                }
            }
        }
    }
}
