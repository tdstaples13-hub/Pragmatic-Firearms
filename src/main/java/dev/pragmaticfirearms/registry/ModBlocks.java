package dev.pragmaticfirearms.registry;

import dev.pragmaticfirearms.PragmaticFirearms;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Initial manufacturing workstation BLOCKS. Machine inventories, recipes and
 * processing will be added as block entities (not yet operational).
 */
public final class ModBlocks {
    private ModBlocks() {}

    public static final DeferredRegister.Blocks BLOCKS =
        DeferredRegister.createBlocks(PragmaticFirearms.MOD_ID);

    public static final DeferredBlock<Block> COMPONENT_FABRICATOR =
        BLOCKS.registerSimpleBlock("component_fabricator",
            BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                .strength(3.5f).requiresCorrectToolForDrops());

    public static final DeferredBlock<Block> AMMUNITION_ASSEMBLY_PRESS =
        BLOCKS.registerSimpleBlock("ammunition_assembly_press",
            BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                .strength(4.0f).requiresCorrectToolForDrops());
}
