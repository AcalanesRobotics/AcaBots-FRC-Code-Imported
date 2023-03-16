// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.math.filter.SlewRateLimiter;

public class DriveTank extends CommandBase {
  SlewRateLimiter rightSlewRateLimiter = new SlewRateLimiter(Constants.SLEWRATELIMITER_RIGHT_RATE);
  SlewRateLimiter leftSlewRateLimiter = new SlewRateLimiter(Constants.SLEWRATELIMITER_LEFT_RATE);

  /** Creates a new DriveTank. */
  public DriveTank() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double rightSpeed = RobotContainer.controller.getRawAxis(Constants.CONTROLLER_RIGHT_DRIVE_AXIS);
    double leftSpeed = RobotContainer.controller.getRawAxis(Constants.CONTROLLER_LEFT_DRIVE_AXIS);

    RobotContainer.m_drivetrain.tankDrive(-rightSlewRateLimiter.calculate(rightSpeed),-leftSlewRateLimiter.calculate(leftSpeed));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_drivetrain.tankDrive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
