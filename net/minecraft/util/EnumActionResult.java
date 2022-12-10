//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package net.minecraft.util;

public enum EnumActionResult
{
    SUCCESS("SUCCESS", 0), 
    PASS("PASS", 1), 
    FAIL("FAIL", 2);
    
    private static final EnumActionResult[] $VALUES;
    
    private EnumActionResult(final String name, final int ordinal) {
    }
    
    static {
        $VALUES = new EnumActionResult[] { EnumActionResult.SUCCESS, EnumActionResult.PASS, EnumActionResult.FAIL };
    }
}
