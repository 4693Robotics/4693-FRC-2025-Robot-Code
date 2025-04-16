// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.config.PIDConstants;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Commands.ManipulatorCommand;
import frc.robot.Commands.AlgaeSubsystemDefault;
import frc.robot.Commands.DriveSubsytemDefault;
import frc.robot.Commands.Auto.FloorIntakeOut;
import frc.robot.ControlsManager.ControlProfile;
import frc.robot.Subsystems.Drive.DriveSubsystem;
import frc.robot.Subsystems.Intake.IntakeSubystem;
import frc.robot.Subsystems.Manipulator.ManipulatorSubsystem;
import frc.robot.Subsystems.Manipulator.ManipulatorSubsystem.ManipulatorPos;
import frc.robot.Utils.NetworkTableManager;

public class RobotContainer {

  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final IntakeSubystem m_robotAlgaeSubsystem = new IntakeSubystem();
  private final ManipulatorSubsystem m_robotManipulator = new ManipulatorSubsystem();
  //private final VisionSubsystem m_robotVision = new VisionSubsystem();

  private final CommandXboxController m_driveController = new CommandXboxController(0);
  private final CommandXboxController m_subsystemController = new CommandXboxController(1);

  private PowerDistribution m_PDH = new PowerDistribution(1, ModuleType.kRev);

  @SuppressWarnings("unused")
  private final UsbCamera m_camera = CameraServer.startAutomaticCapture();

  private SendableChooser<Command> autoChooser;
  private final SendableChooser<ControlProfile> profileChooser = new SendableChooser<>();
  public RobotContainer() {
    configureBindings();
  
    configureDefaultCommands();
  
    configurePathPlanner();

    configureNotifications();
  }
  
    public void periodic() {
      NetworkTableManager.getInstance().putBoolean("OI/DriveControllerConnected", m_driveController.isConnected());
      NetworkTableManager.getInstance().putBoolean("OI/SubsystemControllerConnected", m_subsystemController.isConnected());
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
        
      m_robotDrive.setDefaultCommand(new DriveSubsytemDefault(m_robotDrive, m_driveController));
  
      m_robotAlgaeSubsystem.setDefaultCommand(
        new AlgaeSubsystemDefault(m_robotAlgaeSubsystem, m_subsystemController)
      );

      m_robotManipulator.setDefaultCommand(
        new ManipulatorCommand(m_robotManipulator, m_subsystemController)
      );
    }

    RobotConfig robotConfig;
    
  
    public void configurePathPlanner() {
      try {
        robotConfig = RobotConfig.fromGUISettings();
      } catch (Exception e) {
        e.printStackTrace();
      }

      AutoBuilder.configure(
        m_robotDrive::getPose,
        m_robotDrive::resetOdometry,
        m_robotDrive::getCurrentspeeds,
        (speeds, feedforwards) -> m_robotDrive.setCurrentspeeds(speeds),
        new PPHolonomicDriveController(
          new PIDConstants(5.0), 
          new PIDConstants(5.0)),
        robotConfig,
        () -> {
          var alliance = DriverStation.getAlliance();
          if (alliance.isPresent()) {
            return alliance.get() == DriverStation.Alliance.Red;
          }
          return false;
        },
        m_robotDrive);

        NamedCommands.registerCommand("L1", new InstantCommand(() -> m_robotManipulator.setPos(ManipulatorPos.L1), m_robotManipulator));
        NamedCommands.registerCommand("L2", new InstantCommand(() -> m_robotManipulator.setPos(ManipulatorPos.L2), m_robotManipulator));
        NamedCommands.registerCommand("L3", new InstantCommand(() -> m_robotManipulator.setPos(ManipulatorPos.L3), m_robotManipulator));
        NamedCommands.registerCommand("coralin", new InstantCommand(() -> m_robotManipulator.setPos(ManipulatorPos.CORALIN), m_robotManipulator));
        NamedCommands.registerCommand("A1", new InstantCommand(() -> m_robotManipulator.setPos(ManipulatorPos.A1), m_robotManipulator));
        NamedCommands.registerCommand("A2", new InstantCommand(() -> m_robotManipulator.setPos(ManipulatorPos.A2), m_robotManipulator));
        NamedCommands.registerCommand("Barge", new InstantCommand(() -> m_robotManipulator.setPos(ManipulatorPos.BARGE), m_robotManipulator));
        NamedCommands.registerCommand("Intake On", new InstantCommand(() -> m_robotManipulator.setIntakeSpeed(1), m_robotManipulator));
        NamedCommands.registerCommand("Intake Out", new InstantCommand(() -> m_robotManipulator.setIntakeSpeed(-1), m_robotManipulator));
        NamedCommands.registerCommand("Intake Stop", new InstantCommand(() -> m_robotManipulator.setIntakeSpeed(0), m_robotManipulator));
        NamedCommands.registerCommand("Floor Intake Out", new FloorIntakeOut(m_robotAlgaeSubsystem));
  
        autoChooser = AutoBuilder.buildAutoChooser();
  }

  private void configureDashboard() {
    NetworkTableManager.putSendable(autoChooser);
    NetworkTableManager.putSendable(profileChooser);
  }

  private void configureNotifications() {
    
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();    
  }
}
