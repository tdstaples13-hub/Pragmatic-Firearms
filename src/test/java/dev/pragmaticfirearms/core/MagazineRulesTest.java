package dev.pragmaticfirearms.core;

/** Execute with: java -ea dev.pragmaticfirearms.core.MagazineRulesTest */
public class MagazineRulesTest {
    public static void main(String[] args) {
        int capacity = 30;
        int damage = capacity;
        assert MagazineRules.rounds(capacity, damage) == 0;
        for (int i = 0; i < capacity; i++) damage = MagazineRules.loadOne(capacity, damage);
        assert damage == 0;
        assert !MagazineRules.canLoad(capacity, damage);
        try { MagazineRules.loadOne(capacity, damage); throw new AssertionError("Overfill"); }
        catch (IllegalStateException expected) {}
        for (int i = 0; i < capacity; i++) damage = MagazineRules.unloadOne(capacity, damage);
        assert damage == capacity;
        assert !MagazineRules.canUnload(capacity, damage);
        try { MagazineRules.unloadOne(capacity, damage); throw new AssertionError("Underflow"); }
        catch (IllegalStateException expected) {}
        System.out.println("MagazineRulesTest PASS");
    }
}
