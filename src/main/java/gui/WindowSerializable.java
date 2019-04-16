package gui;

import java.beans.PropertyVetoException;

public interface WindowSerializable {
    String getKey();
    State getState();
    void setState(State location);
}