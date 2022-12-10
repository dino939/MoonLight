//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils.friend;

import ru.internali.module.*;
import akka.util.*;
import java.util.*;
import net.minecraft.util.text.*;
import ru.internali.notifications.*;
import com.google.common.reflect.*;
import com.google.common.collect.*;

public class FriendManager
{
    public static ArrayList friendsList;
    private final Module[] modules;
    public static BoundedBlockingQueue friends;
    public static List FRIENDS;
    
    public static void removeFriend(final String str) {
        if (FriendManager.friendsList.contains(str)) {
            FriendManager.friendsList.remove(str);
            notifications.add(String.valueOf(new StringBuilder().append(TextFormatting.RED).append(str)), "Remove from Friend list", Type.OK);
        }
    }
    
    public static void remove(final String s) {
    }
    
    public static ArrayList getClasses(final String prefix) {
        final ArrayList<Class> list = new ArrayList<Class>();
        try {
            for (final ClassPath.ClassInfo classInfo : ClassPath.from(Thread.currentThread().getContextClassLoader()).getAllClasses()) {
                if (!classInfo.getName().startsWith(prefix)) {
                    continue;
                }
                list.add(classInfo.load());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public static void clear() {
        if (!FriendManager.friendsList.isEmpty()) {
            FriendManager.friendsList.clear();
        }
    }
    
    static {
        FriendManager.FRIENDS = new ArrayList();
        FriendManager.friendsList = new ArrayList();
    }
    
    public Module getModule(final String anotherString) {
        for (final Module module : this.modules) {
            if (module.getName().equalsIgnoreCase(anotherString)) {
                return module;
            }
        }
        return null;
    }
    
    public static boolean isFriend(final String s) {
        return FriendManager.FRIENDS.contains(s);
    }
    
    public static void add(final String s) {
    }
    
    public FriendManager(final Module[] modules) {
        this.modules = modules;
    }
    
    public static void addFriend(final String str) {
        if (!FriendManager.friendsList.contains(str)) {
            FriendManager.friendsList.add(str);
            notifications.add(String.valueOf(new StringBuilder().append(TextFormatting.GREEN).append(str)), "add in Friends list", Type.OK);
        }
    }
    
    public static void toggleFriend(final String s) {
        if (isFriend(s)) {
            FriendManager.FRIENDS.remove(s);
            notifications.add(String.valueOf(new StringBuilder().append(TextFormatting.RED).append(s)), "Remove from Friend list", Type.OK);
        }
        else {
            FriendManager.FRIENDS.add(s);
            notifications.add(String.valueOf(new StringBuilder().append(TextFormatting.GREEN).append(s)), "add in Friends list", Type.OK);
        }
    }
}
