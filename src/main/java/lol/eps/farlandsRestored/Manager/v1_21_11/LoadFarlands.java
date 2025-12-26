package lol.eps.farlandsRestored.Manager.v1_21_11;

import lol.eps.farlandsRestored.FarlandsRestored;
import lol.eps.farlandsRestored.Manager.FLRestoredOptions;
import lol.eps.farlandsRestored.Messages;

import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;

import net.minecraft.world.level.chunk.ChunkGenerator;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;


public class LoadFarlands {
    private final World world;
    private final ServerLevel nmsWorld;
    private final String worldName;
    private String originalGenName;
    private final Messages messages;
    private ChunkGenerator originalGenerator;
    private ServerChunkCache serverChunkCache;
    private boolean isPaper;
    private FLRestoredOptions config;

    public LoadFarlands(World world, FLRestoredOptions config, boolean isPaper, String pluginName) {
        this.world = world;
        this.config = config;
        this.worldName = world.getName();
        this.isPaper = isPaper;
        this.nmsWorld = ((CraftWorld) world).getHandle();
        this.messages = new Messages(pluginName, FarlandsRestored.getInstance());

        this.serverChunkCache = this.nmsWorld.getChunkSource();
        this.originalGenerator = this.serverChunkCache.getGenerator();
        this.originalGenName = this.originalGenerator.getClass().getSimpleName();

        //modifyGenerator();
    }

    public void restoreGenerator() {
        this.messages.restoreFailed(this.worldName);
    }
/*
    public void modifyGenerator() {
        World.Environment environment = this.world.getEnvironment();
        boolean enabled = false;

        // NOTE: Flat map type does not use noise generators for terrain so Far Lands can't be done there
        // The equivalent would just be a solid block anyway
        if (this.nmsWorld.isFlat()) {
            this.messages.providerFlat(this.worldName);
            return;
        } else if (!isRecognizedGenerator(environment, this.originalGenName)) {
            this.messages.unknownGenerator(this.worldName, this.originalGenName);
            return;
        } else {
            //int divisor = (environment == Environment.THE_END) ? 8 : 4;
            int divisor = (environment == World.Environment.THE_END) ? 8 : 1;
            NoiseBasedChunkGenerator noiseBasedChunkGenerator = (NoiseBasedChunkGenerator) this.originalGenerator;
            WorldgenRandom.Algorithm al = noiseBasedChunkGenerator.settings.get().getRandomSource();
            // This is as good as we got for now. Still not sure it matters given how it's used.
            RandomSource randomSource = al.newInstance(this.nmsWorld.getSeed());
            NoiseSamplingSettings noiseSamplingSettings = noiseBasedChunkGenerator.settings.get().noiseSettings()
                    .noiseSamplingSettings();
            NoiseSampler noiseSampler = (NoiseSampler) noiseBasedChunkGenerator.climateSampler();

            try {
                Field blendedNoiseField = ReflectionHelper.getField(noiseSampler.getClass(), "q", true);
                blendedNoiseField.setAccessible(true);
                BlendedNoise blendedNoise = (BlendedNoise) blendedNoiseField.get(noiseSampler);
                String blendedNoiseName = blendedNoise.getClass().getSimpleName();

                if (blendedNoiseName.equals("BlendedNoise")) {
                    Field minLimitNoiseField = ReflectionHelper.getField(blendedNoise.getClass(), "a", true);
                    minLimitNoiseField.setAccessible(true);
                    PerlinNoise minLimitNoise = (PerlinNoise) minLimitNoiseField.get(blendedNoise);

                    Field maxLimitNoiseField = ReflectionHelper.getField(blendedNoise.getClass(), "b", true);
                    maxLimitNoiseField.setAccessible(true);
                    PerlinNoise maxLimitNoise = (PerlinNoise) maxLimitNoiseField.get(blendedNoise);

                    Field mainNoiseField = ReflectionHelper.getField(blendedNoise.getClass(), "c", true);
                    mainNoiseField.setAccessible(true);
                    PerlinNoise mainNoise = (PerlinNoise) mainNoiseField.get(blendedNoise);

                    Field cellWidthField = ReflectionHelper.getField(blendedNoise.getClass(), "h", true);
                    cellWidthField.setAccessible(true);
                    int cellWidth = cellWidthField.getInt(blendedNoise);

                    Field cellHeightField = ReflectionHelper.getField(blendedNoise.getClass(), "i", true);
                    cellHeightField.setAccessible(true);
                    int cellHeight = cellHeightField.getInt(blendedNoise);

                    FL_BlendedNoise newBlendedNoise = new FL_BlendedNoise(minLimitNoise, maxLimitNoise, mainNoise,
                            noiseSamplingSettings, cellWidth, cellHeight, randomSource, this.configValues, divisor);
                    ReflectionHelper.fieldSetter(blendedNoiseField, noiseSampler, newBlendedNoise);
                    enabled = true;
                } else {
                    this.messages.unknownNoise(worldName, blendedNoiseName);
                    return;
                }
            } catch (Throwable t) {
                t.printStackTrace();
                enabled = false;
            }
        }

        if (enabled) {
            this.messages.enableSuccess(worldName);
        } else {
            this.messages.enableFailed(worldName);
        }
    }*/

    private boolean isRecognizedGenerator(World.Environment environment, String originalGenName) {
        if (environment == World.Environment.NORMAL) {
            return originalGenName.equals("ChunkGeneratorAbstract");
        } else if (environment == World.Environment.NETHER) {
            return originalGenName.equals("ChunkGeneratorAbstract");
        } else if (environment == World.Environment.THE_END) {
            return originalGenName.equals("ChunkGeneratorAbstract");
        }

        return false;
    }
}
