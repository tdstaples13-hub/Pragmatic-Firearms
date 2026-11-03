package dev.pragmaticfirearms.item;

import dev.pragmaticfirearms.core.MagazineRules;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/** Damage encodes unused capacity, not physical wear. Never destroy at zero rounds. */
public class AmmoMagazineItem extends Item {
    private final int capacity;
    private final String caliberId;
    private final String magazineFamily;

    public AmmoMagazineItem(Properties properties, int capacity, String caliberId, String magazineFamily) {
        super(properties.durability(capacity).component(DataComponents.DAMAGE, capacity).setNoRepair());
        this.capacity = capacity;
        this.caliberId = caliberId;
        this.magazineFamily = magazineFamily;
    }
    public int capacity() { return capacity; }
    public String caliberId() { return caliberId; }
    public String magazineFamily() { return magazineFamily; }
    public int rounds(ItemStack stack) {
        return MagazineRules.rounds(capacity, stack.getOrDefault(DataComponents.DAMAGE, capacity));
    }
    public ItemStack emptyStack() {
        return withRounds(0);
    }
    public ItemStack withRounds(int rounds) {
        if (rounds < 0 || rounds > capacity) throw new IllegalArgumentException("Invalid count");
        ItemStack stack = new ItemStack(this);
        stack.set(DataComponents.DAMAGE, capacity - rounds);
        return stack;
    }
    public ItemStack loadedOne(ItemStack input) {
        ItemStack output = input.copy();
        output.set(DataComponents.DAMAGE, MagazineRules.loadOne(capacity,
            input.getOrDefault(DataComponents.DAMAGE, capacity)));
        return output;
    }
    public ItemStack unloadedOne(ItemStack input) {
        ItemStack output = input.copy();
        output.set(DataComponents.DAMAGE, MagazineRules.unloadOne(capacity,
            input.getOrDefault(DataComponents.DAMAGE, capacity)));
        return output;
    }
    @Override
    public boolean isBarVisible(ItemStack stack) { return true; }
    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(13.0f * rounds(stack) / capacity);
    }
    @Override
    public int getBarColor(ItemStack stack) { return 0x62BE50; }
}
