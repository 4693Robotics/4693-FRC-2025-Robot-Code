package frc.robot.Subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;
import frc.robot.Constants.AlgaeSubsystemConstants;
import frc.robot.Utils.NetworkTableManager;

public class AlgaeSubsystem extends SubsystemBase {

    private final SparkMax m_algaeArm = new SparkMax(
        AlgaeSubsystemConstants.kAlgaeArmCanId,
        MotorType.kBrushless);

    private final SparkMax m_algaeIntake = new SparkMax(
        AlgaeSubsystemConstants.kAlgaeIntakeCanId,
        MotorType.kBrushless);

    public AlgaeSubsystem() {
        m_algaeArm.configure(
            Configs.AlgaeSubsystem.algaeArmConfig,
            ResetMode.kResetSafeParameters, 
            PersistMode.kPersistParameters);

        m_algaeIntake.configure(
            Configs.AlgaeSubsystem.algaeIntakeConfig, 
            ResetMode.kResetSafeParameters, 
            PersistMode.kPersistParameters);
    }

    public void periodic() {
        NetworkTableManager.getInstance().putNumber("AlgaeSubsystem/ArmSpeed" , m_algaeArm.get());
        NetworkTableManager.getInstance().putNumber("AlgaeSubsystem/ArmEncoder", m_algaeArm.getEncoder().getPosition());
        NetworkTableManager.getInstance().putNumber("AlgaeSubsystem/IntakeSpeed", m_algaeIntake.get());
    } 

    public void setAlgaeArmSpeed(double speed) {
        m_algaeArm.set(speed * 0.3);
    }

    public void setAlgaeIntakeSpeed(double speed) {
        m_algaeIntake.set(speed);
    }

}