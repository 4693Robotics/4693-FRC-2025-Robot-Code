// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants.OIConstants;
import frc.robot.Subsystems.DriveSubsystem;
import frc.robot.Subsystems.HangerSubsystem;

public class RobotContainer {

  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final HangerSubsystem m_HangerSubsystem = new HangerSubsystem();

  private final XboxController m_driveController = new XboxController(0);
  private final XboxController m_subsystemsController = new XboxController(1);

  public RobotContainer() {

    configureBindings();

    configureDefaultCommands();

  }

  public void periodic() {
    
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

    m_HangerSubsystem.setDefaultCommand(
      new RunCommand(() -> m_HangerSubsystem.setHangerMotorSpeed(
        m_subsystemsController.getLeftY()),
        m_HangerSubsystem)
    );
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
    
  }
}
