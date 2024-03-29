// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

//import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  // Declare all vars here
  private Command m_autonomousCommand;

  private final XboxController mainStick = new XboxController(0);
  private RobotContainer m_robotContainer;

  // Multipliers to fine tune controls and speedMultipliers
  // TO DO
  static final double speedMultiplier = 0.8;
  static final double clawMultiplier = 1;
  static final double elephantSpeed = 1;

  // Slew rate limiter filters
  SlewRateLimiter filterLeft = new SlewRateLimiter(2);
  SlewRateLimiter filterRight = new SlewRateLimiter(2);

  // Motor Declarations
  PWMSparkMax motor_RightFront = new PWMSparkMax(0);
  PWMSparkMax motor_RightRear = new PWMSparkMax(1);
  PWMSparkMax motor_LeftFront = new PWMSparkMax(2);
  PWMSparkMax motor_LeftRear = new PWMSparkMax(3);

  PWMVictorSPX motor_ClawOne = new PWMVictorSPX(4);
  PWMVictorSPX motor_ClawTwo = new PWMVictorSPX(6);

  PWMSparkMax motor_Elephant = new PWMSparkMax(5);

  // Motor Group Declarations
  MotorControllerGroup motorRight = new MotorControllerGroup(motor_RightRear, motor_RightFront);
  MotorControllerGroup motorLeft = new MotorControllerGroup(motor_LeftRear, motor_LeftFront);

  DifferentialDrive drive = null;

  MotorControllerGroup motorClaw = new MotorControllerGroup(motor_ClawOne,motor_ClawTwo);

  // Other
  Accelerometer accelerometer = new BuiltInAccelerometer();

  @Override
  public void robotInit() { // Initial code. Runs on startup.
    m_robotContainer = new RobotContainer();

    // Invert the right motor control group so applying a positive voltage will move
    // forwards on both sides.P
    motorRight.setInverted(true);
    motor_ClawOne.setInverted(true);

    drive = new DifferentialDrive(motorLeft, motorRight);

    //CameraServer.startAutomaticCapture();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items
   * like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    // m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    // Play Station Controller controls
    // Movement controls (TANK DRIVE)

    // Sets the joystick variable to account for stick drift
    
    double leftStick = mainStick.getRawAxis(1);
    double rightStick = mainStick.getRawAxis(3);

    // // Drive state bools
    // boolean isBraking = (leftStick > 0 && rightStick > 0);
    // boolean isTurningLeft = (leftStick < 0 && rightStick > 0);
    // boolean isTurningRight = (leftStick > 0 && rightStick < 0);

    // boolean isLeftStationary = (leftStick == 0);
    // boolean isRightStationary = (rightStick == 0);

    // // Moving
    // if (isBraking || isTurningLeft || isTurningRight) {
    //   motorLeft.set(speedMultiplier * leftStick);
    //   motorRight.set(speedMultiplier * rightStick);
    // } else {

    //   if (isLeftStationary) {
    //     motorLeft.set(0);
    //   } else {
    //     motorLeft.set(speedMultiplier * filterLeft.calculate(leftStick));
    //   }

    //   if (isRightStationary) {
    //     motorRight.set(0);
    //   } else {
    //     motorRight.set(speedMultiplier * filterRight.calculate(rightStick));
    //   }
    // }

    drive.tankDrive(filterLeft.calculate(leftStick), filterRight.calculate(rightStick));

    // Claw controls
    if (mainStick.getRawButton(5)){
      //Left button opens
      motorClaw.set(clawMultiplier);
      System.out.println("Claw opens. Left pressed.");
    } else if (mainStick.getRawButton(6)){
      //Right button closes
      motorClaw.set(-clawMultiplier);
      System.out.println("Claw closes. Right pressed.");
    } else {
      motorClaw.set(0);
    }
    
    
    if(mainStick.getRawButton(4)){
      motor_Elephant.set(elephantSpeed);
    }else if(mainStick.getRawButton(2)){
      motor_Elephant.set(-elephantSpeed);
    }else{
      motor_Elephant.set(0);  
    }
  }

  @Override
  public void testInit() {
    
  }

  @Override
  public void testPeriodic() {

  }
}