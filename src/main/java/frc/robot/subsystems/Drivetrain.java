// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  // Variables
  PWMSparkMax motorRightFront = null;
  PWMSparkMax motorRightBack = null;
  PWMSparkMax motorLeftFront = null;
  PWMSparkMax motorLeftBack = null;

  MotorControllerGroup rightMotors = null;
  MotorControllerGroup leftMotors = null;

  DifferentialDrive drive = null;

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    // Sets drive motors
    motorRightFront = new PWMSparkMax(Constants.DRIVETRAIN_MOTOR_RIGHT_FRONT);
    motorRightBack = new PWMSparkMax(Constants.DRIVETRAIN_MOTOR_RIGHT_BACK);
    motorLeftFront = new PWMSparkMax(Constants.DRIVETRAIN_MOTOR_LEFT_FRONT);
    motorLeftBack = new PWMSparkMax(Constants.DRIVETRAIN_MOTOR_LEFT_BACK);

    rightMotors = new MotorControllerGroup(motorRightFront, motorRightBack);
    leftMotors = new MotorControllerGroup(motorLeftFront, motorLeftBack);
    leftMotors.setInverted(true);
  }

  public void tankDrive(double rightSpeed, double leftSpeed){
    drive.tankDrive(rightSpeed, leftSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
