//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import ru.internali.*;
import java.lang.reflect.*;
import java.util.*;

public abstract class Event
{
    private boolean cancelled;
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public Event call() {
        this.cancelled = false;
        call(this);
        return this;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    private static void call(final Event event) {
        final EventManager eventManager = CatClient.eventManager;
        final ArrayHelper value = EventManager.get(event.getClass());
        if (value != null) {
            for (final Data data : value) {
                try {
                    data.target.invoke(data.source, event);
                }
                catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
                catch (InvocationTargetException ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }
}
