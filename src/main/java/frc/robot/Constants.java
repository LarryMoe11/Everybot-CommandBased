// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class MotorConstants {
    public static final int kLeftRearID = 1;
    public static final int kRightRearID = 3;

    public static final int kLeftFrontID = 2;
    public static final int kRightFrontID = 4;

    public static final int kAmpWheelID = 7;
    public static final int kLaunchWheelID = 6;
    public static final int kFeedWheelID = 5;

    public static final double kIntakeSpeed = 500;
    public static final double kShootSpeed = 5500;

    public static final double kAmpIntakePower = -0.5;
    public static final double kAmpOuttakePower = 0.5;
  }
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
    public static final boolean kReverseDriveSticks = true;
    public static final double kDeadZone = 0.1; // joystick deadzone
  }
}
