// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Extender extends SubsystemBase {

  PWMSparkMax motorMain = null;

  /** Creates a new Extender. */
  public Extender() {
    motorMain = new PWMSparkMax(Constants.EXTENDER_MOTOR_MAIN);
  }

  public void move(double speed){
    motorMain.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
