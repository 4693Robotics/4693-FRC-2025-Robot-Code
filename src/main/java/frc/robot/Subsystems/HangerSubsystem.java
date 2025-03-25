package frc.robot.Subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;

public class HangerSubsystem extends SubsystemBase {

    private final SparkMax m_hanger = new SparkMax(40, MotorType.kBrushless); 

    public HangerSubsystem() {
        m_hanger.configure(
            Configs.HangerSubsystem.hangerConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
    }

    public void setHangerSpeed(double speed) {
        m_hanger.set(speed);
    }
}
