//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module;

import java.util.*;
import com.google.common.reflect.*;
import com.google.common.collect.*;
import ru.internali.module.World.*;
import ru.internali.module.CHAT.*;
import ru.internali.module.MANAGER.*;
import ru.internali.module.render.*;
import ru.internali.module.HUD.*;
import ru.internali.module.CONFIG.*;
import ru.internali.module.misc.*;
import ru.internali.module.player.*;
import ru.internali.module.combat.*;
import ru.internali.module.movement.*;

public class ModuleManager
{
    public static ArrayList modules;
    
    public Module getModule(final String anotherString) {
        for (final Module module : ModuleManager.modules) {
            if (module.getName().equalsIgnoreCase(anotherString)) {
                return module;
            }
        }
        return null;
    }
    
    public static ArrayList getModules() {
        return ModuleManager.modules;
    }
    
    public static ArrayList getClasses(final String prefix) {
        final ArrayList<Class> list = new ArrayList<Class>();
        try {
            for (final ClassPath.ClassInfo classInfo : ClassPath.from(Thread.currentThread().getContextClassLoader()).getAllClasses()) {
                if (classInfo.getName().startsWith(prefix)) {
                    list.add(classInfo.load());
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public ArrayList getModulesInCategory(final Category category) {
        final ArrayList<Module> list = new ArrayList<Module>();
        for (final Module e : ModuleManager.modules) {
            if (e.getCategory() == category) {
                list.add(e);
            }
        }
        return list;
    }
    
    public ArrayList getModuleList() {
        return ModuleManager.modules;
    }
    
    public ModuleManager() {
        (ModuleManager.modules = new ArrayList()).clear();
        ModuleManager.modules.add(new ClickGUI());
        ModuleManager.modules.add(new HUD());
        ModuleManager.modules.add(new Watermark());
        ModuleManager.modules.add(new Sprint());
        ModuleManager.modules.add(new ESP());
        ModuleManager.modules.add(new Tracers());
        ModuleManager.modules.add(new GuiWalk());
        ModuleManager.modules.add(new FullBright());
        ModuleManager.modules.add(new NameTags());
        ModuleManager.modules.add(new HitBoxMod());
        ModuleManager.modules.add(new SelfDestruct());
        ModuleManager.modules.add(new strafe());
        ModuleManager.modules.add(new NoHurtCum());
        ModuleManager.modules.add(new ArmorHUD());
        ModuleManager.modules.add(new cartridgesHUD());
        ModuleManager.modules.add(new MCF());
        ModuleManager.modules.add(new ShkafRender());
        ModuleManager.modules.add(new AimPistol());
        ModuleManager.modules.add(new ViewModel());
        ModuleManager.modules.add(new AutoClicker());
        ModuleManager.modules.add(new NoOverlay());
        ModuleManager.modules.add(new AntiAim());
        ModuleManager.modules.add(new TargetHud());
        ModuleManager.modules.add(new Notifications());
        ModuleManager.modules.add(new Clip());
        ModuleManager.modules.add(new ChinaHat());
        ModuleManager.modules.add(new PlayerEntity());
        ModuleManager.modules.add(new Coord());
        ModuleManager.modules.add(new PlayerRadar());
        ModuleManager.modules.add(new AntiAFK());
        ModuleManager.modules.add(new AntiBot());
        ModuleManager.modules.add(new AutoShiftTap());
        ModuleManager.modules.add(new Freecam());
        ModuleManager.modules.add(new NoSlowDown());
        ModuleManager.modules.add(new HClip());
        ModuleManager.modules.add(new NoPush());
        ModuleManager.modules.add(new NoFall());
        ModuleManager.modules.add(new ItemsESP());
        ModuleManager.modules.add(new BombInfo());
        ModuleManager.modules.add(new GappelHud());
        ModuleManager.modules.add(new AutoArmor());
        ModuleManager.modules.add(new JumpCircles());
        ModuleManager.modules.add(new ChestStealer());
        ModuleManager.modules.add(new Trails());
        ModuleManager.modules.add(new DeleteArmorStands());
        ModuleManager.modules.add(new HitParticles());
        ModuleManager.modules.add(new Zamorozka());
        ModuleManager.modules.add(new InvViewer());
        ModuleManager.modules.add(new AutoFishMod());
        ModuleManager.modules.add(new FogColor());
        ModuleManager.modules.add(new Xray());
        ModuleManager.modules.add(new BoatFly());
        ModuleManager.modules.add(new PlayerFinder());
        ModuleManager.modules.add(new Spammer());
        ModuleManager.modules.add(new Blink());
        ModuleManager.modules.add(new DonkeyAlert());
        ModuleManager.modules.add(new FOVchanger());
        ModuleManager.modules.add(new PenisESP());
        ModuleManager.modules.add(new AntiHuy());
        ModuleManager.modules.add(new DeathCoords());
        ModuleManager.modules.add(new NameProtect());
        ModuleManager.modules.add(new Keystrokes());
        ModuleManager.modules.add(new Radar());
        ModuleManager.modules.add(new Chams());
        ModuleManager.modules.add(new NoSmoothCamera());
        ModuleManager.modules.add(new Airshoot());
        ModuleManager.modules.add(new Crosshair());
        ModuleManager.modules.add(new AntiFog());
        ModuleManager.modules.add(new Indicators());
        ModuleManager.modules.add(new HudEditor());
        ModuleManager.modules.add(new AmmoGun());
        ModuleManager.modules.add(new Timer());
        ModuleManager.modules.add(new Exp());
        ModuleManager.modules.add(new Binding());
        ModuleManager.modules.add(new AntiInvis());
        ModuleManager.modules.add(new AutoMine());
        ModuleManager.modules.add(new WallHack());
        ModuleManager.modules.add(new BackTP());
        ModuleManager.modules.add(new Command());
        ModuleManager.modules.add(new ExpInfo());
        ModuleManager.modules.add(new ConfigLoad());
        ModuleManager.modules.add(new ConfigSave());
        ModuleManager.modules.add(new AutoHeal());
        ModuleManager.modules.add(new AutoFarm());
        ModuleManager.modules.add(new Speed());
        ModuleManager.modules.add(new WaterFast());
        ModuleManager.modules.add(new VClip());
        ModuleManager.modules.add(new AimAssist());
        ModuleManager.modules.add(new RageSprint());
        for (Module module : ModuleManager.modules) {}
    }
    
    public ArrayList getModules(final Category category) {
        final ArrayList<Module> list = new ArrayList<Module>();
        for (final Module e : ModuleManager.modules) {
            if (e.getCategory() == category) {
                list.add(e);
            }
        }
        return list;
    }
    
    public Module getModule(final Class clazz) {
        for (final Module module : ModuleManager.modules) {
            if (module.getClass() == clazz) {
                return module;
            }
        }
        return null;
    }
}
