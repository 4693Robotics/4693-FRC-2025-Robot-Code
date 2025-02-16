package frc.robot.Utils;

import frc.robot.Utils.Elastic.Notification;
import frc.robot.Utils.Elastic.Notification.NotificationLevel;

public class ElasticAlerts {
    
    public class GyroAlerts {

        public static void gyroConnectedAlert() {
            Elastic.sendNotification(new Notification(
                NotificationLevel.INFO,
                "Gyro Connected",
                "The Gyro has connected to the robot"));
        }

        public static void gyroDisconectedAlert() {
            Elastic.sendNotification(new Notification(
                NotificationLevel.ERROR,
                "Gyro Disconnected",
                "The Gyro has disconnected to the robot"));
        }
    }
}
