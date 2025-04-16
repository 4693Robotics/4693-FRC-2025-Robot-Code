package frc.robot.Subsystems.Intake;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;
import frc.robot.Utils.NetworkTableManager;

public class IntakeSubystem extends SubsystemBase {

    private final SparkMax m_algaeArm = new SparkMax(
        IntakeConstants.kArmCANId,
        IntakeConstants.kArmMotorType);
    private final SparkMax m_algaeIntake = new SparkMax(
        IntakeConstants.kIntakeCANId,
        IntakeConstants.kIntakeMotorType);

    public IntakeSubystem() {
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
        NetworkTableManager.getInstance().putNumber("IntakeSubsystem/ArmSpeed" , m_algaeArm.get());
        NetworkTableManager.getInstance().putNumber("IntakeSubsystem/IntakeSpeed", m_algaeIntake.get());
    } 

    public void setArmSpeed(double speed) {
        m_algaeArm.set(speed * 0.3);
    }

    public void setIntakeSpeed(double speed) {
        m_algaeIntake.set(speed);
    }

}