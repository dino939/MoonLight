//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import java.net.*;
import java.io.*;
import java.util.*;

public class WebUtils
{
    public static String agent2;
    public static String agent1;
    
    static {
        WebUtils.agent1 = "User-Agent";
        WebUtils.agent2 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36";
    }
    
    public static String visitSite(final String spec) {
        final ArrayList<String> list = new ArrayList<String>();
        String value = "";
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(spec).openConnection();
            httpURLConnection.addRequestProperty(WebUtils.agent1, WebUtils.agent2);
            String line;
            while ((line = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8")).readLine()) != null) {
                list.add(line);
            }
        }
        catch (Exception ex) {}
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            value = String.valueOf(new StringBuilder().append(value).append(iterator.next()));
        }
        return value;
    }
}
