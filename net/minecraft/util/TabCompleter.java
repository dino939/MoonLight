//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package net.minecraft.util;

import net.minecraftforge.fml.relauncher.*;
import net.minecraft.client.gui.*;
import java.util.*;
import net.minecraft.util.text.*;
import net.minecraftforge.client.*;
import net.minecraft.client.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.math.*;
import javax.annotation.*;
import com.google.common.collect.*;
import org.apache.commons.lang3.*;

@SideOnly(Side.CLIENT)
public abstract class TabCompleter
{
    protected final GuiTextField textField;
    protected final boolean hasTargetBlock;
    protected boolean didComplete;
    protected boolean requestedCompletions;
    protected int completionIdx;
    protected List completions;
    
    public TabCompleter(final GuiTextField textField, final boolean hasTargetBlock) {
        this.completions = Lists.newArrayList();
        this.textField = textField;
        this.hasTargetBlock = hasTargetBlock;
    }
    
    public void complete() {
        if (this.didComplete) {
            this.textField.deleteFromCursor(0);
            this.textField.deleteFromCursor(this.textField.getNthWordFromPosWS(-1, this.textField.getCursorPosition(), false) - this.textField.getCursorPosition());
            if (this.completionIdx >= this.completions.size()) {
                this.completionIdx = 0;
            }
        }
        else {
            final int getNthWordFromPosWS = this.textField.getNthWordFromPosWS(-1, this.textField.getCursorPosition(), false);
            this.completions.clear();
            this.completionIdx = 0;
            this.requestCompletions(this.textField.getText().substring(0, this.textField.getCursorPosition()));
            if (this.completions.isEmpty()) {
                return;
            }
            this.didComplete = true;
            this.textField.deleteFromCursor(getNthWordFromPosWS - this.textField.getCursorPosition());
        }
        this.textField.writeText(TextFormatting.getTextWithoutFormattingCodes((String)this.completions.get(this.completionIdx++)));
    }
    
    private void requestCompletions(final String s) {
        if (s.length() >= 1) {
            ClientCommandHandler.instance.autoComplete(s);
            Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketTabComplete(s, this.getTargetBlockPos(), this.hasTargetBlock));
            this.requestedCompletions = true;
        }
    }
    
    @Nullable
    public abstract BlockPos getTargetBlockPos();
    
    public void setCompletions(String... array) {
        if (this.requestedCompletions) {
            this.didComplete = false;
            this.completions.clear();
            final String[] latestAutoComplete = ClientCommandHandler.instance.latestAutoComplete;
            if (latestAutoComplete != null) {
                array = (String[])ObjectArrays.concat((Object[])latestAutoComplete, (Object[])array, (Class)String.class);
            }
            for (final String s : array) {
                if (!s.isEmpty()) {
                    this.completions.add(s);
                }
            }
            final String substring = this.textField.getText().substring(this.textField.getNthWordFromPosWS(-1, this.textField.getCursorPosition(), false));
            final String getTextWithoutFormattingCodes = TextFormatting.getTextWithoutFormattingCodes(StringUtils.getCommonPrefix(array));
            if (!getTextWithoutFormattingCodes.isEmpty() && !substring.equalsIgnoreCase(getTextWithoutFormattingCodes)) {
                this.textField.deleteFromCursor(0);
                this.textField.deleteFromCursor(this.textField.getNthWordFromPosWS(-1, this.textField.getCursorPosition(), false) - this.textField.getCursorPosition());
                this.textField.writeText(getTextWithoutFormattingCodes);
            }
            else if (!this.completions.isEmpty()) {
                this.didComplete = true;
                this.complete();
            }
        }
    }
    
    public void resetDidComplete() {
        this.didComplete = false;
    }
    
    public void resetRequested() {
        this.requestedCompletions = false;
    }
}
