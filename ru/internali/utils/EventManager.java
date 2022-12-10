//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import java.lang.reflect.*;
import java.lang.annotation.*;
import java.util.*;

public class EventManager
{
    private static final Map REGISTRY_MAP;
    
    public void unregister(final Object obj, final Class clazz) {
        if (EventManager.REGISTRY_MAP.containsKey(clazz)) {
            for (final Data data : EventManager.REGISTRY_MAP.get(clazz)) {
                if (data.source.equals(obj)) {
                    EventManager.REGISTRY_MAP.get(clazz).remove((Object)data);
                }
            }
            cleanMap(true);
        }
    }
    
    public static ArrayHelper get(final Class clazz) {
        return EventManager.REGISTRY_MAP.get(clazz);
    }
    
    public static void shutdown() {
        EventManager.REGISTRY_MAP.clear();
    }
    
    public void removeEnty(final Class obj) {
        final Iterator<Map.Entry<Class<?>, V>> iterator = EventManager.REGISTRY_MAP.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getKey().equals(obj)) {
                iterator.remove();
                break;
            }
        }
    }
    
    private static boolean isMethodBad(final Method method) {
        return method.getParameterTypes().length != 1 || !method.isAnnotationPresent(EventTarget.class);
    }
    
    public static void unregister(final Object obj) {
        for (final ArrayHelper arrayHelper : EventManager.REGISTRY_MAP.values()) {
            for (final Data data : arrayHelper) {
                if (data.source.equals(obj)) {
                    arrayHelper.remove((Object)data);
                }
            }
        }
        cleanMap(true);
    }
    
    public static void cleanMap(final boolean b) {
        final Iterator<Map.Entry<K, ArrayHelper>> iterator = EventManager.REGISTRY_MAP.entrySet().iterator();
        while (iterator.hasNext()) {
            if (!b || iterator.next().getValue().isEmpty()) {
                iterator.remove();
            }
        }
    }
    
    private static boolean isMethodBad(final Method method, final Class obj) {
        return isMethodBad(method) || method.getParameterTypes()[0].equals(obj);
    }
    
    public static void register(final Object o, final Class clazz) {
        for (final Method method : o.getClass().getDeclaredMethods()) {
            if (!isMethodBad(method, clazz)) {
                register(method, (Class)o);
            }
        }
    }
    
    public static void register(final Object o) {
        for (final Method method : o.getClass().getDeclaredMethods()) {
            if (!isMethodBad(method)) {
                register(method, (Class)o);
            }
        }
    }
    
    static {
        REGISTRY_MAP = new HashMap();
    }
}
