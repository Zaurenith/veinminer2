package com.veinminer;

import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyBindings {

    // Held (not toggled) while breaking a block to trigger the chain-mine.
    // Fully rebindable via Options -> Controls, since it's a standard KeyBinding.
    public static final KeyBinding veinMineKey = new KeyBinding(
            "key.veinminer.activate",
            Keyboard.KEY_G,
            "key.categories.veinminer"
    );
}
