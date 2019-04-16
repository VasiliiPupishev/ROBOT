package log;

import java.awt.event.WindowEvent;

public interface LogChangeListener
{
    public void onLogChanged();

    void windowClosing(WindowEvent we);
}
