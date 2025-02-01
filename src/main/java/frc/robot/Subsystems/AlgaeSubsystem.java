package frc.robot.Subsystems;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkMaxAlternateEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;

public class AlgaeSubsystem extends SubsystemBase {

    private final SparkMax m_algaeArm = new SparkMax(10, MotorType.kBrushless);
    private final SparkMax m_algaeIntake = new SparkMax(11, MotorType.kBrushless);

    private final SparkClosedLoopController m_algaeArmClosedLoop = m_algaeArm.getClosedLoopController();

    private double armSetpoint = 0;

    public AlgaeSubsystem() {
        m_algaeArm.configure(
            Configs.AlgaeSubsystem.algaeArmConfig,
            ResetMode.kResetSafeParameters, 
            PersistMode.kPersistParameters);

        m_algaeIntake.configure(
            Configs.AlgaeSubsystem.algaeArmConfig, 
            ResetMode.kResetSafeParameters, 
            PersistMode.kPersistParameters);
    }

    public void periodic() {
        m_algaeArmClosedLoop.setReference(armSetpoint, ControlType.kPosition);

        NetworkTableInstance ntInstance = NetworkTableInstance.getDefault();
        NetworkTable table = ntInstance.getTable("AlgaeSubsystem");

        table.getEntry("Arm Setpoint").setDouble(m_algaeArm.getAlternateEncoder().getPosition());
    }

    public void setAlgaeArmSpeed(double speed) {
        m_algaeArm.set(speed / 5);
    }

    public void setAlgaeIntakeSpeed(double speed) {
        m_algaeIntake.set(speed / 1.3);
    }

}