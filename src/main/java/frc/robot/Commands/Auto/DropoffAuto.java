package main.java.frc.robot.Commands.Auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.CoralIntakeSubsystem;
import frc.robot.Subsystems.DriveSubsystem;

public class DropoffAuto extends Command {

    private final CoralIntakeSubsystem coralIntakeSubsystem;

    private final Timer timer;

    public DropoffAuto(frc.robot.Subsystems.CoralIntakeSubsystem coralIntakeSubsystem) {

        this.coralIntakeSubsystem = coralIntakeSubsystem;
        timer = new Timer();

        addRequirements(coralIntakeSubsystem);
    }
}
