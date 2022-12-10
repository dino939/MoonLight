//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali;

import java.awt.*;
import ru.internali.clickgui.*;
import ru.internali.settings.*;
import ru.internali.utils.HudEditor.*;
import click.*;
import new_gui.*;
import clickgui.*;
import ru.internali.utils.*;
import java.net.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.*;
import org.lwjgl.input.*;
import ru.internali.module.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.security.*;
import net.minecraftforge.common.*;
import java.nio.charset.*;
import java.io.*;

public class CatClient
{
    public DraggableManager draggableManager;
    public static Color color;
    public ModuleManager moduleManager;
    public ClickGuiScreen clickGui;
    public static EventManager eventManager;
    public SettingsManager settingsManager;
    public static HudEditor hudEditor;
    public static CatClient instance;
    private static CatClient gate;
    public ClickGui guiw;
    public static Config config;
    public CSGOGui csgui;
    public static final EventManager EVENT_MANAGER;
    public static Clickgui guib;
    
    public static Color setColor() {
        try {
            return new Color(ColorUtils.astolfoColors((int)(Math.abs(System.currentTimeMillis() / 10L) / 100.0 + 0.16999999999999998), 5));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return new Color(30, 30, 30, 150);
        }
    }
    
    public static String requestURLSRC(final String spec) throws IOException {
        final URLConnection openConnection = new URL(spec).openConnection();
        openConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        return AP2iKAwcS2gFL8cX8z944ZiJp2zS54T68Tp39nr2rJAwh(openConnection.getInputStream());
    }
    
    static {
        CatClient.color = Color.red;
        CatClient.color = Color.red;
        EVENT_MANAGER = new EventManager();
    }
    
    public static String UID() {
        return "UID: PlutoSolutions";
    }
    
    public static int getColor() {
        return ColorUtils.TwoColoreffect(Color.blue, Color.MAGENTA, Math.abs(System.currentTimeMillis() / 10L) / 100.0 + 0.16999999999999998).getRGB();
    }
    
    @SubscribeEvent
    public void key(final InputEvent.KeyInputEvent keyInputEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null) {
            return;
        }
        try {
            if (Keyboard.isCreated() && Keyboard.getEventKeyState()) {
                final int eventKey = Keyboard.getEventKey();
                if (eventKey <= 0) {
                    return;
                }
                final ModuleManager moduleManager = this.moduleManager;
                for (final Module module : ModuleManager.modules) {
                    if (module.getKey() == eventKey && eventKey > 0) {
                        module.toggle();
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static CatClient getGate() {
        return CatClient.gate;
    }
    
    public static Color getClientColor() {
        return CatClient.color;
    }
    
    public static String getHWID() {
        try {
            final String value = String.valueOf(new StringBuilder().append(System.getenv("COMPUTERNAME")).append(System.getProperty("user.name")).append(System.getenv("PROCESSOR_IDENTIFIER")).append(System.getenv("PROCESSOR_LEVEL")));
            final MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(value.getBytes());
            final StringBuffer sb = new StringBuffer();
            final byte[] digest = instance.digest();
            for (int length = digest.length, i = 0; i < length; ++i) {
                final String hexString = Integer.toHexString(0xFF & digest[i]);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "Error";
        }
    }
    
    public void init() throws Exception {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.settingsManager = new SettingsManager();
        this.moduleManager = new ModuleManager();
        this.clickGui = new ClickGuiScreen();
        this.csgui = new CSGOGui();
        this.guiw = new ClickGui();
        this.draggableManager = new DraggableManager();
        CatClient.hudEditor = new HudEditor();
        CatClient.guib = new Clickgui();
        CatClient.config = new Config();
    }
    
    private static String AP2iKAwcS2gFL8cX8z944ZiJp2zS54T68Tp39nr2rJAwh(final InputStream in) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        final Throwable t = null;
        try {
            final StringBuilder obj = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                obj.append(line);
            }
            final String value = String.valueOf(obj);
            if (bufferedReader != null) {
                if (t != null) {
                    try {
                        bufferedReader.close();
                    }
                    catch (Throwable exception) {
                        t.addSuppressed(exception);
                    }
                }
                else {
                    bufferedReader.close();
                }
            }
            return value;
        }
        catch (Throwable t2) {
            throw t2;
        }
    }
}
