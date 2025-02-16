package frc.robot.Utils;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class NetworkTableManager {
    private static NetworkTableManager instance;
    private final NetworkTable table;

    private NetworkTableManager() {
        table = NetworkTableInstance.getDefault().getTable("Robot");
    }

    public static NetworkTableManager getInstance() {
        if (instance == null) {
            instance = new NetworkTableManager();
        }
        return instance;
    }

    public void putNumber(String key, double value) {
        table.getEntry(key).setDouble(value);
    }

    public void putBoolean(String key, boolean value) {
        table.getEntry(key).setBoolean(value);
    }

    public void putString(String key, String value) {
        table.getEntry(key).setString(value);
    }
}

