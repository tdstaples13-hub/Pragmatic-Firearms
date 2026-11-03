# Manufacturing workstations

The registered Component Fabricator and Ammunition Assembly Press are currently
placeable blocks, with item forms, crafting recipes and drops. They **do not yet
have block entities, interactive menus, processing ticks, or item automation**.

Shared Java recipe definitions are in `core/ManufacturingCatalog.java`.
The `design/` JSON files record the equivalent balancing profiles but do not
yet load into Minecraft's recipe registry.

## Planned processing loop

- Fabricator: select one of 12 component recipes, accept ingredients and
  produce 8 components after 40 ticks.
- Assembly press: select one of 22 caliber profiles and consume the specified
  four component categories. Batch output: eight caliber-specific cartridges.
- Tick duration: Tier I 40 ticks, Tier II 60 ticks, Tier III 100 ticks.
- Recipe lock prevents automation from switching the selected output.
- Top/sides accept inputs, bottom extracts output, including hopper compatibility.
- Save recipe selection, inventory, and progress to block-entity persistence.

## Implementation rules

Crafting machines must use server-authoritative atomic transactions:
never consume inputs unless the output fits, and never produce an output without
consuming the corresponding inputs. Persist pending progress and recipe selection.
Test shift-clicks and machine automation to avoid duplication.
