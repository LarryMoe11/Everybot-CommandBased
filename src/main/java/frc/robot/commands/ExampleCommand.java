// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.drivetrain;

public class ExampleCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final drivetrain m_Drivetrain;
  XboxController m_driverController = new XboxController(OperatorConstants.kDriverControllerPort);

  public ExampleCommand(drivetrain subsystem) {
    m_Drivetrain = subsystem;

    addRequirements(m_Drivetrain);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_Drivetrain.setDrivePower(m_driverController.getLeftY(), m_driverController.getRightY());
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
