// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Claw extends SubsystemBase {

  PWMVictorSPX motorRight = null;
  PWMVictorSPX motorLeft = null;

  MotorControllerGroup motors = null;

  /** Creates a new Claw. */
  public Claw() {
    motorRight = new PWMVictorSPX(Constants.CLAW_MOTOR_RIGHT);
    motorLeft = new PWMVictorSPX(Constants.CLAW_MOTOR_LEFT);

    motorRight.setInverted(true);

    motors = new MotorControllerGroup(motorRight,motorLeft);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
