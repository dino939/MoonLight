//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils.xray;

import net.minecraft.util.math.*;

public class XRayBlock
{
    private XRayData xRayData;
    private BlockPos blockPos;
    
    public XRayData getxRayData() {
        return this.xRayData;
    }
    
    public XRayBlock(final BlockPos blockPos, final XRayData xRayData) {
        this.blockPos = blockPos;
        this.xRayData = xRayData;
    }
    
    public BlockPos getBlockPos() {
        return this.blockPos;
    }
}
