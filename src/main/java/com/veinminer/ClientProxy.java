package com.veinminer;

import com.veinminer.KeyBindings;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        ClientRegistry.registerKeyBinding(KeyBindings.veinMineKey);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }
}
