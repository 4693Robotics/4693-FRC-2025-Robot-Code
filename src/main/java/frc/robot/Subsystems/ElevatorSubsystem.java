package frc.robot.Subsystems;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;
import frc.robot.Utils.NetworkTableManager;

public class ElevatorSubsystem extends SubsystemBase {

    private final SparkMax m_Elevator = new SparkMax(30, MotorType.kBrushless);

    private final SparkClosedLoopController m_ElevatorClosedLoop = m_Elevator.getClosedLoopController();

    private double elevatorSetpoint = 0;

    public ElevatorSubsystem() {
        m_Elevator.configure(
            Configs.ElevatorSubsystem.elevatorConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
    }
    
    public void periodic() {
        m_ElevatorClosedLoop.setReference(elevatorSetpoint, ControlType.kPosition);

        NetworkTableManager.getInstance().putNumber("ElevatorSubsystem/Encoder Value", m_Elevator.getAlternateEncoder().getPosition());
    } 

    public void setElevatorSetpoint(double setpoint) {
        elevatorSetpoint = setpoint;
    }
}
