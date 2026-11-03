package dev.pragmaticfirearms.core;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Pure Java source-of-truth for manufacturing balance.
 *
 * One component fabrication job yields eight pieces; one ammunition assembly
 * job consumes eight bodies, eight projectile cores, eight universal caps,
 * and a caliber-specific multiple of eight universal charge tokens.
 * These values are Minecraft gameplay abstractions.
 */
public final class ManufacturingCatalog {
    private ManufacturingCatalog() {}

    public record ComponentRecipe(String id, Map<String, Integer> inputs,
                                  int outputCount, int processingTicks) {
        public ComponentRecipe {
            inputs = Map.copyOf(inputs);
            if (outputCount <= 0 || processingTicks <= 0 || inputs.isEmpty())
                throw new IllegalArgumentException("Invalid component recipe");
            if (inputs.values().stream().anyMatch(v -> v <= 0))
                throw new IllegalArgumentException("Invalid component quantity");
        }
    }

    public record AssemblyRecipe(String caliberId, int tier, int outputCount,
                                 Map<String, Integer> inputs, int processingTicks) {
        public AssemblyRecipe {
            inputs = Map.copyOf(inputs);
        }
    }

    private static ComponentRecipe component(String id, Object... pairs) {
        Map<String, Integer> costs = new LinkedHashMap<>();
        for (int i = 0; i < pairs.length; i += 2) costs.put((String) pairs[i], (Integer) pairs[i + 1]);
        return new ComponentRecipe(id, costs, 8, 40);
    }

    public static final List<ComponentRecipe> COMPONENTS = List.of(
        component("light_cartridge_body", "minecraft:copper_ingot", 1, "minecraft:iron_nugget", 1),
        component("standard_cartridge_body", "minecraft:copper_ingot", 2, "minecraft:iron_nugget", 2),
        component("heavy_cartridge_body", "minecraft:copper_ingot", 3, "minecraft:iron_ingot", 1),
        component("shotgun_shell_body", "minecraft:copper_ingot", 1, "minecraft:paper", 4),
        component("small_projectile_core", "minecraft:iron_nugget", 1),
        component("standard_projectile_core", "minecraft:iron_ingot", 1),
        component("reinforced_projectile_core", "minecraft:iron_ingot", 2),
        component("heavy_projectile_core", "minecraft:iron_ingot", 3),
        component("shotgun_pellet_bundle", "minecraft:iron_ingot", 1),
        component("shotgun_slug_core", "minecraft:iron_ingot", 2),
        component("universal_ignition_cap", "pragmaticfirearms:copper_nugget", 1, "minecraft:redstone", 1),
        component("universal_propellant_charge", "minecraft:gunpowder", 1)
    );

    public static final List<AssemblyRecipe> ASSEMBLY = AmmoCatalog.CALIBERS.stream().map(caliber -> {
        Map<String, Integer> requirements = new LinkedHashMap<>();
        requirements.put(caliber.body(), 8);
        requirements.put(caliber.projectile(), 8);
        requirements.put("universal_ignition_cap", 8);
        requirements.put("universal_propellant_charge", caliber.propellantPerBatch());
        int processingTicks = switch (caliber.tier()) {
            case 1 -> 40;
            case 2 -> 60;
            case 3 -> 100;
            default -> throw new IllegalArgumentException("Unknown tier: " + caliber.tier());
        };
        return new AssemblyRecipe(caliber.id(), caliber.tier(), 8, requirements, processingTicks);
    }).toList();

    /** Resolve the raw inputs needed to complete exactly one assembly batch. */
    public static Map<String, Integer> rawCostOfOneBatch(AssemblyRecipe assembly) {
        Map<String, Integer> totals = new LinkedHashMap<>();
        for (var input : assembly.inputs().entrySet()) {
            ComponentRecipe recipe = COMPONENTS.stream()
                .filter(component -> component.id().equals(input.getKey()))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Unknown component " + input.getKey()));
            int batches = Math.floorDiv(input.getValue() + recipe.outputCount() - 1, recipe.outputCount());
            recipe.inputs().forEach((item, count) -> totals.merge(item, count * batches, Integer::sum));
        }
        return Map.copyOf(totals);
    }
}
