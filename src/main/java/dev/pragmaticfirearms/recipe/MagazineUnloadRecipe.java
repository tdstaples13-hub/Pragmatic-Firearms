package dev.pragmaticfirearms.recipe;

import dev.pragmaticfirearms.registry.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

/**
 * Loaded magazine -> one cartridge.
 * The magazine is a dynamic recipe remainder, returned to its same grid slot
 * after vanilla consumes the original input, like a bucket after cake crafting.
 */
public final class MagazineUnloadRecipe extends CustomRecipe {
    public MagazineUnloadRecipe(CraftingBookCategory category) { super(category); }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return MagazineCrafting.scan(input, false) != null;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        var selection = MagazineCrafting.scan(input, false);
        if (selection == null) return ItemStack.EMPTY;
        var item = ModItems.AMMUNITION.get(selection.magazine().caliberId());
        return item == null ? ItemStack.EMPTY : new ItemStack(item.get());
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remainder = NonNullList.withSize(input.size(), ItemStack.EMPTY);
        var selection = MagazineCrafting.scan(input, false);
        if (selection != null) {
            remainder.set(selection.magazineSlot(),
                selection.magazine().unloadedOne(selection.magazineStack()));
        }
        return remainder;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.MAGAZINE_UNLOAD.get();
    }
}
