// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.teleopDrive;
import frc.robot.subsystems.autonSelector;
import frc.robot.subsystems.drivetrain;
import frc.robot.subsystems.launcher;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final drivetrain m_Drivetrain = new drivetrain();
  private final autonSelector m_AutonSelector = new autonSelector();
  private final launcher m_Launcher = new launcher();

  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);

  public RobotContainer() {
    m_Drivetrain.setDefaultCommand(new teleopDrive(m_Drivetrain));
    configureBindings();
  }

  private void configureBindings() {
    m_driverController.a().whileTrue(Commands.startEnd(() -> m_Launcher.shootNote(), () -> m_Launcher.stopMotors())); // shoot if "a" is held down
    m_driverController.x().whileTrue(Commands.startEnd(() -> m_Launcher.intakeNote(), () -> m_Launcher.stopMotors())); // intake if "x" is held down
    m_driverController.leftTrigger(0.5).whileTrue(Commands.startEnd(() -> m_Launcher.setAmpPower(MotorConstants.kAmpIntakePower), () -> m_Launcher.setAmpPower(0))); // amp intake if left trigger is held down
    m_driverController.rightTrigger(0.5).whileTrue(Commands.startEnd(() -> m_Launcher.setAmpPower(MotorConstants.kAmpOuttakePower), () -> m_Launcher.setAmpPower(0))); // amp outtake if right trigger is held down
  }

  public Command getAutonomousCommand() {
    return Autos.exampleAuto(m_Drivetrain, m_Launcher, m_AutonSelector);
  }
}
