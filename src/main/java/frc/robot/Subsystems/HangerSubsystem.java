package frc.robot.Subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HangerSubsystem extends SubsystemBase {

    private final SparkMax m_hangerSparkMax = new SparkMax(9, MotorType.kBrushless);

    

    public HangerSubsystem() {
        
    }

    public void periodic() {

    }

    public void setHangerMotorSpeed(double speed) {
     m_hangerSparkMax.set(speed);

    }

}