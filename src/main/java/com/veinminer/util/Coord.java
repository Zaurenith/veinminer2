package com.veinminer.util;

/**
 * Minimal immutable block coordinate. Minecraft 1.7.10 predates BlockPos,
 * so we roll our own tiny value type for the BFS queue / visited set.
 */
public final class Coord {

    public final int x;
    public final int y;
    public final int z;

    public Coord(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coord offset(int dx, int dy, int dz) {
        return new Coord(x + dx, y + dy, z + dz);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coord)) return false;
        Coord other = (Coord) o;
        return x == other.x && y == other.y && z == other.z;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }
}
