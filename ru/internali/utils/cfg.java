//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import java.util.*;
import ru.internali.*;
import ru.internali.module.*;
import ru.internali.settings.*;
import net.minecraft.client.*;
import com.google.gson.*;
import java.io.*;

public class cfg
{
    public static File file;
    public static final File CONFIG_FOLDER;
    public static final File CLIENT_FOLDER;
    
    public static void init() {
        cfg.CLIENT_FOLDER.mkdir();
        cfg.CONFIG_FOLDER.mkdir();
    }
    
    private static boolean contains(final Set set, final String anObject, final JsonElement obj) {
        for (final Map.Entry<String, V> entry : set) {
            if (entry.getKey().equals(anObject) && ((JsonElement)entry.getValue()).equals(obj)) {
                return true;
            }
        }
        return false;
    }
    
    public static void load() {
        final JsonParser jsonParser = new JsonParser();
        try {
            final JsonObject asJsonObject = jsonParser.parse((Reader)new FileReader(new File(cfg.CONFIG_FOLDER, "default.json"))).getAsJsonObject();
            asJsonObject.get("auto save");
            for (final JsonElement jsonElement : asJsonObject.getAsJsonArray("modules")) {
                if (jsonElement instanceof JsonObject) {
                    final Set entrySet = ((JsonObject)jsonElement).entrySet();
                    final ModuleManager moduleManager = CatClient.instance.moduleManager;
                    for (final Module module : ModuleManager.modules) {
                        if (contains(entrySet, "name", (JsonElement)new JsonPrimitive(module.getName()))) {
                            for (final Map.Entry<String, V> entry : entrySet) {
                                final String s = entry.getKey();
                                final JsonElement jsonElement2 = (JsonElement)entry.getValue();
                                Label_0272: {
                                    try {
                                        if (!s.equals("enabled")) {
                                            break Label_0272;
                                        }
                                        module.setEnable(jsonElement2.getAsBoolean());
                                    }
                                    catch (Exception cause) {
                                        throw new RuntimeException(cause);
                                    }
                                    continue;
                                }
                                if (s.equals("keybind")) {
                                    module.setKey(jsonElement2.getAsInt());
                                }
                                else if (s.equals("PosX")) {
                                    module.setPosX(jsonElement2.getAsInt());
                                }
                                else if (s.equals("PosY")) {
                                    module.setPosY(jsonElement2.getAsInt());
                                }
                                else {
                                    if (!s.equals("settings")) {
                                        continue;
                                    }
                                    for (final JsonElement jsonElement3 : jsonElement2.getAsJsonArray()) {
                                        if (jsonElement3 instanceof JsonObject) {
                                            final Set entrySet2 = ((JsonObject)jsonElement3).entrySet();
                                            for (final Setting setting : CatClient.instance.settingsManager.getSettings(module)) {
                                                for (final Map.Entry<String, V> entry2 : entrySet2) {
                                                    final String s2 = entry2.getKey();
                                                    final JsonElement jsonElement4 = (JsonElement)entry2.getValue();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    static {
        CLIENT_FOLDER = new File(Minecraft.getMinecraft().gameDir, "None Code");
        CONFIG_FOLDER = new File(cfg.CLIENT_FOLDER, "config");
    }
    
    public static void save() {
        final JsonObject jsonObject = new JsonObject();
        final JsonArray jsonArray = new JsonArray();
        final ModuleManager moduleManager = CatClient.instance.moduleManager;
        for (final Module module : ModuleManager.modules) {
            final JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("name", module.getName());
            jsonObject2.addProperty("enabled", Boolean.valueOf(module.isEnable()));
            jsonObject2.addProperty("keybind", (Number)module.getKey());
            jsonObject2.addProperty("PosX", (Number)module.getPosX());
            jsonObject2.addProperty("PosY", (Number)module.getPosY());
            final JsonArray jsonArray2 = new JsonArray();
            for (final Setting setting : CatClient.instance.settingsManager.getSettings(module)) {
                jsonArray2.add((JsonElement)new JsonObject());
            }
            jsonObject2.add("settings", (JsonElement)jsonArray2);
            jsonArray.add((JsonElement)jsonObject2);
        }
        jsonObject.add("modules", (JsonElement)jsonArray);
        try {
            final FileWriter fileWriter = new FileWriter(new File(cfg.CONFIG_FOLDER, "default.json"));
            fileWriter.write(new GsonBuilder().setPrettyPrinting().create().toJson((JsonElement)jsonObject));
            fileWriter.flush();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
