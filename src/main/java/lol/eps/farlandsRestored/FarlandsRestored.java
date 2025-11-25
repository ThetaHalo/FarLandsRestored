package lol.eps.farlandsRestored;

import lol.eps.farlandsRestored.Manager.FLRestoredOptions;
import lol.eps.farlandsRestored.Manager.ManageFarlands;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.event.Listener;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;


public final class FarlandsRestored extends JavaPlugin implements Listener {

    private HashMap<String, ManageFarlands> manageWorlds = new HashMap<>();
    public final Messages messages = new Messages(this.getName(), this);
    public String version;
    public String name;

    private static FarlandsRestored instance;

    public static FarlandsRestored getInstance() {
        return instance;
    }


    @Override
    public void onEnable() {
        instance = this;

        ConfigurationSerialization.registerClass(FLRestoredOptions.class);
        saveDefaultConfig();

        this.name = getServer().getClass().getPackage().getName();
        this.version = this.name.substring(this.name.lastIndexOf('.') + 1);
        this.getServer().getPluginManager().registerEvents(this, this);
        this.manageWorlds = new HashMap<>();

        this.getComponentLogger().info("FLRestored - Preparing Worlds");
        for (World world : Bukkit.getWorlds()) {
            this.getComponentLogger().info("FLRestored - Preparing World: {} ", world.getName());
            prepareWorld(world);
        }

    }

    public boolean isPaper() {
        try {
            Class.forName("com.destroystokyo.paper.PaperConfig");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public void onDisable() {
        if (manageWorlds != null) {
            for (String worldName : this.manageWorlds.keySet()) {
                this.manageWorlds.get(worldName).restoreGenerator();
            }
        }
        this.messages.pluginDisable();
    }

    public void prepareWorld(World world) {
        String worldName = world.getName();
        String path = "worlds." + worldName;

        if (!getConfig().getBoolean(path + ".enabled", false)) return;
        if (manageWorlds.containsKey(worldName)) return;

        Object section = getConfig().get(path + ".options");

        if (!(section instanceof FLRestoredOptions options)) {
            getLogger().warning("World '" + worldName + "' has no valid options section!");
            return;
        }

        manageWorlds.put(worldName, new ManageFarlands(world, this, options));
    }
}
