// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveClaw extends CommandBase {
  double speed;

  /** Creates a new MoveClaw. */
  public MoveClaw(int direction) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.subClaw);

    speed = direction * Constants.SPEED_CLAW;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.subClaw.move(speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.subClaw.move(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
