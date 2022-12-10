//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package net.minecraft.client.gui;

import net.minecraftforge.fml.relauncher.*;
import net.minecraft.client.*;
import net.minecraft.util.text.*;
import com.google.common.collect.*;
import java.util.*;

@SideOnly(Side.CLIENT)
public class GuiUtilRenderComponents
{
    public static String removeTextColorsIfConfigured(final String s, final boolean b) {
        return (!b && !Minecraft.getMinecraft().gameSettings.chatColours) ? TextFormatting.getTextWithoutFormattingCodes(s) : s;
    }
    
    public static List splitText(final ITextComponent textComponent, final int n, final FontRenderer fontRenderer, final boolean b, final boolean b2) {
        int n2 = 0;
        TextComponentString textComponentString = new TextComponentString("");
        final ArrayList arrayList = Lists.newArrayList();
        final ArrayList arrayList2 = Lists.newArrayList((Iterable)textComponent);
        for (int i = 0; i < arrayList2.size(); ++i) {
            final ITextComponent textComponent2 = arrayList2.get(i);
            String str = textComponent2.getUnformattedComponentText();
            boolean b3 = false;
            if (str.contains("\n")) {
                final int index = str.indexOf(10);
                final String substring = str.substring(index + 1);
                str = str.substring(0, index + 1);
                final TextComponentString textComponentString2 = new TextComponentString(substring);
                ((ITextComponent)textComponentString2).setStyle(textComponent2.getStyle().createShallowCopy());
                arrayList2.add(i + 1, textComponentString2);
                b3 = true;
            }
            final String removeTextColorsIfConfigured = removeTextColorsIfConfigured(textComponent2.getStyle().getFormattingCode() + str, b2);
            final String s = removeTextColorsIfConfigured.endsWith("\n") ? removeTextColorsIfConfigured.substring(0, removeTextColorsIfConfigured.length() - 1) : removeTextColorsIfConfigured;
            int n3 = fontRenderer.getStringWidth(s);
            TextComponentString textComponentString3 = new TextComponentString(s);
            textComponentString3.setStyle(textComponent2.getStyle().createShallowCopy());
            if (n2 + n3 > n) {
                String s2 = fontRenderer.trimStringToWidth(removeTextColorsIfConfigured, n - n2, false);
                String substring2 = (s2.length() < removeTextColorsIfConfigured.length()) ? removeTextColorsIfConfigured.substring(s2.length()) : null;
                if (substring2 != null && !substring2.isEmpty()) {
                    int lastIndex = s2.lastIndexOf(32);
                    if (lastIndex >= 0 && fontRenderer.getStringWidth(removeTextColorsIfConfigured.substring(0, lastIndex)) > 0) {
                        s2 = removeTextColorsIfConfigured.substring(0, lastIndex);
                        if (b) {
                            ++lastIndex;
                        }
                        substring2 = removeTextColorsIfConfigured.substring(lastIndex);
                    }
                    else if (n2 > 0 && !removeTextColorsIfConfigured.contains(" ")) {
                        s2 = "";
                        substring2 = removeTextColorsIfConfigured;
                    }
                    final TextComponentString textComponentString4 = new TextComponentString(FontRenderer.getFormatFromString(s2) + substring2);
                    textComponentString4.setStyle(textComponent2.getStyle().createShallowCopy());
                    arrayList2.add(i + 1, textComponentString4);
                }
                n3 = fontRenderer.getStringWidth(s2);
                textComponentString3 = new TextComponentString(s2);
                textComponentString3.setStyle(textComponent2.getStyle().createShallowCopy());
                b3 = true;
            }
            if (n2 + n3 <= n) {
                n2 += n3;
                ((ITextComponent)textComponentString).appendSibling((ITextComponent)textComponentString3);
            }
            else {
                b3 = true;
            }
            if (b3) {
                arrayList.add(textComponentString);
                n2 = 0;
                textComponentString = new TextComponentString("");
            }
        }
        arrayList.add(textComponentString);
        return arrayList;
    }
}
