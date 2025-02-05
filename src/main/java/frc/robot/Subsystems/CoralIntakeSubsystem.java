package frc.robot.Subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;

public class CoralIntakeSubsystem extends SubsystemBase {

    private SparkMax m_nuckleMotor = new SparkMax(20, MotorType.kBrushless);
    private SparkMax m_intakeMotorLeft = new SparkMax(21, MotorType.kBrushless);
    private SparkMax m_intakeMotorRight = new SparkMax(22, MotorType.kBrushless);

    public CoralIntakeSubsystem() {

        m_nuckleMotor.configure(
            Configs.CoralIntakeSubsystem.nuckleMotorConfig, 
            ResetMode.kResetSafeParameters, 
            PersistMode.kNoPersistParameters);

    }
    
    public void setNuckleSpeed(double speed) {
        m_nuckleMotor.set(speed * 0.1);
    } 

    public void setCoralIntakeSpeed(double speed) {
        m_intakeMotorLeft.set(speed);
        m_intakeMotorRight.set(speed);
    }
}
