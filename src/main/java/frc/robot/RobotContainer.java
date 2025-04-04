// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.config.PIDConstants;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Commands.AlgaeManipSubsytemDefault;
import frc.robot.Commands.AlgaeSubsystemDefault;
import frc.robot.Commands.DriveSubsytemDefault;
import frc.robot.Commands.Auto.FloorIntakeOut;
import frc.robot.Constants.OIConstants;
import frc.robot.Subsystems.DriveSubsystem;
import frc.robot.Subsystems.HangerSubsystem;
import frc.robot.Utils.NetworkTableManager;
import frc.robot.Utils.ElasticAlerts.ControllerAlerts;
import frc.robot.Subsystems.AlgaeManipSubsystem;
import frc.robot.Subsystems.AlgaeSubsystem;

public class RobotContainer {

  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final AlgaeSubsystem m_robotAlgaeSubsystem = new AlgaeSubsystem();
  private final HangerSubsystem m_robotHangerSubsystem = new HangerSubsystem();
  private final AlgaeManipSubsystem m_algaeManipSubsystem = new AlgaeManipSubsystem();
  //private final VisionSubsystem m_robotVision = new VisionSubsystem();

  private final CommandXboxController m_driveController = new CommandXboxController(0);
  private final CommandXboxController m_subsystemController = new CommandXboxController(1);

  private PowerDistribution m_PDH = new PowerDistribution(1, ModuleType.kRev);

  @SuppressWarnings("unused")
  private final UsbCamera m_camera = CameraServer.startAutomaticCapture();

  @SuppressWarnings("unused")
  private ComplexWidget PDHWidget = Shuffleboard.getTab("robot").add(m_PDH);

  private SendableChooser<Command> autoChooser;
  
    public RobotContainer() {
  
      configureNotifications();
  
      configureBindings();
  
      configureDefaultCommands();
  
      configurePathPlanner();
    }
  
    public void periodic() {
      NetworkTableManager.getInstance().putBoolean("OI/DriveControllerConnected", m_driveController.isConnected());
      NetworkTableManager.getInstance().putBoolean("OI/SubsystemControllerConnected", m_subsystemController.isConnected());
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
       /*m_robotDrive.setDefaultCommand(
        new RunCommand(
          () -> m_robotDrive.drive(
            (1 - (m_driveController.getRightTriggerAxis() * 0.5)) * -MathUtil.applyDeadband(m_driveController.getLeftY(), OIConstants.kDriveDeadband),
            (1 - (m_driveController.getRightTriggerAxis() * 0.5)) * -MathUtil.applyDeadband(m_driveController.getLeftX(), OIConstants.kDriveDeadband),
            (1 - (m_driveController.getRightTriggerAxis() * 0.5)) * -MathUtil.applyDeadband(m_driveController.getRightX(), OIConstants.kDriveDeadband),
            /*m_driveController.button(1).toggleOnTrue(new Command() {
              
            }).getAsBoolean() true,
            true),
          m_robotDrive));  */

        m_robotDrive.setDefaultCommand(new DriveSubsytemDefault(m_robotDrive, m_driveController));
  
      m_robotAlgaeSubsystem.setDefaultCommand(
        new AlgaeSubsystemDefault(m_robotAlgaeSubsystem, m_subsystemController)
      );

      m_algaeManipSubsystem.setDefaultCommand(
        new AlgaeManipSubsytemDefault(m_algaeManipSubsystem, m_subsystemController)
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

        

        NamedCommands.registerCommand("L1", new InstantCommand(() -> m_algaeManipSubsystem.l1(), m_algaeManipSubsystem));
        NamedCommands.registerCommand("L2", new InstantCommand(() -> m_algaeManipSubsystem.l2(), m_algaeManipSubsystem));
        NamedCommands.registerCommand("L3", new InstantCommand(() -> m_algaeManipSubsystem.l3(), m_algaeManipSubsystem));
        NamedCommands.registerCommand("coralin", new InstantCommand(() -> m_algaeManipSubsystem.coralin(), m_algaeManipSubsystem));
        NamedCommands.registerCommand("A1", new InstantCommand(() -> m_algaeManipSubsystem.a1(), m_algaeManipSubsystem));
        NamedCommands.registerCommand("A2", new InstantCommand(() -> m_algaeManipSubsystem.a2(), m_algaeManipSubsystem));
        NamedCommands.registerCommand("Barge", new InstantCommand(() -> m_algaeManipSubsystem.b(), m_algaeManipSubsystem));
        NamedCommands.registerCommand("Intake On", new InstantCommand(() -> m_algaeManipSubsystem.setIntakeSpeed(1), m_algaeManipSubsystem));
        NamedCommands.registerCommand("Intake Out", new InstantCommand(() -> m_algaeManipSubsystem.setIntakeSpeed(-1), m_algaeManipSubsystem));
        NamedCommands.registerCommand("Intake Stop", new InstantCommand(() -> m_algaeManipSubsystem.setIntakeSpeed(0), m_algaeManipSubsystem));
        NamedCommands.registerCommand("Floor Intake Out", new FloorIntakeOut(m_robotAlgaeSubsystem));

  
        autoChooser = AutoBuilder.buildAutoChooser();
        Shuffleboard.getTab("robot").add(autoChooser);
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();    
  }
}
