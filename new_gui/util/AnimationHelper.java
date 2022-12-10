//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package new_gui.util;

import net.minecraft.client.*;

public class AnimationHelper
{
    public static int deltaTime;
    private State currentState;
    private long currentStateStart;
    private int time;
    private boolean initialState;
    private State previousState;
    
    public static float calculateCompensation(final float n, float n2, long n3, final double n4) {
        final float n5 = n2 - n;
        if (n3 < 1L) {
            n3 = 1L;
        }
        if (n3 > 1000L) {
            n3 = 16L;
        }
        if (n5 > n4) {
            n2 -= (float)((n4 * n3 / 16.0 < 0.5) ? 0.5 : (n4 * n3 / 16.0));
            if (n2 < n) {
                n2 = n;
            }
        }
        else if (n5 < -n4) {
            n2 += (float)((n4 * n3 / 16.0 < 0.5) ? 0.5 : (n4 * n3 / 16.0));
            if (n2 > n) {
                n2 = n;
            }
        }
        else {
            n2 = n;
        }
        return n2;
    }
    
    public static double animate(final double n, double n2, double n3) {
        final boolean b = n > n2;
        if (n3 < 0.0) {
            n3 = 0.0;
        }
        else if (n3 > 1.0) {
            n3 = 1.0;
        }
        double n4 = (Math.max(n, n2) - Math.min(n, n2)) * n3;
        if (n4 < 0.1) {
            n4 = 0.1;
        }
        n2 = (n2 = (b ? (n2 + n4) : (n2 - n4)));
        return n2;
    }
    
    public double getAnimationFactor() {
        if (this.currentState == State.EXPANDING) {
            return (System.currentTimeMillis() - this.currentStateStart) / (double)this.time;
        }
        if (this.currentState == State.RETRACTING) {
            return (this.time - (System.currentTimeMillis() - this.currentStateStart)) / (double)this.time;
        }
        return (this.previousState == State.EXPANDING) ? 1.0 : 0.0;
    }
    
    public AnimationHelper(final int time, final boolean initialState) {
        this.previousState = State.STATIC;
        this.currentState = State.STATIC;
        this.currentStateStart = 0L;
        this.time = time;
        this.initialState = initialState;
        if (initialState) {
            this.previousState = State.EXPANDING;
        }
    }
    
    public static float animation(final float n, final float n2, final float a) {
        float n3 = (n2 - n) / Minecraft.getDebugFPS() * 15.0f;
        if (n3 > 0.0f) {
            n3 = Math.min(n2 - n, Math.max(a, n3));
        }
        else if (n3 < 0.0f) {
            n3 = Math.max(n2 - n, Math.min(-a, n3));
        }
        return n + n3;
    }
    
    public enum State
    {
        STATIC("STATIC", 2), 
        RETRACTING("RETRACTING", 1), 
        EXPANDING("EXPANDING", 0);
        
        private static final State[] $VALUES;
        
        static {
            $VALUES = new State[] { State.EXPANDING, State.RETRACTING, State.STATIC };
        }
        
        private State(final String name, final int ordinal) {
        }
    }
}
