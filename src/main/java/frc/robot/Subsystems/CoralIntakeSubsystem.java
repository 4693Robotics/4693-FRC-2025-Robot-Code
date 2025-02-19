package frc.robot.Subsystems;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;

public class CoralIntakeSubsystem extends SubsystemBase {

    private final SparkMax m_nuckleMotor = new SparkMax(20, MotorType.kBrushless);
    private final SparkMax m_intakeMotorLeft = new SparkMax(21, MotorType.kBrushless);
    private final SparkMax m_intakeMotorRight = new SparkMax(22, MotorType.kBrushless);

    private SparkClosedLoopController m_nuckleClosedLoopController = m_nuckleMotor.getClosedLoopController();

    private double nuckleSetpoint = 0;

    public CoralIntakeSubsystem() {

        m_nuckleMotor.configure(
            Configs.CoralIntakeSubsystem.nuckleMotorConfig, 
            ResetMode.kResetSafeParameters, 
            PersistMode.kPersistParameters);

    }

    @Override
    public void periodic() {
        m_nuckleClosedLoopController.setReference(nuckleSetpoint, ControlType.kPosition);

        NetworkTableInstance ntInstance = NetworkTableInstance.getDefault();
        NetworkTable table = ntInstance.getTable("CoralSubsystem");

        table.getEntry("nuckleAngle").setDouble(nuckleSetpoint);
        table.getEntry("EncoderNuckleAngle").setDouble(m_nuckleMotor.getAbsoluteEncoder().getPosition());
    }
    
    public void setNucklePoint(double setpoint) {
        nuckleSetpoint = setpoint;
    }

    public void setCoralIntakeSpeed(double speed) {
        m_intakeMotorLeft.set(speed);
        m_intakeMotorRight.set(speed);
    }
}
