// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.autonSelector;
import frc.robot.subsystems.drivetrain;
import frc.robot.subsystems.launcher;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command exampleAuto(drivetrain m_Drivetrain, launcher m_Launcher , autonSelector m_AutonSelector) {
    if (m_AutonSelector.getChoice() == m_AutonSelector.autonChoices[0]) { // do nothing
      return Commands.waitSeconds(15); // wait 15 seconds (length of auton)

    } else if (m_AutonSelector.getChoice() == m_AutonSelector.autonChoices[1]) { // launch drive
      return Commands.sequence(
        Commands.run(() -> m_Launcher.shootNote()), // shoot note
        Commands.waitSeconds(5), // wait 5 seconds
        Commands.run(() -> m_Launcher.stopMotors()), // stop launcher
        Commands.run(() -> m_Drivetrain.arcadeDrive(-0.5, 0)), // drive backwards at 1/2 speed
        Commands.waitSeconds(2), // wait 2 seconds
        Commands.run(() -> m_Drivetrain.arcadeDrive(0, 0)) // stop
      );

    } else if (m_AutonSelector.getChoice() == m_AutonSelector.autonChoices[2]) { // launch
      return Commands.sequence(
        Commands.run(() -> m_Launcher.shootNote()), // shoot note
        Commands.waitSeconds(5), // wait 5 seconds
        Commands.run(() -> m_Launcher.stopMotors()) // stop launcher
      );

    } else if (m_AutonSelector.getChoice() == m_AutonSelector.autonChoices[3]) { // drive
      return Commands.sequence(
        Commands.run(() -> m_Drivetrain.arcadeDrive(-0.5, 0)), // drive backwards at 1/2 speed
        Commands.waitSeconds(2), // wait 2 seconds
        Commands.run(() -> m_Drivetrain.arcadeDrive(0, 0)) // stop
      );
    }

    return null;
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
