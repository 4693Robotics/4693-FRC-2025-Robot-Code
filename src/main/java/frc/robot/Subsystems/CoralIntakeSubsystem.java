package frc.robot.Subsystems;

import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;
import frc.robot.Constants.CoralIntakeSubsystemConstants;
import frc.robot.Utils.NetworkTableManager;

public class CoralIntakeSubsystem extends SubsystemBase {

    private final SparkMax m_nuckle = new SparkMax(
        CoralIntakeSubsystemConstants.kNuckleCanId,
        MotorType.kBrushless);
    private final SparkMax m_intakeLeft = new SparkMax(
        CoralIntakeSubsystemConstants.kIntakeLeft,
        MotorType.kBrushless);
    private final SparkMax m_intakeRight = new SparkMax(
        CoralIntakeSubsystemConstants.kIntakeRight,
        MotorType.kBrushless);

    private SparkClosedLoopController m_nuckleClosedLoopController = m_nuckle.getClosedLoopController();

    private double nuckleSetpoint = 75;

    private final Rev2mDistanceSensor m_DistanceSensor = new Rev2mDistanceSensor(Port.kOnboard);

    public CoralIntakeSubsystem() {

        m_nuckle.configure(
            Configs.CoralIntakeSubsystem.nuckleConfig, 
            ResetMode.kResetSafeParameters, 
            PersistMode.kPersistParameters);

        m_intakeLeft.configure(
            Configs.CoralIntakeSubsystem.intakeLeftConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);

        m_intakeRight.configure(
            Configs.CoralIntakeSubsystem.intakeRightConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
    }

    @Override
    public void periodic() {
        m_nuckleClosedLoopController.setReference(nuckleSetpoint, ControlType.kPosition);

        NetworkTableManager.getInstance().putNumber("CoralIntakeSubsystem/NuckleSetpoint", nuckleSetpoint);
        NetworkTableManager.getInstance().putNumber("CoralIntakeSubsystem/NuckleEncoder", m_nuckle.getAbsoluteEncoder().getPosition());
        NetworkTableManager.getInstance().putNumber("CoralIntakeSubsystem/Distance Sensor", m_DistanceSensor.isRangeValid() ? m_DistanceSensor.getRange() : -1);
    }
    
    public void setNucklePoint(double setpoint) {
        nuckleSetpoint = setpoint;
    }

    public void setCoralIntakeSpeed(double speed) {
        m_intakeLeft.set(speed*0.2);
        m_intakeRight.set(speed*0.2);
    }

    public void getRange() {
        m_DistanceSensor.getRange();
    }
}
