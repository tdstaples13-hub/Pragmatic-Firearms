# Pragmatic Firearms (early development)

A Minecraft **1.21.1** / **NeoForge 21.1.265** mod project, requiring **Java 21**. Mod ID: `pragmaticfirearms`.

## Implemented starter source
- 22 ammo item registrations, 13 manufacturing items, 8 magazine/clip/belt item definitions.
- Damage-based magazine round-count helpers: `damage = capacity - loadedRounds`.
- A full/empty visible ammo bar on magazine items.
- 22 assembly and 12 component fabrication profiles under `design/` and the pure-Java `ManufacturingCatalog` (these are not operational machine recipes).
- Translation entries and temporary vanilla-texture item models.
- Pure-Java magazine-counting and manufacturing catalog tests.
- Craftable, placeable Component Fabricator and Ammunition Assembly Press **blocks**, with placeholder models and drops.
- GitHub Actions workflow for logic checks and NeoForge build.

## Not implemented
- **In-game verification of magazine crafting**, particularly the shift-click, full inventory, and automation edge cases. Custom load/unload recipe implementations and serializers are now in source.
- Machine processing is not yet operational: block entities, inventory, processing ticks, GUIs, recipe codecs and hopper handling remain.
- Upgrades and power-free production rules need runtime implementation.
- Firearm items, firing, chambers, feeding and reload mechanics.
- Actual textures and polished interfaces.

The `design/` files are planning data, **not operational Minecraft recipes**.
The project compiles and packages successfully in GitHub Actions using Java 21 and Gradle 8.10.2. Runtime crafting and workstation behavior still require in-game verification.

## Build
Import with an installed Gradle and Java 21 (CI uses Gradle 8.10.2), or generate a Gradle wrapper with a compatible Gradle 8.x distribution.
The starter does not currently include `gradlew`.

## Gameplay specification
Magazine count is stored in item damage, magazines never break when empty, and ammunition compatibility is caliber-specific. Two special crafting recipes are now defined for the player 2x2 grid and the 3x3 table. For unloading, the updated magazine is returned to its original crafting input slot via dynamic recipe remainder. Runtime validation is still required; see `docs/MAGAZINE_CRAFTING_TEST_PLAN.md`.
