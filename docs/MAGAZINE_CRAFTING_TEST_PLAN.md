# Magazine crafting manual QA

Intended in both player 2x2 and crafting table 3x3, survival and creative:

1. New magazines start EMPTY (0/capacity); tooltip/bar should reflect emptiness.
2. Place an empty 9x19 Glock magazine and a 9x19 cartridge in any two cells.
   Taking output consumes the original mag and one cartridge, yields loaded magazine with 1 round.
3. Repeat to capacity. At maximum, the output must disappear.
4. Incompatible ammunition never matches; extra unrelated ingredients never match.
5. Put ONLY a loaded mag in the grid: output shows one cartridge.
   After taking it, the mag MUST remain IN THE SAME GRID CELL with count reduced by one.
6. Repeat until empty. Output then disappears; empty mag must survive.
7. Try the above with full inventory, shift-click, stacked rounds, hotbar swap,
   double-click, and recipe book. Confirm no duplicated cartridges or magazines.
8. Closing the screen with magazines in the crafting grid must retain ammunition count.
9. Reload the world and confirm magazine damage persists correctly.

The implementation uses `Recipe#getRemainingItems` to return an updated magazine
in the original grid slot during unloading. This follows Minecraft's normal
recipe remainder handling, but requires an in-game QA pass to verify all
crafting screen and quick-move pathways. Do not label runtime-tested until verified.
