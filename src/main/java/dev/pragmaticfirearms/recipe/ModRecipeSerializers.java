package dev.pragmaticfirearms.recipe;

import dev.pragmaticfirearms.PragmaticFirearms;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/** Parameter-free special crafting recipe serializers. */
public final class ModRecipeSerializers {
    private ModRecipeSerializers() {}

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
        DeferredRegister.create(Registries.RECIPE_SERIALIZER, PragmaticFirearms.MOD_ID);

    public static final Supplier<RecipeSerializer<MagazineLoadRecipe>> MAGAZINE_LOAD =
        SERIALIZERS.register("magazine_load",
            () -> new SimpleCraftingRecipeSerializer<>(MagazineLoadRecipe::new));

    public static final Supplier<RecipeSerializer<MagazineUnloadRecipe>> MAGAZINE_UNLOAD =
        SERIALIZERS.register("magazine_unload",
            () -> new SimpleCraftingRecipeSerializer<>(MagazineUnloadRecipe::new));
}
