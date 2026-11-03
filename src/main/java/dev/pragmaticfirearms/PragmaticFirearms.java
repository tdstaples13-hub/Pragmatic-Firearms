package dev.pragmaticfirearms;

import dev.pragmaticfirearms.registry.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(PragmaticFirearms.MOD_ID)
public final class PragmaticFirearms {
    public static final String MOD_ID = "pragmaticfirearms";
    public PragmaticFirearms(IEventBus modBus) {
        ModItems.ITEMS.register(modBus);
    }
}
