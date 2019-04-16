package gui;

public class RobotState {
    private double m_robotPositionX = 100;
    private double m_robotPositionY = 100;
    private double m_robotDirection = 0;

    private int m_targetPositionX = 150;
    private int m_targetPositionY = 100;

    private double maxVelocity = 0.1;
    private double maxAngularVelocity = 0.001;

    public RobotState(double robotPositionX,
                      double robotPositionY,
                      double robotDirection,
                      int targetPositionX,
                      int targetPositionY,
                      double maxVelocity,
                      double maxAngularVelocity){
        this.m_robotPositionX = robotPositionX;
        this.m_robotPositionY = robotPositionY;
        this.m_robotDirection = robotDirection;
        this.m_targetPositionX = targetPositionX;
        this.m_targetPositionY = targetPositionY;
        this.maxVelocity = maxVelocity;
        this.maxAngularVelocity = maxAngularVelocity;
    }

    public String createString(){
        StringBuilder result = new StringBuilder();
        result.append("<html>");
        result.append(String.format("robot position X: %f<br>", m_robotPositionX));
        result.append(String.format("robot position Y: %f<br>", m_robotPositionY));
        result.append(String.format("robot direction: %f<br>", m_robotDirection));
        result.append("<br>");
        result.append(String.format("target position X: %d<br>", m_targetPositionX));
        result.append(String.format("target position X: %d<br>", m_targetPositionY));
        result.append("<br>");
        result.append(String.format("max velocity: %f<br>", maxVelocity));
        result.append(String.format("max angular velocity: %f<br>", maxAngularVelocity));
        return result.toString();
    }
}