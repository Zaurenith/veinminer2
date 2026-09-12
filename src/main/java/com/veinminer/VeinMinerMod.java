package com.veinminer;

import com.veinminer.handler.BlockBreakHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

/**
 * Lightweight client-side vein miner.
 *
 * Holds no server-side state and requires no server-side install:
 * every "extra" block break is performed through the normal
 * PlayerControllerMP break call, so it behaves exactly like the
 * player manually breaking each block (same packets, same
 * survival/creative rules, same tool requirements).
 */
@Mod(modid = VeinMinerMod.MODID, name = VeinMinerMod.NAME, version = VeinMinerMod.VERSION, acceptableRemoteVersions = "*")
public class VeinMinerMod {

    public static final String MODID = "veinminer";
    public static final String NAME = "Vein Miner";
    public static final String VERSION = "1.0.0";

    @SidedProxy(clientSide = "com.veinminer.ClientProxy", serverSide = "com.veinminer.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        MinecraftForge.EVENT_BUS.register(new BlockBreakHandler());
    }
}
