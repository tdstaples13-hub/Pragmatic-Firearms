package dev.pragmaticfirearms.registry;

import dev.pragmaticfirearms.PragmaticFirearms;
import dev.pragmaticfirearms.core.AmmoCatalog;
import dev.pragmaticfirearms.item.AmmoMagazineItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ModItems {
    private ModItems() {}
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PragmaticFirearms.MOD_ID);
    public static final Map<String, DeferredItem<Item>> AMMUNITION = new LinkedHashMap<>();
    public static final Map<String, DeferredItem<Item>> COMPONENTS = new LinkedHashMap<>();
    public static final Map<String, DeferredItem<AmmoMagazineItem>> MAGAZINES = new LinkedHashMap<>();

    static {
        for (AmmoCatalog.Caliber caliber : AmmoCatalog.CALIBERS)
            AMMUNITION.put(caliber.id(), ITEMS.registerSimpleItem("ammo_" + caliber.id()));
        for (String name : new String[] {
            "light_cartridge_body", "standard_cartridge_body", "heavy_cartridge_body",
            "shotgun_shell_body", "small_projectile_core", "standard_projectile_core",
            "reinforced_projectile_core", "heavy_projectile_core", "shotgun_pellet_bundle",
            "shotgun_slug_core", "universal_ignition_cap", "universal_propellant_charge",
            "copper_nugget"
        }) COMPONENTS.put(name, ITEMS.registerSimpleItem(name));

        registerMagazine("stanag_30_556", 30, "556x45mm", "stanag");
        registerMagazine("m1_garand_clip_8", 8, "30_06", "m1_en_bloc");
        registerMagazine("m1911_magazine_7", 7, "45_acp", "m1911");
        registerMagazine("glock_17_magazine", 17, "9x19mm", "glock_9mm");
        registerMagazine("ak_30_762", 30, "762x39mm", "ak");
        registerMagazine("revolver_speedloader_6_357", 6, "357_magnum", "revolver_357");
        registerMagazine("mosin_stripper_clip_5", 5, "762x54mmr", "mosin");
        registerMagazine("m249_belt_100", 100, "556x45mm", "m249");
    }
    private static void registerMagazine(String name, int capacity, String caliber, String family) {
        MAGAZINES.put(name, ITEMS.registerItem(name,
            props -> new AmmoMagazineItem(props, capacity, caliber, family)));
    }
}
