// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.drivetrain;

public class teleopDrive extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final drivetrain m_Drivetrain;
  XboxController m_driverController = new XboxController(OperatorConstants.kDriverControllerPort); // gets controller on (default) port 0

  double leftX, leftY, rightX, rightY;

  public teleopDrive(drivetrain subsystem) {
    m_Drivetrain = subsystem;

    addRequirements(m_Drivetrain);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    leftY = m_driverController.getLeftY();
    leftX = m_driverController.getLeftX();
    rightX = m_driverController.getRightX();
    rightY = m_driverController.getRightY();

    leftY = Math.max(Math.abs(leftY) - OperatorConstants.kDeadZone, 0) * Math.signum(leftY) * (OperatorConstants.kReverseDriveSticks?-1:1); // handles joystick deadzone
    leftX = Math.max(Math.abs(leftX) - OperatorConstants.kDeadZone, 0) * Math.signum(leftX) * (OperatorConstants.kReverseDriveSticks?-1:1); // handles joystick deadzone
    rightX = Math.max(Math.abs(rightX) - OperatorConstants.kDeadZone, 0) * Math.signum(rightX) * (OperatorConstants.kReverseDriveSticks?-1:1); // handles joystick deadzone
    rightY = Math.max(Math.abs(rightY) - OperatorConstants.kDeadZone, 0) * Math.signum(rightY) * (OperatorConstants.kReverseDriveSticks?-1:1); // handles joystick deadzone

    m_Drivetrain.arcadeDrive(leftY, rightX); // sets control mode to (default) arcade
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
