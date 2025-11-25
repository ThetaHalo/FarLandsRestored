package lol.eps.farlandsRestored.Manager;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

public class FLRestoredOptions implements ConfigurationSerializable {

    private final String worldName;

    private final int lowX;
    private final int lowZ;
    private final int highX;
    private final int highZ;

    private final boolean isPaper;

    public FLRestoredOptions(
            String worldName,
            int lowX, int lowZ,
            int highX, int highZ,
            boolean isPaper
    ) {
        this.worldName = worldName;
        this.lowX = lowX;
        this.lowZ = lowZ;
        this.highX = highX;
        this.highZ = highZ;
        this.isPaper = isPaper;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<>();

        data.put("world-name", this.worldName);
        data.put("lowX", this.lowX);
        data.put("lowZ", this.lowZ);
        data.put("highX", this.highX);
        data.put("highZ", this.highZ);
        data.put("paper", this.isPaper);

        return data;
    }

    public static FLRestoredOptions deserialize(Map<String, Object> args) {
        return new FLRestoredOptions(
                (String) args.getOrDefault("world-name", "world"),
                (int) args.getOrDefault("lowX", -12550824),
                (int) args.getOrDefault("lowZ", -12550824),
                (int) args.getOrDefault("highX", 12550824),
                (int) args.getOrDefault("highZ", 12550824),
                (boolean) args.getOrDefault("paper", true)
        );
    }

    public String getWorldName() { return worldName; }
    public int getLowX() { return lowX; }
    public int getLowZ() { return lowZ; }
    public int getHighX() { return highX; }
    public int getHighZ() { return highZ; }
    public boolean isPaper() { return isPaper; }
}
