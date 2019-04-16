package gui;

import log.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar
{
    private MainApplicationFrame ApplicationFrame;

    public MenuBar(MainApplicationFrame mainApplicationFrame) {
        ApplicationFrame = mainApplicationFrame;
    }

    public JMenuBar initMenu(){
        JMenuBar menuBar = new JMenuBar();
        //Set up the lone menu.

        createMenuDocuments(menuBar);
        createLookAndFeelMenu(menuBar);
        createTestMenu(menuBar);


        return menuBar;
    }

    private void createMenuDocuments(JMenuBar menuBar){
        JMenu menu = new JMenu("Document");
        menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);

        //Set up the first menu item.

        JMenuItem menuItem = new JMenuItem("New", KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("new");
        //        menuItem.addActionListener(this);
        menu.add(menuItem);

        //Set up the second menu item.
        menuItem = new JMenuItem("Quit", KeyEvent.VK_Q);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("quit");
        menuItem.addActionListener((event) -> {
            Object[] options = { "Да", "Нет!" };
            int n = JOptionPane.showOptionDialog(this, "Закрыть окно?",
                    "Подтверждение", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (n == 0) {
                this.ApplicationFrame.setVisible(false);
                this.ApplicationFrame.dispose();
                System.exit(0);
            }
        });

        menu.add(menuItem);
    }

    private void createLookAndFeelMenu(JMenuBar menuBar){
        JMenu lookAndFeelMenu = new JMenu("Режим отображения");
        lookAndFeelMenu.setMnemonic(KeyEvent.VK_V);
        lookAndFeelMenu.getAccessibleContext().setAccessibleDescription(
                "Управление режимом отображения приложения");

        {
            JMenuItem systemLookAndFeel = new JMenuItem("Системная схема", KeyEvent.VK_S);
            systemLookAndFeel.addActionListener((event) -> {
                //this.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                //this.mainFrame1.invalidate();
            });
            lookAndFeelMenu.add(systemLookAndFeel);
        }

        {
            JMenuItem crossplatformLookAndFeel = new JMenuItem("Универсальная схема", KeyEvent.VK_S);
            crossplatformLookAndFeel.addActionListener((event) -> {
                //this.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                //this.mainFrame1.invalidate();

            });
            lookAndFeelMenu.add(crossplatformLookAndFeel);
        }

        menuBar.add(lookAndFeelMenu);
    }

    private void createTestMenu(JMenuBar menuBar) {
        JMenu testMenu = new JMenu("Тесты");
        testMenu.setMnemonic(KeyEvent.VK_T);
        testMenu.getAccessibleContext().setAccessibleDescription(
                "Тестовые команды");

        {
            JMenuItem addLogMessageItem = new JMenuItem("Сообщение в лог", KeyEvent.VK_S);
            addLogMessageItem.addActionListener((event) -> {
                Logger.debug("Новая строка");
            });
            testMenu.add(addLogMessageItem);
        }

        menuBar.add(testMenu);
    }

    public void setLookAndFeel(String className)
    {
        try
        {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e)
        {

            // just ignore
        }
    }
}
