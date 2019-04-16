package gui;

import java.io.Serializable;

public class State implements Serializable {
    private int x;
    private int Y;
    private int width;
    private int heigth;
    private boolean isIcon;
    private boolean isMaximum;

    public boolean isIcon() {
        return isIcon;
    }

    public boolean isMaximum() {
        return isMaximum;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return Y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeigth() {
        return heigth;
    }

    public State(int x, int y, int h, int w, boolean isIcon, boolean isMaximum){
        this.x = x;
        this.Y = y;
        this.heigth=h;
        this.width=w;
        this.isIcon = isIcon;
        this.isMaximum = isMaximum;
    }
}