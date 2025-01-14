package frc.robot.Commands;

import com.studica.frc.AHRS;

import edu.wpi.first.wpilibj2.command.Command;
import frc.Utils.Elastic;
import frc.Utils.Elastic.Notification.NotificationLevel;

public class ResetGyro extends Command{

    AHRS gyro;

    public ResetGyro(AHRS gyro) {
        this.gyro = gyro;
    }

    @Override
    public void initialize() {
        gyro.reset();
        Elastic.sendNotification(new Elastic.Notification(
            NotificationLevel.INFO,
            "Gyro Reset Succsess",
            "The Gyro has reset Successfuly"));
    }

    @Override
    public void end(boolean interrupted) {
        
    }
    
}
