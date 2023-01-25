// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
//import edu.wpi.first.wpilibj.Compressor;
//import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


public class Robot extends TimedRobot {
  // Declare all vars here
  private Command m_autonomousCommand;
  // Compressor pcmCompressor = new Compressor(PneumaticsModuleType.CTREPCM);
  // DoubleSolenoid solenoid1 = new
  // DoubleSolenoid(PneumaticsModuleType.CTREPCM,1,2);
  // private final Joystick mainStick = new Joystick(0);
  private final XboxController secondStick = new XboxController(0);
  private RobotContainer m_robotContainer;

  // Multipliers to fine tune controls and speeds
  // TO DO
  double speed = 0.8;

  // Slew rate limiter filters
  SlewRateLimiter filterLeft = new SlewRateLimiter(0.75);
  SlewRateLimiter filterRight = new SlewRateLimiter(0.75);

  // Motor Declarations
  PWMVictorSPX motor_RightFront = new PWMVictorSPX(0);
  PWMVictorSPX motor_RightRear = new PWMVictorSPX(1);
  PWMVictorSPX motor_LeftFront = new PWMVictorSPX(2);
  PWMVictorSPX motor_LeftRear = new PWMVictorSPX(3);

  // Motor Group Declarations
  MotorControllerGroup motorRight = new MotorControllerGroup(motor_RightRear, motor_RightFront);
  MotorControllerGroup motorLeft = new MotorControllerGroup(motor_LeftRear, motor_LeftFront);
  // DifferentialDrive mainDrive = new DifferentialDrive(motorLeft,motorRight);

  Accelerometer accelerometer = new BuiltInAccelerometer();

  @Override
  public void robotInit() { // Initial code. Runs on startup.
    m_robotContainer = new RobotContainer();

    // Compressor and Solenoid disabling/closing just to play it safe
    // pcmCompressor.disable();

    // Invert the left motor control group so applying a positive voltage will move
    // forwards on both sides.
    motorRight.setInverted(true);

    CameraServer.startAutomaticCapture();
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
    /** Main joystick controls */
    /**
     * if(mainStick.isConnected()){
     * mainDrive.arcadeDrive(-mainStick.getY(),mainStick.getTwist());
     * if (mainStick.getRawButtonPressed(3)) {
     * System.out.println("Button 3 Pressed. Compressor Enabled.");
     * pcmCompressor.enableDigital();
     * }
     * if (mainStick.getRawButtonPressed(4)) {
     * System.out.println("Button 4 Pressed. Compressor Disabled.");
     * pcmCompressor.disable();
     * }
     * if (mainStick.getRawButtonPressed(5)) {
     * System.out.println("Button 5 Pressed. Soneloid Forward.");
     * solenoid1.set(DoubleSolenoid.Value.kForward);
     * }
     * if (mainStick.getRawButtonPressed(6)) {
     * System.out.println("Button 6 Pressed. Soneloid Reverse.");
     * solenoid1.set(DoubleSolenoid.Value.kReverse);
     * }
     * if(mainStick.getRawButtonPressed(7)){
     * System.out.println("Button 7 Pressed. Solenoid Disabled.");
     * solenoid1.set(DoubleSolenoid.Value.kOff);
     * }
     * 
     * } else if(secondStick.isConnected()){
     */
    /** Play Station Controller controls */
    // Movement controls (TANK DRIVE)
    // These if statements are built in to negate joycon dift, where the joycons
    // detect movement while stationary

    //These are here to avoid issues with the acceleration when trying to turn or brake so we can stop more easily.
    boolean isBraking = (secondStick.getRawAxis(1) < -0.04 && secondStick.getRawAxis(3) < -0.04 ); 
    boolean isTurningLeft = (secondStick.getRawAxis(1) < -0.04 && secondStick.getRawAxis(3) > 0.04);
    boolean isTurningRight = (secondStick.getRawAxis(1) > 0.04 && secondStick.getRawAxis(3) < -0.04);
    if(isBraking || isTurningLeft || isTurningRight){
      motorLeft.set(speed * secondStick.getRawAxis(1));
      motorRight.set(speed * secondStick.getRawAxis(3));
    } else {
    if (secondStick.getRawAxis(1) > -0.04 && secondStick.getRawAxis(1) < 0.04) {
      motorLeft.set(0);
    } else {
      motorLeft.set(speed * filterLeft.calculate(secondStick.getRawAxis(1)));
    }
    if (secondStick.getRawAxis(3) > -0.04 && secondStick.getRawAxis(3) < 0.04) {
      motorRight.set(0);
    } else {
      if(secondStick.getRawAxis(3) < 0){
        motorLeft.set(speed * secondStick.getRawAxis(1));
      } else {
        motorRight.set(speed * filterRight.calculate(secondStick.getRawAxis(3)));
      }
    }
  }

    // Compressor and solenoid controls
    /*
     * if(secondStick.getRawButtonPressed(1)) {
     * System.out.println("Button 3 Pressed. Compressor Enabled.");
     * pcmCompressor.enableDigital();
     * }
     * if(secondStick.getRawButtonPressed(3)) {
     * System.out.println("Button 2 Pressed. Compressor Disabled.");
     * pcmCompressor.disable();
     * }
     * if(secondStick.getRawButtonPressed(5)) {
     * System.out.println("Button 5 Pressed. Soneloid Forward.");
     * solenoid1.set(DoubleSolenoid.Value.kForward);
     * }
     * if(secondStick.getRawButtonPressed(6)) {
     * System.out.println("Button 6 Pressed. Soneloid Reverse.");
     * solenoid1.set(DoubleSolenoid.Value.kReverse);
     * }
     */
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    
  }

  @Override
  public void testPeriodic() {
    /* 
    System.out.println("X: " + accelerometer.getX());
    System.out.println("Y: " + accelerometer.getY());
    System.out.println("Z: " + accelerometer.getZ());
    */
  }
}