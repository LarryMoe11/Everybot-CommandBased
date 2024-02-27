// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;

public class drivetrain extends SubsystemBase {
  CANSparkMax leftRear = new CANSparkMax(MotorConstants.kLeftRearID, MotorType.kBrushless);
  CANSparkMax leftFront = new CANSparkMax(MotorConstants.kLeftFrontID, MotorType.kBrushless);

  CANSparkMax rightRear = new CANSparkMax(MotorConstants.kRightRearID, MotorType.kBrushless);
  CANSparkMax rightFront = new CANSparkMax(MotorConstants.kRightFrontID, MotorType.kBrushless);

  public drivetrain() {
    leftRear.follow(leftFront);
    rightRear.follow(rightFront);

    leftFront.setInverted(false);
    rightFront.setInverted(true);

    leftFront.setIdleMode(IdleMode.kBrake);
    rightFront.setIdleMode(IdleMode.kBrake);
  }

  public void tankDrive(double leftPower, double rightPower) {
    leftFront.set(Math.min(Math.abs(leftPower), 1) * Math.signum(leftPower));
    rightFront.set(Math.min(Math.abs(rightPower), 1) * Math.signum(rightPower));
  }

  public void arcadeDrive(double forwardPower, double turnPower) {
    tankDrive(forwardPower - turnPower, forwardPower + turnPower);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Left Power", leftFront.get());
    SmartDashboard.putNumber("Right Power", rightFront.get());
  }
}
