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

    public class ControllerAlerts {
        public class DriveController {
            public static void driveControllerConnectedAlert() {
                Elastic.sendNotification(new Notification(
                    NotificationLevel.INFO,
                    "Drive Controller Connected",
                    "The Driver Controller has connected"));
            }

            public static void driveControllerDisconnectedAlert() {
                Elastic.sendNotification(new Notification(
                    NotificationLevel.WARNING,
                    "Drive Controller Disconnected",
                    "The Driver Controller has disconnected"));
            }
        }

        public class SubsystemController {
            public static void subsystemControllerConnectAlert() {
                Elastic.sendNotification(new Notification(
                    NotificationLevel.INFO,
                    "Subsystem Controller Connected",
                    "The Subsystem Controller has connected"));
            }

            public static void subsystemControllerDisconnectAlert() {
                Elastic.sendNotification(new Notification(
                    NotificationLevel.INFO,
                    "Subsystem Controller Disconnected",
                    "The Subsystem Controller has disconnected"));
            }
        }
    }
}
