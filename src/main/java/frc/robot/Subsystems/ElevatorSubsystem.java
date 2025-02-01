package frc.robot.Subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {

    private final SparkMax m_ElevatorRight = new SparkMax(30, MotorType.kBrushless);
    private final SparkMax m_ElevatorLeft = new SparkMax(31, MotorType.kBrushless);

    public ElevatorSubsystem() {

    }
}
