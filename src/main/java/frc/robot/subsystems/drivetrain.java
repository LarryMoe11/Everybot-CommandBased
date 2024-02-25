// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class drivetrain extends SubsystemBase {
  CANSparkMax frontLeft = new CANSparkMax(1, CANSparkLowLevel.MotorType.kBrushless);
  CANSparkMax rearLeft = new CANSparkMax(2, CANSparkLowLevel.MotorType.kBrushless);

  CANSparkMax frontRight = new CANSparkMax(3, CANSparkLowLevel.MotorType.kBrushless);
  CANSparkMax rearRight = new CANSparkMax(4, CANSparkLowLevel.MotorType.kBrushless);

  public drivetrain() {}

  public void setDrivePower(double leftPower, double rightPower) {
    frontLeft.set(leftPower);
    rearLeft.set(leftPower);

    frontRight.set(rightPower);
    rearRight.set(rightPower);
  }

  @Override
  public void periodic() {}
}
