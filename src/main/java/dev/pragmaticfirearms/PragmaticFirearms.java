package dev.pragmaticfirearms;

import dev.pragmaticfirearms.registry.ModItems;
import dev.pragmaticfirearms.registry.ModBlocks;
import dev.pragmaticfirearms.recipe.ModRecipeSerializers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(PragmaticFirearms.MOD_ID)
public final class PragmaticFirearms {
    public static final String MOD_ID = "pragmaticfirearms";
    public PragmaticFirearms(IEventBus modBus) {
        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModRecipeSerializers.SERIALIZERS.register(modBus);
    }
}
