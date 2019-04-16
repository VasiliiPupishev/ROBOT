package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.round;

public class GameVisualizer extends JPanel {

    private final Map map;

    GameVisualizer() {
        map = new Map(500, 500);
        map.setRobot(new Robot(100, 100, 0));
        map.setTarget(new GameObj(150, 100));

        Timer timer = new Timer("Events generator", true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onRedrawEvent();
            }
        }, 0, 50);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                map.update();
            }
        }, 0, 10);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                map.setTarget(new GameObj(e.getX(), e.getY()));
                repaint();
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                map.setWidth(e.getComponent().getWidth());
                map.setHeight(e.getComponent().getHeight());
            }
        });

        setDoubleBuffered(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        drawRobot(g2d, (int) round(map.getRobot().getX()), (int) round(map.getRobot().getY()),
                map.getRobot().getDirection());
        drawTarget(g2d, (int) round(map.getTarget().getX()), (int) round(map.getTarget().getY()));
    }

    private void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }

    private void drawRobot(Graphics2D g, int x, int y, double direction) {
        g.setTransform(AffineTransform.getRotateInstance(direction, x, y));
        g.setColor(Color.MAGENTA);
        fillOval(g, map.getRobot().getX(), map.getRobot().getY(), 30, 10);
        g.setColor(Color.BLACK);
        drawOval(g, map.getRobot().getX(), map.getRobot().getY(), 30, 10);
        g.setColor(Color.WHITE);
        fillOval(g, map.getRobot().getX()  + 10, map.getRobot().getY(), 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, map.getRobot().getX()  + 10, map.getRobot().getY(), 5, 5);
    }

    private void drawTarget(Graphics2D g, double x, double y) {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, x, y, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, x, y, 5, 5);    }

    private static void fillOval(Graphics g, double centerX, double centerY, int diam1, int diam2)
    {
        g.fillOval((int)centerX - diam1 / 2, (int)centerY - diam2 / 2, diam1, diam2);
    }

    private static void drawOval(Graphics g, double centerX, double centerY, int diam1, int diam2)
    {
        g.drawOval((int)centerX - diam1 / 2, (int)centerY - diam2 / 2, diam1, diam2);
    }

}