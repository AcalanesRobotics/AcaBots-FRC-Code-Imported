// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.DriveTank;
import frc.robot.commands.MoveClaw;
import frc.robot.commands.MoveExtender;
import frc.robot.subsystems.Claw;
//import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Extender;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  
  // public static XboxController controller = new XboxController(Constants.CONTROLLER_PORT);
  public static CommandXboxController controller = new CommandXboxController(Constants.CONTROLLER_PORT);

  // The robot's subsystems and commands are defined here...
  public static final Drivetrain m_drivetrain = new Drivetrain();
  public static final Extender subExtender = new Extender();  
  public static final Claw subClaw = new Claw();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Set default commands of subsystems
    m_drivetrain.setDefaultCommand(new DriveTank());
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    controller.y()
      .whileTrue(new MoveExtender(Constants.EXTENDER_DIRECTION_OUTWARDS));

    controller.b()
      .whileTrue(new MoveExtender(Constants.EXTENDER_DIRECTION_INWARDS));

    controller.leftBumper()
      .whileTrue(new MoveClaw(Constants.CLAW_DIRECTION_OPEN));

    controller.rightBumper()
      .whileTrue(new MoveClaw(Constants.CLAW_DIRECTION_CLOSE));

    // controller.rightTrigger()
    //   .onTrue(new InstantCommand(() -> m_drivetrain.changeSlowMode()))
    //   .onFalse(new InstantCommand(() -> m_drivetrain.changeSlowMode()));

    controller.button(7)
      .onTrue(new InstantCommand(() -> m_drivetrain.changeSlowMode()))
      .onFalse(new InstantCommand(() -> m_drivetrain.changeSlowMode()));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

    // An example command will be run in autonomous

    return new AutonomousCommand();
  }
}