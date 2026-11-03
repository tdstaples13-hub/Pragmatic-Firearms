package dev.pragmaticfirearms.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

/**
 * Magazine + compatible cartridge -> same magazine with one additional round.
 * Crafting consumes the original magazine and exactly one cartridge.
 */
public final class MagazineLoadRecipe extends CustomRecipe {
    public MagazineLoadRecipe(CraftingBookCategory category) { super(category); }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return MagazineCrafting.scan(input, true) != null;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        var selection = MagazineCrafting.scan(input, true);
        return selection == null ? ItemStack.EMPTY
            : selection.magazine().loadedOne(selection.magazineStack());
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.MAGAZINE_LOAD.get();
    }
}
