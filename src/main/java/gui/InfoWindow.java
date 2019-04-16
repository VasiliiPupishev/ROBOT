package gui;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class InfoWindow extends JInternalFrame
        implements WindowSerializable, VetoableChangeListener {

    private JLabel text;
    private State gameState;

    InfoWindow(){
        super("Информация про робота", true, true, true, true);
//        gameState = new GameVisualizerState(0,0,0,0,0,0,0);
        text = new JLabel("first");
        setSize(400,400);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(text, BorderLayout.CENTER);
//        notify(gameState);
        this.getContentPane().add(panel);
        this.pack();
        addVetoableChangeListener(this);
    }

    @Override
    public String getKey() {
        return this.getTitle();
    }
    @Override
    public State getState(){
        return new State(this.getLocation().x,this.getLocation().y,this.getHeight(),this.getWidth(), this.isIcon(), this.isMaximum());
    }

    @Override
    public void setState(State location) {
        this.setLocation(location.getX(), location.getY());
        this.setSize(location.getWidth(), location.getHeigth());

        try {
            this.setIcon(location.isIcon());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        try {
            this.setMaximum(location.isMaximum());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }
    public void setLabel(String text)
    {
        this.text.setText(text);
    }

    public void vetoableChange(PropertyChangeEvent pce)
            throws PropertyVetoException {
        if (pce.getPropertyName().equals(IS_CLOSED_PROPERTY)) {
            boolean changed = ((Boolean) pce.getNewValue()).booleanValue();
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