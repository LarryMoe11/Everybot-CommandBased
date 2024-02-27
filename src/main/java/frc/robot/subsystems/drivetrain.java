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
    leftRear.follow(leftFront); // tells the rear left motor to have the same output as front left
    rightRear.follow(rightFront); // tells the rear right motor to have the same output as front right

    leftFront.setInverted(false); // we need to invert one side (default right)
    rightFront.setInverted(true); // we need to invert one side (default right)

    leftFront.setIdleMode(IdleMode.kBrake); // make sure drivetrain is in brake mode so robot stops when joystick is released
    rightFront.setIdleMode(IdleMode.kBrake); // make sure drivetrain is in brake mode so robot stops when joystick is released
  }

  public void tankDrive(double leftPower, double rightPower) {
    leftFront.set( // sets left motor power
      Math.min(Math.abs(leftPower), 1) * Math.signum(leftPower) // just ensures power is never set over 1 and under -1
    );

    rightFront.set( // sets right motor power
      Math.min(Math.abs(rightPower), 1) * Math.signum(rightPower) // just ensures power is never set over 1 and under -1
    );
  }

  public void arcadeDrive(double forwardPower, double turnPower) {
    tankDrive(forwardPower - turnPower, forwardPower + turnPower);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Left Power", leftFront.get()); // prints power being set to left motor
    SmartDashboard.putNumber("Right Power", rightFront.get()); // prints power being set to right motor
  }
}
