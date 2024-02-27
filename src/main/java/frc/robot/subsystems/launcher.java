// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;

public class launcher extends SubsystemBase {
  CANSparkMax launchWheel = new CANSparkMax(MotorConstants.kLaunchWheelID, MotorType.kBrushless);
  CANSparkMax feedWheel = new CANSparkMax(MotorConstants.kFeedWheelID, MotorType.kBrushless);
  
  SparkPIDController launchWheelPID;
  SparkPIDController feedWheelPID;

  Boolean shootState = false; // true if the shooter should be running
  Boolean state = false; // true if motors are in use

  public launcher() {
    launchWheelPID = launchWheel.getPIDController();
    launchWheelPID.setFeedbackDevice(launchWheel.getEncoder());
    launchWheelPID.setP(6e-5); // 0.00006
    launchWheelPID.setI(0);
    launchWheelPID.setD(0);
    launchWheelPID.setIZone(0);
    launchWheelPID.setFF(0.000180);
    launchWheelPID.setOutputRange(-1, 1);

    feedWheelPID = feedWheel.getPIDController();
    feedWheelPID.setFeedbackDevice(feedWheel.getEncoder());
    feedWheelPID.setP(6e-5);
    feedWheelPID.setI(0);
    feedWheelPID.setD(0);
    feedWheelPID.setIZone(0);
    feedWheelPID.setFF(0.000180);
    feedWheelPID.setOutputRange(-1, 1);
  }

  public void intakeNote() {
    if (!state) {
      state = true;
      launchWheelPID.setReference(MotorConstants.kIntakeSpeed, ControlType.kVelocity);
      feedWheelPID.setReference(MotorConstants.kIntakeSpeed, ControlType.kVelocity);
    }
  }

  public void shootNote() {
    if (!state) {
      state = true; shootState = true;
      launchWheelPID.setReference(MotorConstants.kShootSpeed, ControlType.kVelocity);
    }
  }

  public void stopMotors() {
    launchWheelPID.setReference(0, ControlType.kVelocity);
    feedWheelPID.setReference(0, ControlType.kVelocity);
    state = false; shootState = false;
  }

  private void updateShooter() {
    if (shootState) {
      if (launchWheel.getEncoder().getVelocity() >= MotorConstants.kShootSpeed - 25) {
        feedWheelPID.setReference(MotorConstants.kShootSpeed/4, ControlType.kVelocity);
        if (feedWheel.getEncoder().getVelocity() >= (MotorConstants.kShootSpeed/4) - 25) { stopMotors(); state = true;}
      }
    }
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Intake-Shooter State", state);
    SmartDashboard.putNumber("Launch Wheel Speed", launchWheel.getEncoder().getVelocity());
    SmartDashboard.putNumber("Feed Wheel Speed", feedWheel.getEncoder().getVelocity());

    updateShooter();
  }
}
