// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Commands.AlgaeSubsystemDefault;
import frc.robot.Commands.CoralIntakeSubsystemDefault;
import frc.robot.Commands.ElevatorSubsystemDefault;
import frc.robot.Subsystems.CoralIntakeSubsystem;
import frc.robot.Constants.OIConstants;
import frc.robot.Subsystems.DriveSubsystem;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.VisionSubsystem;
import frc.robot.Utils.NetworkTableManager;
import frc.robot.Utils.ElasticAlerts.ControllerAlerts;
import frc.robot.Subsystems.AlgaeSubsystem;

public class RobotContainer {

  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final AlgaeSubsystem m_robotAlgaeSubsystem = new AlgaeSubsystem();
  private final CoralIntakeSubsystem m_robotCoralIntakeSubsystem = new CoralIntakeSubsystem();
  private final ElevatorSubsystem m_robotElevatorSubsystem = new ElevatorSubsystem();
  private final VisionSubsystem m_robotVision = new VisionSubsystem();

  private final CommandXboxController m_driveController = new CommandXboxController(0);
  private final CommandXboxController m_subsystemController = new CommandXboxController(1);

  private PowerDistribution m_PDH = new PowerDistribution(1, ModuleType.kRev);

  private ComplexWidget PDHWidget = Shuffleboard.getTab("robot").add(m_PDH);

  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  public RobotContainer() {

    configureNotifications();

    configureBindings();

    configureDefaultCommands();
  }

  public void periodic() {
    NetworkTableManager.getInstance().putBoolean("OI/DriveControllerConnected", m_driveController.isConnected());
    NetworkTableManager.getInstance().putBoolean("OI/SubsystemControllerConnected", m_subsystemController.isConnected());

    m_driveController.setRumble(RumbleType.kBothRumble, 0);
  }

  private void configureNotifications() {
    new Trigger(m_driveController::isConnected)
      .onTrue(new InstantCommand(
        () -> ControllerAlerts.DriveController.driveControllerConnectedAlert()))
      .onFalse(new InstantCommand(
        () -> ControllerAlerts.DriveController.driveControllerDisconnectedAlert()));

  new Trigger(m_subsystemController::isConnected)
    .onTrue(new InstantCommand(
      () -> ControllerAlerts.SubsystemController.subsystemControllerConnectAlert()))
    .onFalse(new InstantCommand(
      () -> ControllerAlerts.SubsystemController.subsystemControllerDisconnectAlert()));
  }

  private void configureBindings() {

    m_driveController.rightBumper().whileTrue(new RunCommand(
      () -> m_robotDrive.setX(),
      m_robotDrive));

    m_driveController.leftBumper().onTrue(new InstantCommand(
      () -> m_robotDrive.zeroHeading(),
      m_robotDrive));
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

    m_robotAlgaeSubsystem.setDefaultCommand(
      new AlgaeSubsystemDefault(m_robotAlgaeSubsystem, m_subsystemController)
    );

    m_robotCoralIntakeSubsystem.setDefaultCommand(
      new CoralIntakeSubsystemDefault(m_robotCoralIntakeSubsystem, m_subsystemController)
    );

    m_robotElevatorSubsystem.setDefaultCommand(
      new ElevatorSubsystemDefault(m_robotElevatorSubsystem, m_subsystemController)
    );
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
    
  }
}
