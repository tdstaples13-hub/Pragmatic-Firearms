package dev.pragmaticfirearms.core;

import java.util.List;

public final class AmmoCatalog {
    private AmmoCatalog() {}
    public record Caliber(String id, String label, int tier, String body, String projectile, int propellantPerBatch) {}
    public static final List<Caliber> CALIBERS = List.of(
        new Caliber("22_lr", ".22 LR", 1, "light_cartridge_body", "small_projectile_core", 8),
        new Caliber("380_acp", ".380 ACP", 1, "light_cartridge_body", "standard_projectile_core", 8),
        new Caliber("9x19mm", "9×19mm", 1, "light_cartridge_body", "standard_projectile_core", 8),
        new Caliber("40_sw", ".40 S&W", 1, "light_cartridge_body", "standard_projectile_core", 8),
        new Caliber("45_acp", ".45 ACP", 1, "light_cartridge_body", "standard_projectile_core", 8),
        new Caliber("38_special", ".38 Special", 1, "light_cartridge_body", "standard_projectile_core", 8),
        new Caliber("357_magnum", ".357 Magnum", 2, "standard_cartridge_body", "reinforced_projectile_core", 16),
        new Caliber("44_magnum", ".44 Magnum", 2, "standard_cartridge_body", "reinforced_projectile_core", 16),
        new Caliber("50_ae", ".50 AE", 2, "standard_cartridge_body", "reinforced_projectile_core", 16),
        new Caliber("545x39mm", "5.45×39mm", 2, "standard_cartridge_body", "reinforced_projectile_core", 16),
        new Caliber("556x45mm", "5.56×45mm", 2, "standard_cartridge_body", "reinforced_projectile_core", 16),
        new Caliber("762x39mm", "7.62×39mm", 2, "standard_cartridge_body", "reinforced_projectile_core", 16),
        new Caliber("300_blackout", ".300 Blackout", 2, "standard_cartridge_body", "reinforced_projectile_core", 16),
        new Caliber("762x51mm", "7.62×51mm", 2, "standard_cartridge_body", "reinforced_projectile_core", 24),
        new Caliber("762x54mmr", "7.62×54mmR", 2, "standard_cartridge_body", "reinforced_projectile_core", 24),
        new Caliber("30_06", ".30-06 Springfield", 2, "standard_cartridge_body", "reinforced_projectile_core", 24),
        new Caliber("300_win_mag", ".300 Win Mag", 3, "heavy_cartridge_body", "heavy_projectile_core", 32),
        new Caliber("338_lapua", ".338 Lapua Magnum", 3, "heavy_cartridge_body", "heavy_projectile_core", 32),
        new Caliber("50_bmg", ".50 BMG", 3, "heavy_cartridge_body", "heavy_projectile_core", 32),
        new Caliber("12_gauge", "12 Gauge", 1, "shotgun_shell_body", "shotgun_pellet_bundle", 16),
        new Caliber("20_gauge", "20 Gauge", 1, "shotgun_shell_body", "shotgun_pellet_bundle", 16),
        new Caliber("410_bore", ".410 Bore", 1, "shotgun_shell_body", "shotgun_pellet_bundle", 8)
    );
}
