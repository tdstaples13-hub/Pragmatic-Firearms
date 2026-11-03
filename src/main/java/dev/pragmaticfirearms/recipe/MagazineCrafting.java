package dev.pragmaticfirearms.recipe;

import dev.pragmaticfirearms.item.AmmoMagazineItem;
import dev.pragmaticfirearms.registry.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;

/** Scan a crafting grid without changing its contents. */
final class MagazineCrafting {
    private MagazineCrafting() {}

    record Selection(int magazineSlot, ItemStack magazineStack,
                     AmmoMagazineItem magazine, int ammunitionSlot, ItemStack ammunition) {}

    static Selection scan(CraftingInput input, boolean loading) {
        int magSlot = -1;
        int ammoSlot = -1;
        ItemStack magStack = ItemStack.EMPTY;
        ItemStack ammoStack = ItemStack.EMPTY;
        AmmoMagazineItem magazine = null;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;
            if (stack.getItem() instanceof AmmoMagazineItem candidate) {
                if (magSlot >= 0 || stack.getCount() != 1) return null;
                magSlot = i;
                magStack = stack;
                magazine = candidate;
            } else if (loading) {
                if (ammoSlot >= 0) return null;
                ammoSlot = i;
                ammoStack = stack;
            } else {
                return null;
            }
        }
        if (magazine == null || (loading && ammoSlot < 0)) return null;
        if (loading) {
            var registeredAmmo = ModItems.AMMUNITION.get(magazine.caliberId());
            if (registeredAmmo == null || !ammoStack.is(registeredAmmo.get())
                    || magazine.rounds(magStack) >= magazine.capacity()) return null;
        } else if (magazine.rounds(magStack) == 0) return null;
        return new Selection(magSlot, magStack, magazine, ammoSlot, ammoStack);
    }
}
