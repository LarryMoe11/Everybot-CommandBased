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
  CANSparkMax launchWheel = new CANSparkMax(MotorConstants.kLaunchWheelID, MotorType.kBrushless); // upper shooter wheel
  CANSparkMax feedWheel = new CANSparkMax(MotorConstants.kFeedWheelID, MotorType.kBrushless); // lower shooter wheel
  CANSparkMax ampWheel = new CANSparkMax(MotorConstants.kAmpWheelID, MotorType.kBrushless); // amp motor
  SparkPIDController launchWheelPID, feedWheelPID; // just variables for the PID Controllers

  Boolean shootState = false; // true if the shooter should be running
  Boolean state = false; // true if motors are in use

  public launcher() {
    launchWheelPID = launchWheel.getPIDController(); // just a variable for the PID Controller
    launchWheelPID.setFeedbackDevice(launchWheel.getEncoder()); // what encoder to get speed from
    launchWheelPID.setP(6e-5); // acceleration amount
    launchWheelPID.setI(0); // acceleration speed
    launchWheelPID.setD(0); // deceleration speed
    launchWheelPID.setIZone(0); // ?
    launchWheelPID.setFF(0.000180); // ?
    launchWheelPID.setOutputRange(-1, 1); // max & min power for .set() command

    feedWheelPID = feedWheel.getPIDController(); // just a variable for the PID Controller
    feedWheelPID.setFeedbackDevice(feedWheel.getEncoder()); // what encoder to get speed from
    feedWheelPID.setP(6e-5); // acceleration amount
    feedWheelPID.setI(0); // acceleration speed
    feedWheelPID.setD(0); // deceleration speed
    feedWheelPID.setIZone(0); // ?
    feedWheelPID.setFF(0.000180); // ?
    feedWheelPID.setOutputRange(-1, 1); // max & min power for .set() command
  }

  public void setAmpPower(double power) {
    ampWheel.set(power);
  }

  public void intakeNote() {
    if (!state) { // if motors arent in use
      state = true; // motors are being used
      launchWheelPID.setReference(MotorConstants.kIntakeSpeed, ControlType.kVelocity); // set to (default) 500 RPM
      feedWheelPID.setReference(MotorConstants.kIntakeSpeed, ControlType.kVelocity); // set to (default) 500 RPM
    }
  }

  public void shootNote() {
    if (!state) { // if motors arent in use
      state = true; // motors are being used
      shootState = true; // we are trying to shoot
      launchWheelPID.setReference(MotorConstants.kShootSpeed, ControlType.kVelocity); // set shooter to (default) 5500 RPM
    }
  }

  public void stopMotors() {
    launchWheelPID.setReference(0, ControlType.kVelocity); // turns off shooter wheel
    feedWheelPID.setReference(0, ControlType.kVelocity); // turns off feed wheel
    state = false; shootState = false; // we arent using the motors or trying to shoot
  }

  private void updateShooter() {
    if (shootState) { // if we are trying to shoot
      if (launchWheel.getEncoder().getVelocity() >= MotorConstants.kShootSpeed - 25) { // if shooter is up to (default) 5475 RPM
        feedWheelPID.setReference(MotorConstants.kShootSpeed, ControlType.kVelocity); // set feed wheel to (default) 5500 RPM
        if (feedWheel.getEncoder().getVelocity() >= (MotorConstants.kShootSpeed) - 25) { // if feed wheel is up to (default) 5475 RPM
          shootState = false; // we are done shooting
        }
      }
    }
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Intake-Shooter State", state); // are the intake or shooter motors being used?
    SmartDashboard.putNumber("Launch Wheel Speed", launchWheel.getEncoder().getVelocity()); // prints launch wheel speed
    SmartDashboard.putNumber("Feed Wheel Speed", feedWheel.getEncoder().getVelocity()); // prints feed wheel speed

    updateShooter(); // calls updateShooter() so speeds are constantly being checked
  }
}
