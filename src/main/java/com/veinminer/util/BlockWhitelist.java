package com.veinminer.util;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

/**
 * Strict whitelist of block+metadata pairs the vein miner is allowed to
 * touch. Everything else is completely ignored, no matter what the player
 * is holding or looking at.
 *
 * Railcraft is treated as a soft/optional dependency: we look its block
 * up by registry name at runtime (GameRegistry.findBlock) instead of
 * compiling against the Railcraft API, so this mod has no hard dependency
 * on Railcraft and simply skips that entry if Railcraft isn't installed.
 */
public final class BlockWhitelist {

    private static final int GRAY_HARDENED_CLAY_META = 7; // vanilla stained_hardened_clay, gray

    private static Block railcraftQuarriedStone;
    private static boolean lookedUpRailcraft = false;

    private BlockWhitelist() {
    }

    private static Block getRailcraftQuarriedStone() {
        if (!lookedUpRailcraft) {
            lookedUpRailcraft = true;
            // Soft dependency: returns null if Railcraft isn't loaded,
            // which is handled gracefully everywhere this is used.
            railcraftQuarriedStone = GameRegistry.findBlock("railcraft", "stone.quarried");
        }
        return railcraftQuarriedStone;
    }

    /**
     * @return true if this exact block+metadata combination is allowed to
     *         be vein-mined.
     */
    public static boolean isWhitelisted(Block block, int metadata) {
        if (block == null) {
            return false;
        }

        if (block == Blocks.stained_hardened_clay && metadata == GRAY_HARDENED_CLAY_META) {
            return true;
        }

        Block quarried = getRailcraftQuarriedStone();
        if (quarried != null && block == quarried) {
            return true;
        }

        return false;
    }
}
