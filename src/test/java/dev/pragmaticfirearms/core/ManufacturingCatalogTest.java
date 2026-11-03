package dev.pragmaticfirearms.core;

import java.util.HashSet;
import java.util.Set;

public final class ManufacturingCatalogTest {
    public static void main(String[] args) {
        assert AmmoCatalog.CALIBERS.size() == 22;
        assert ManufacturingCatalog.COMPONENTS.size() == 12;
        assert ManufacturingCatalog.ASSEMBLY.size() == 22;

        Set<String> unique = new HashSet<>();
        for (var recipe : ManufacturingCatalog.ASSEMBLY) {
            assert unique.add(recipe.caliberId()) : "Duplicate caliber";
            assert recipe.outputCount() == 8;
            assert recipe.inputs().get("universal_ignition_cap") == 8;
            assert recipe.inputs().get("universal_propellant_charge") % 8 == 0;
            assert !ManufacturingCatalog.rawCostOfOneBatch(recipe).isEmpty();
        }
        var nine = ManufacturingCatalog.ASSEMBLY.stream()
            .filter(x -> x.caliberId().equals("9x19mm")).findFirst().orElseThrow();
        var raw = ManufacturingCatalog.rawCostOfOneBatch(nine);
        assert raw.get("minecraft:gunpowder") == 1 : raw;
        assert raw.get("minecraft:copper_ingot") == 1 : raw;
        assert raw.get("minecraft:iron_ingot") == 1 : raw;
        assert raw.get("minecraft:iron_nugget") == 1 : raw;
        assert raw.get("pragmaticfirearms:copper_nugget") == 1 : raw;
        assert raw.get("minecraft:redstone") == 1 : raw;
        System.out.println("ManufacturingCatalogTest PASS");
    }
}
