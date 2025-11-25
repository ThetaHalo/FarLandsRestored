package lol.eps.farlandsRestored.Manager;

import lol.eps.farlandsRestored.FarlandsRestored;
import lol.eps.farlandsRestored.Manager.v1_21_10.LoadFarlands;
import org.bukkit.World;

public class ManageFarlands {

    private final FarlandsRestored plugin;
    private final World world;
    private final FLRestoredOptions options;

    private lol.eps.farlandsRestored.Manager.v1_21_10.LoadFarlands v1_21_10;

    public ManageFarlands(World world, FarlandsRestored instance, FLRestoredOptions options) {
        this.plugin = instance;
        this.world = world;
        this.options = options;

        switch (this.plugin.version) {
            default: {
                this.v1_21_10 = new LoadFarlands(this.world, options, this.plugin.isPaper(), this.plugin.getName());
            }
        }
    }

    public void restoreGenerator() {
        switch (this.plugin.version) {
            default -> this.v1_21_10.restoreGenerator();
        }
    }
}
