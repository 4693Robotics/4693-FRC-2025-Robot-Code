package frc.robot.Subsystems;

import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;
import frc.robot.Constants.CoralIntakeSubsystemConstants;
import frc.robot.Utils.NetworkTableManager;

public class AlgaeManipSubsystem extends SubsystemBase{
    
     private final SparkMax m_Elevator = new SparkMax(30, MotorType.kBrushless);

    private final SparkClosedLoopController m_ElevatorClosedLoop = m_Elevator.getClosedLoopController();

    private double elevatorSetpoint = 0;

     private final SparkMax m_arm = new SparkMax(
        CoralIntakeSubsystemConstants.kNuckleCanId,
        MotorType.kBrushless);
    private final SparkMax m_intakeLeft = new SparkMax(
        CoralIntakeSubsystemConstants.kIntakeLeft,
        MotorType.kBrushless);
    private final SparkMax m_intakeRight = new SparkMax(
        CoralIntakeSubsystemConstants.kIntakeRight,
        MotorType.kBrushless);

    private SparkClosedLoopController m_nuckleClosedLoopController = m_arm.getClosedLoopController();

    private double nuckleSetpoint = 185;

    public AlgaeManipSubsystem() {
        
        m_Elevator.configure(
            Configs.ElevatorSubsystem.elevatorConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);

        m_arm.configure(
            Configs.CoralIntakeSubsystem.nuckleConfig, 
            ResetMode.kResetSafeParameters, 
            PersistMode.kPersistParameters);

        m_intakeLeft.configure(
            Configs.CoralIntakeSubsystem.intakeLeftConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);

        m_intakeRight.configure(
            Configs.CoralIntakeSubsystem.intakeRightConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
    }

    public void periodic() {

        m_ElevatorClosedLoop.setReference(elevatorSetpoint, ControlType.kPosition);

        NetworkTableManager.getInstance().putNumber("ElevatorSubsystem/Encoder Value", m_Elevator.getAlternateEncoder().getPosition());

        m_nuckleClosedLoopController.setReference(nuckleSetpoint, ControlType.kPosition);

        NetworkTableManager.getInstance().putNumber("CoralIntakeSubsystem/NuckleSetpoint", nuckleSetpoint);
        NetworkTableManager.getInstance().putNumber("CoralIntakeSubsystem/NuckleEncoder", m_arm.getAbsoluteEncoder().getPosition());
    } 

    public void setElevatorSetpoint(double setpoint) {
        elevatorSetpoint = setpoint;
    }

    public void setArmPoint(double setpoint) {
        nuckleSetpoint = setpoint;
    }

    public void setIntakeSpeed(double speed) {
        m_intakeLeft.set(speed*0.5);
        m_intakeRight.set(speed*0.5);
    }

    public void l1() {
        elevatorSetpoint = 115;
        nuckleSetpoint = 475;
    }

    public void l2() {
        
    }

    public void l3()    {
        elevatorSetpoint = 465;
        nuckleSetpoint = 475;
    }

    public void l4() {
        elevatorSetpoint = 850;
        nuckleSetpoint = 580;
    }

    public void a1() {

    }

    public void a2() {
        
    }
}
