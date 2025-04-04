package frc.robot.Commands.Auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.AlgaeSubsystem;

public class FloorIntakeOut extends Command {

    private final AlgaeSubsystem algaeSubsystem;

    private final Timer timer;
    
    public FloorIntakeOut(AlgaeSubsystem algaeSubsystem) {

        this.algaeSubsystem = algaeSubsystem;

        this.timer = new Timer();

    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
        algaeSubsystem.setAlgaeArmSpeed(-1);
    }

    @Override
    public void end(boolean interrupted) {
        timer.stop();
        algaeSubsystem.setAlgaeArmSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return timer.get() > 2;
    }
}
