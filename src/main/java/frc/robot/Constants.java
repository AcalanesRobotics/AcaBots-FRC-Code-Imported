package frc.robot;

public class Constants {
    // Drive motors
    public static final int DRIVETRAIN_MOTOR_RIGHT_FRONT = 0;
    public static final int DRIVETRAIN_MOTOR_RIGHT_BACK = 1;
    public static final int DRIVETRAIN_MOTOR_LEFT_FRONT = 2;
    public static final int DRIVETRAIN_MOTOR_LEFT_BACK = 3;

    // Extender motor
    public static final int EXTENDER_MOTOR_MAIN = 6;
    public static final int EXTENDER_DIRECTION_OUTWARDS = 1;
    public static final int EXTENDER_DIRECTION_INWARDS = -1;

    // Claw motors
    public static final int CLAW_MOTOR_RIGHT = 4;
    public static final int CLAW_MOTOR_LEFT = 5;
    public static final int CLAW_DIRECTION_OPEN = 1;
    public static final int CLAW_DIRECTION_CLOSE = -1;

    // Controls
    public static final int CONTROLLER_PORT = 0;
    public static final int CONTROLLER_RIGHT_DRIVE_AXIS = 3;
    public static final int CONTROLLER_LEFT_DRIVE_AXIS = 1;

    // SlewRateLimiter rate of changes
    public static final double SLEWRATELIMITER_RIGHT_RATE = 0.85;
    public static final double SLEWRATELIMITER_LEFT_RATE = 0.85;

    // Speeds
    public static final double SPEED_EXTENDER = 1;
    public static final double SPEED_CLAW = 0.7;
}
