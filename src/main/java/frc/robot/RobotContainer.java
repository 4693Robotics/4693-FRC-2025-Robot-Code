// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants.OIConstants;
import frc.robot.Subsystems.DriveSubsystem;

public class RobotContainer {

  private final DriveSubsystem m_robotDrive = new DriveSubsystem();

  private final XboxController m_driveController = new XboxController(0);
  private final XboxController m_subsystemsController = new XboxController(1);

  private final ShuffleboardTab telopTab = Shuffleboard.getTab("Telop");

  private boolean testBool = false;
  private Timer timer = new Timer();

  private final SimpleWidget testBoolWidget = telopTab
  .add("Boolean", testBool)
  .withWidget(BuiltInWidgets.kBooleanBox);


  public RobotContainer() {

    configureBindings();

    configureDefaultCommands();
    timer.start();
  }

  public void periodic() {
    if (timer.get() % 2 < 1) {
      testBool = true;
    } else {
      testBool = false;
    }

    testBoolWidget.getEntry().setBoolean(testBool);
  }

  private void configureBindings() {
    
  }

  private void configureDashboard() {
    
  }

  private void configureDefaultCommands() {
     m_robotDrive.setDefaultCommand(
      new RunCommand(
        () -> m_robotDrive.drive(
          -MathUtil.applyDeadband(m_driveController.getLeftY(), OIConstants.kDriveDeadband),
          -MathUtil.applyDeadband(m_driveController.getLeftX(), OIConstants.kDriveDeadband),
          -MathUtil.applyDeadband(m_driveController.getRightX(), OIConstants.kDriveDeadband),
          true,
          true),
          m_robotDrive));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
    
  }
}
