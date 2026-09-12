package com.veinminer;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class CommonProxy {

    public static int maxBlocksPerActivation = 256;

    public void preInit(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        maxBlocksPerActivation = config.getInt(
                "maxBlocksPerActivation",
                Configuration.CATEGORY_GENERAL,
                256,
                8,
                4096,
                "Maximum number of extra blocks that can be broken in a single vein-mine activation."
        );
        config.save();
    }

    public void init(FMLInitializationEvent event) {
        // No client-only setup needed on the server/common side.
        // This mod does nothing server-side; it only ever acts on
        // the local player's own block-breaking calls.
    }
}
