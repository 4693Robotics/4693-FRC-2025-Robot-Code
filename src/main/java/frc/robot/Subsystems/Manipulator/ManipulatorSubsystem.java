package frc.robot.Subsystems.Manipulator;

import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Utils.NetworkTableManager;

public class ManipulatorSubsystem extends SubsystemBase{
    
    public static enum ManipulatorPos {
        BARGE,
        A2,
        L3,
        A1,
        L2,
        L1,
        CORALIN,
        REST;

        public ManipulatorPos increment() {
            return switch (this) {
                case BARGE -> BARGE;
                case A2 -> BARGE;
                case L3 -> A2;
                case A1 -> L3;
                case L2 -> A1;
                case L1 -> L2;
                case CORALIN -> L1;
                case REST -> CORALIN;
            };
        }

        public ManipulatorPos decrement() {
            return switch (this) {
                case BARGE -> A2;
                case A2 -> L3;
                case L3 -> A1;
                case A1 -> L2;
                case L2 -> CORALIN;
                case CORALIN -> L1;
                case L1 -> REST;
                case REST -> REST;
            };
        }
    }

    private ManipulatorPos manipulatorPos = ManipulatorPos.REST;

    private final SparkMax m_Elevator = new SparkMax(
        ManipulatorConstants.kElevatorCANId,
        ManipulatorConstants.kElevatorMotorType);
     private final SparkMax m_Arm = new SparkMax(
        ManipulatorConstants.kArmCANId,
        ManipulatorConstants.kArmMotorType);
    private final SparkMax m_IntakeLeft = new SparkMax(
        ManipulatorConstants.kIntakeLeftCANId,
        ManipulatorConstants.kIntakeLeftMotorType);
    private final SparkMax m_IntakeRight = new SparkMax(
        ManipulatorConstants.kIntakeRightCANId,
        ManipulatorConstants.kIntakeRightMotorType);
    
    private final SparkClosedLoopController elevatorController = m_Elevator.getClosedLoopController();
    private final SparkClosedLoopController armController = m_Arm.getClosedLoopController();

    public ManipulatorSubsystem() {
        
        m_Elevator.configure(
            ManipulatorConfigs.elevatorConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);

        m_Arm.configure(
            ManipulatorConfigs.armConfig, 
            ResetMode.kResetSafeParameters, 
            PersistMode.kPersistParameters);

        m_IntakeLeft.configure(
            ManipulatorConfigs.intakeLeftConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);

        m_IntakeRight.configure(
            ManipulatorConfigs.intakeRightConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
    }

    private void updatePos() {
        double armSetpoint;
        switch (manipulatorPos) {
            case BARGE: armSetpoint = ManipulatorConstants.kBargeArmPoint;
            case A2: armSetpoint = ManipulatorConstants.kA2ArmPoint;
            case L3: armSetpoint = ManipulatorConstants.kL3ArmPoint;
            case A1: armSetpoint = ManipulatorConstants.kA1ArmPoint;
            case L2: armSetpoint = ManipulatorConstants.kL2ArmPoint;
            case L1: armSetpoint = ManipulatorConstants.kL1ArmPoint;
            case CORALIN: armSetpoint = ManipulatorConstants.kCoralInArmPoint;
            case REST: armSetpoint = ManipulatorConstants.kRestArmPoint;
            default: armSetpoint = ManipulatorConstants.kRestArmPoint;
        }
        armController.setReference(armSetpoint, ControlType.kPosition);
        NetworkTableManager.getInstance().putNumber("ManipulatorSubsystem/ArmSetpoint", armSetpoint);

        double elevatorSetpoint;
        switch (manipulatorPos) {
            case BARGE: elevatorSetpoint = ManipulatorConstants.kBargeElevatorPoint;
            case A2: elevatorSetpoint = ManipulatorConstants.kA2ElevatorPoint;
            case L3: elevatorSetpoint = ManipulatorConstants.kL3ElevatorPoint;
            case A1: elevatorSetpoint = ManipulatorConstants.kA1ElevatorPoint;
            case L2: elevatorSetpoint = ManipulatorConstants.kL2ElevatorPoint;
            case L1: elevatorSetpoint = ManipulatorConstants.kL1ElevatorPoint;
            case CORALIN: elevatorSetpoint = ManipulatorConstants.kCoralInElevatorPoint;
            case REST: elevatorSetpoint = ManipulatorConstants.kRestElevatorPoint;
            default: elevatorSetpoint = ManipulatorConstants.kRestElevatorPoint;
                
        }
        elevatorController.setReference(elevatorSetpoint, ControlType.kPosition);
        NetworkTableManager.getInstance().putNumber("ManipulatorSubsystem/ElevatorSetpoint", elevatorSetpoint);

        NetworkTableManager.getInstance().putString("ManipulatorSubsystem/ManipulatorPosition", manipulatorPos.toString());
    }

    @Override
    public void periodic() {
        NetworkTableManager.getInstance().putNumber("ManipulatorSubsystem/ElevatorEncoder", m_Elevator.getAlternateEncoder().getPosition());
        NetworkTableManager.getInstance().putNumber("ManipulatorSubsystem/ArmSetpoint", m_Arm.getAbsoluteEncoder().getPosition());
    }

    public void setIntakeSpeed(double speed) {
        m_IntakeLeft.set(speed*0.5);
        m_IntakeRight.set(speed*0.5);
    }

    public void incrementPos() {
        manipulatorPos.increment();
        updatePos();
    }

    public void decrementPos() {
        manipulatorPos.decrement();
        updatePos();
    }

    public void setPos(ManipulatorPos pos) {
        manipulatorPos = pos;
        updatePos();
    }
}


