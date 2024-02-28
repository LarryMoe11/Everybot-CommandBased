// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class autonSelector extends SubsystemBase {
  SendableChooser<String> m_chooser = new SendableChooser<>(); // create new auton chooser
  public String[] autonChoices = {"do nothing", "launch drive", "launch", "drive"}; // choices in auton chooser

  /** Creates a new autonSelector. */
  public autonSelector() {
    m_chooser.setDefaultOption(autonChoices[0], autonChoices[0]); // sets default choice to first in choice list
    for (String choice:autonChoices) { m_chooser.addOption(choice, choice); } // add choices to chooser
    SmartDashboard.putData("Auto choices", m_chooser); // post chooser to ShuffleBoard
  }

  public String getChoice() {
    return m_chooser.getSelected();
  }

  @Override
  public void periodic() {}
}
