package dev.pragmaticfirearms.core;

public final class MagazineRules {
    private MagazineRules() {}
    public static int rounds(int capacity, int damage) {
        check(capacity, damage);
        return capacity - damage;
    }
    public static int loadOne(int capacity, int damage) {
        check(capacity, damage);
        if (damage == 0) throw new IllegalStateException("Magazine is full");
        return damage - 1;
    }
    public static int unloadOne(int capacity, int damage) {
        check(capacity, damage);
        if (damage == capacity) throw new IllegalStateException("Magazine is empty");
        return damage + 1;
    }
    public static boolean canLoad(int capacity, int damage) {
        check(capacity, damage);
        return damage > 0;
    }
    public static boolean canUnload(int capacity, int damage) {
        check(capacity, damage);
        return damage < capacity;
    }
    public static void check(int capacity, int damage) {
        if (capacity < 1 || damage < 0 || damage > capacity)
            throw new IllegalArgumentException("Invalid magazine capacity/damage");
    }
}
