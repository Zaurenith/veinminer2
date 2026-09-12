# Vein Miner (Forge 1.7.10)

Lightweight, client-side chain-mining for two specific block types:

- Vanilla **Gray Terracotta** (`minecraft:stained_hardened_clay`, metadata `7`)
- Railcraft **Quarried Stone** (`railcraft:stone.quarried`) — detected at
  runtime by registry name, so Railcraft is an *optional* dependency. If
  Railcraft isn't installed, that entry is simply skipped and only gray
  terracotta works.

Everything else is ignored, including other terracotta colors.

## How it works

Hold the vein-mine key (default **G**) while breaking a whitelisted block.
The mod does a breadth-first search across the 6 adjacent faces
(up/down/north/south/east/west), and for every connected block that is
the *exact same block + metadata* as the one you broke, it triggers a
normal break through `PlayerControllerMP` — the same code path as you
manually clicking each block. That means:

- Normal survival rules apply (tool/tier checks, block hardness, etc.)
- Normal packets are sent to the server, so it's compatible with vanilla
  Forge servers with **no server-side install required**
- A configurable safety cap (default **256** blocks per activation) stops
  runaway chains from causing lag

The key is a standard Minecraft `KeyBinding`, so it's rebindable from
**Options → Controls → Vein Miner**.

## Project layout

```
src/main/java/com/veinminer/
  VeinMinerMod.java          - @Mod entry point
  CommonProxy.java           - config loading (maxBlocksPerActivation)
  ClientProxy.java           - registers the keybinding
  KeyBindings.java           - the "G" keybinding definition
  handler/BlockBreakHandler.java  - BFS chain-mine logic
  util/BlockWhitelist.java   - the strict block+metadata whitelist
  util/Coord.java            - tiny int-coordinate value type (pre-BlockPos)
src/main/resources/
  mcmod.info
  assets/veinminer/lang/en_US.lang
build.gradle                 - ForgeGradle 1.2 build script for MC 1.7.10
```

## Building

This uses [ForgeGradle](https://github.com/MinecraftForge/ForgeGradle) for
Forge 1.7.10 (MCP mappings baked into `build.gradle`). From the project
root, with a JDK 7/8 available:

```
./gradlew setupDecompWorkspace   # first time only, downloads MCP/Forge
./gradlew build
```

The compiled jar will be in `build/libs/`. Drop it into your `mods/`
folder alongside Forge 1.7.10 (10.13.4.1614+).

## Config

On first launch, `config/veinminer.cfg` is generated with:

```
I:maxBlocksPerActivation=256
```

Range 8–4096. Lower it if chains ever cause a noticeable stutter on your
hardware.

## Notes / possible follow-ups

- Currently only whitelists gray terracotta and Railcraft quarried stone,
  per spec — extending the whitelist is a one-line addition in
  `BlockWhitelist.isWhitelisted`.
- Chains only spread within the *same* block+metadata as the block that
  started the chain (gray terracotta won't bleed into quarried stone or
  vice versa).
- No reach-distance check is enforced beyond what `onPlayerDestroyBlock`
  itself does; if you want a hard reach cap, that'd be a small addition
  to the BFS loop (compare `next` to the player's eye position).
