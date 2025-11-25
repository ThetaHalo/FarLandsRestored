package lol.eps.farlandsRestored;

import java.util.logging.Logger;

public class Messages {
    private final String pluginName;
    private final Logger logger = Logger.getLogger("Minecraft");
    private final FarlandsRestored instance;

    public Messages(String pluginName, FarlandsRestored instance) {
        this.pluginName = pluginName;
        this.instance = instance;
    }

    public void unknownGenerator(String worldName, String generatorName) {
        this.instance.getComponentLogger().error("[{} Error] The world '{}' does not have a recognized generator.", this.pluginName, worldName);
        this.instance.getComponentLogger().error("[{} Error] A custom generator may already be in place or Mojang changed something.", this.pluginName);
        this.instance.getComponentLogger().error("[{} Error] The generator detected is: '{}'.", this.pluginName, generatorName);
        this.instance.getComponentLogger().error("[{} Error] For safety, FarLandsAgain will not be enabled on this world.", this.pluginName);
    }

    public void unknownEnvironment(String worldName, String environment) {
        this.instance.getComponentLogger().warn("[{} Error] The world '{}' is not a recognized environment.", this.pluginName, worldName);
        this.instance.getComponentLogger().warn("[{} Error] FarLandsAgain will not be enabled on this world.", this.pluginName);
    }

    public void incompatibleVersion() {
        this.instance.getComponentLogger().error("[{} Error] This version of Minecraft is not supported. Disabling plugin.", this.pluginName);
    }

    public void enableSuccess(String worldName) {
        this.instance.getComponentLogger().info("[{} Success] The world '{}' will have Far Lands!", this.pluginName, worldName);
    }

    public void enableFailed(String worldName) {
        this.instance.getComponentLogger().error("[{} Error] Something went wrong enabling FarLandsAgain on world '{}'.", this.pluginName, worldName);
    }

    public void pluginReady() {
        this.logger.info("[" + this.pluginName + "] Everything is ready to go!");
    }

    public void pluginDisable() {
        this.instance.getComponentLogger().warn("[{}] {} now disabled.", this.pluginName, this.pluginName);
    }

    public void alreadyEnabled(String worldName) {this.instance.getComponentLogger().warn("[" + this.pluginName + " Success] FarLandsAgain appears to already be enabled for this world.");
    }

    public void restoreFailed(String worldName) {
        this.instance.getComponentLogger().error("[{} Error] Something went wrong while restoring the original world generation.", this.pluginName);
    }

    public void providerFlat(String worldName) {
        this.instance.getComponentLogger().warn("[{} Error] Flatlands generator detected for '{}'.", this.pluginName, worldName);
        this.instance.getComponentLogger().warn("[{} Error] Far Lands do not generate in flat worlds.", this.pluginName);
    }

    public void unknownNoise(String worldName, String noiseName) {
        this.instance.getComponentLogger().warn("[{} Error] The world '{}' does not have a recognized noise generator.", this.pluginName, worldName);
        this.instance.getComponentLogger().warn("[{} Error] The original may have been modified, replaced or Mojang changed something.", this.pluginName);
        this.instance.getComponentLogger().warn("[{} Error] The noise generator detected is: '{}'.", this.pluginName, noiseName);
        this.instance.getComponentLogger().warn("[{} Error] To avoid possible conflicts, FarLandsAgain will not be enabled on this world.", this.pluginName);
    }
}
