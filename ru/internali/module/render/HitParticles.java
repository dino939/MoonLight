//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import java.util.*;
import ru.internali.module.*;
import ru.internali.*;
import ru.internali.settings.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class HitParticles extends Module
{
    private final Random random;
    public static List Modes;
    
    static {
        HitParticles.Modes = new ArrayList();
    }
    
    public HitParticles() {
        super("HitParticles", "HitParticles", Category.RENDER);
        this.random = new Random();
        HitParticles.Modes.add("Redstone");
        HitParticles.Modes.add("Heart");
        HitParticles.Modes.add("Flame");
        HitParticles.Modes.add("Cloud");
        HitParticles.Modes.add("HappyVillager");
        HitParticles.Modes.add("AngryVillager");
        HitParticles.Modes.add("Spell");
        HitParticles.Modes.add("Portal");
        HitParticles.Modes.add("Enchant");
        HitParticles.Modes.add("Criticals");
        CatClient.instance.settingsManager.rSetting(new Setting("Mode", this, "Redstone", (ArrayList)HitParticles.Modes));
        CatClient.instance.settingsManager.rSetting(new Setting("particleMultiplier", this, 5.0, 1.0, 15.0, false));
    }
    
    @SubscribeEvent
    public void onAttack(final AttackEntityEvent attackEntityEvent) {
        final String valString = CatClient.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "particleMultiplier").getValDouble();
        if (valString.equalsIgnoreCase("Redstone")) {
            for (float n2 = 0.0f; n2 < attackEntityEvent.getTarget().height; n2 += 0.2f) {
                for (int n3 = 0; n3 < n; ++n3) {
                    HitParticles.mc.effectRenderer.spawnEffectParticle(37, attackEntityEvent.getTarget().posX, attackEntityEvent.getTarget().posY + n2, attackEntityEvent.getTarget().posZ, (double)((this.random.nextInt(6) - 3) / 5.0f), 0.1, (double)((this.random.nextInt(6) - 3) / 5.0f), new int[] { Block.getIdFromBlock(Blocks.REDSTONE_BLOCK) });
                }
            }
        }
        else if (valString.equalsIgnoreCase("Heart")) {
            for (float n4 = 0.0f; n4 < attackEntityEvent.getTarget().height; n4 += 0.2f) {
                for (int n5 = 0; n5 < n; ++n5) {
                    HitParticles.mc.world.spawnParticle(EnumParticleTypes.HEART, attackEntityEvent.getTarget().posX, attackEntityEvent.getTarget().posY + n4, attackEntityEvent.getTarget().posZ, (double)((this.random.nextInt(6) - 3) / 5.0f), 0.1, (double)((this.random.nextInt(6) - 3) / 5.0f), new int[0]);
                }
            }
        }
        else if (valString.equalsIgnoreCase("Flame")) {
            for (float n6 = 0.0f; n6 < attackEntityEvent.getTarget().height; n6 += 0.2f) {
                for (int n7 = 0; n7 < n; ++n7) {
                    HitParticles.mc.world.spawnParticle(EnumParticleTypes.FLAME, attackEntityEvent.getTarget().posX, attackEntityEvent.getTarget().posY + n6, attackEntityEvent.getTarget().posZ, (double)((this.random.nextInt(6) - 3) / 5.0f), 0.1, (double)((this.random.nextInt(6) - 3) / 5.0f), new int[0]);
                }
            }
        }
        else if (valString.equalsIgnoreCase("Cloud")) {
            for (float n8 = 0.0f; n8 < attackEntityEvent.getTarget().height; n8 += 0.2f) {
                for (int n9 = 0; n9 < n; ++n9) {
                    HitParticles.mc.world.spawnParticle(EnumParticleTypes.CLOUD, attackEntityEvent.getTarget().posX, attackEntityEvent.getTarget().posY + n8, attackEntityEvent.getTarget().posZ, (double)((this.random.nextInt(6) - 3) / 5.0f), 0.1, (double)((this.random.nextInt(6) - 3) / 5.0f), new int[0]);
                }
            }
        }
        else if (valString.equalsIgnoreCase("HappyVillager")) {
            for (float n10 = 0.0f; n10 < attackEntityEvent.getTarget().height; n10 += 0.2f) {
                for (int n11 = 0; n11 < n; ++n11) {
                    HitParticles.mc.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, attackEntityEvent.getTarget().posX, attackEntityEvent.getTarget().posY + n10, attackEntityEvent.getTarget().posZ, (double)((this.random.nextInt(6) - 3) / 5.0f), 0.1, (double)((this.random.nextInt(6) - 3) / 5.0f), new int[0]);
                }
            }
        }
        else if (valString.equalsIgnoreCase("AngryVillager")) {
            for (float n12 = 0.0f; n12 < attackEntityEvent.getTarget().height; n12 += 0.2f) {
                for (int n13 = 0; n13 < n; ++n13) {
                    HitParticles.mc.world.spawnParticle(EnumParticleTypes.VILLAGER_ANGRY, attackEntityEvent.getTarget().posX, attackEntityEvent.getTarget().posY + n12, attackEntityEvent.getTarget().posZ, (double)((this.random.nextInt(6) - 3) / 5.0f), 0.1, (double)((this.random.nextInt(6) - 3) / 5.0f), new int[0]);
                }
            }
        }
        else if (valString.equalsIgnoreCase("Spell")) {
            for (float n14 = 0.0f; n14 < attackEntityEvent.getTarget().height; n14 += 0.2f) {
                for (int n15 = 0; n15 < n; ++n15) {
                    HitParticles.mc.world.spawnParticle(EnumParticleTypes.SPELL_WITCH, attackEntityEvent.getTarget().posX, attackEntityEvent.getTarget().posY + n14, attackEntityEvent.getTarget().posZ, (double)((this.random.nextInt(6) - 3) / 5.0f), 0.1, (double)((this.random.nextInt(6) - 3) / 5.0f), new int[0]);
                }
            }
        }
        else if (valString.equalsIgnoreCase("Portal")) {
            for (float n16 = 0.0f; n16 < attackEntityEvent.getTarget().height; n16 += 0.2f) {
                for (int n17 = 0; n17 < n; ++n17) {
                    HitParticles.mc.world.spawnParticle(EnumParticleTypes.PORTAL, attackEntityEvent.getTarget().posX, attackEntityEvent.getTarget().posY + n16, attackEntityEvent.getTarget().posZ, (double)((this.random.nextInt(6) - 3) / 5.0f), 0.1, (double)((this.random.nextInt(6) - 3) / 5.0f), new int[0]);
                }
            }
        }
        else if (valString.equalsIgnoreCase("Enchant")) {
            for (int n18 = 0; n18 < n; ++n18) {
                HitParticles.mc.player.onEnchantmentCritical(attackEntityEvent.getTarget());
            }
        }
        else if (valString.equalsIgnoreCase("Criticals")) {
            for (int n19 = 0; n19 < n; ++n19) {
                HitParticles.mc.player.onCriticalHit(attackEntityEvent.getTarget());
            }
        }
    }
}
