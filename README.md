# Pragmatic Firearms (early development)

A Minecraft **1.21.1** / **NeoForge 21.1.265** mod project, requiring **Java 21**. Mod ID: `pragmaticfirearms`.

## Implemented starter source
- 22 ammo item registrations, 13 manufacturing items, 8 magazine/clip/belt item definitions.
- Damage-based magazine round-count helpers: `damage = capacity - loadedRounds`.
- A full/empty visible ammo bar on magazine items.
- 22 assembly and 12 component fabrication profiles under `design/`.
- Translation entries and temporary vanilla-texture item models.
- Pure-Java magazine-counting test (`MagazineRulesTest`).

## Not implemented
- Crafting-grid **loading** (magazine + one compatible round -> updated magazine).
- Crafting-grid **unloading** (magazine alone -> one round, same magazine stays in same grid slot with one fewer round).
  These need custom server-authoritative recipe/slot transaction integration, including shift-click and automation edge cases.
- Ammunition manufacturing blocks, upgrades, input/output inventory, and machine recipe codecs.
- Firearm items, firing, chambers, feeding and reload mechanics.
- Actual textures and polished interfaces.

The `design/` files are planning data, **not operational Minecraft recipes**.
The project source has not yet been confirmed by a full NeoForge Gradle build.

## Build
Import with an installed Gradle and Java 21, or generate a Gradle wrapper with a suitable Gradle 8.x distribution.
The starter does not currently include `gradlew`.

## Gameplay specification
Magazine count is stored in item damage, magazines never break when empty, and ammunition compatibility is caliber-specific. Loading/unloading will be available from the player 2x2 grid or 3x3 table without a separate GUI.
