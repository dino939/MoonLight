//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import java.util.*;

public class ArrayHelper implements Iterable
{
    private Object[] elements;
    
    public void remove(final Object obj) {
        if (this.contains(obj)) {
            final Object[] array = new Object[this.size() - 1];
            int n = 1;
            for (int i = 0; i < this.size(); ++i) {
                if (n != 0 && this.get(i).equals(obj)) {
                    n = 0;
                }
                else {
                    array[(n != 0) ? i : (i - 1)] = this.get(i);
                }
            }
            this.set(array);
        }
    }
    
    @Override
    public Iterator iterator() {
        return null;
    }
    
    public Object get(final int n) {
        return this.array()[n];
    }
    
    public int size() {
        return this.array().length;
    }
    
    public void clear() {
        this.elements = new Object[0];
    }
    
    public boolean contains(final Object obj) {
        Object[] array;
        for (int length = (array = this.array()).length, i = 0; i < length; ++i) {
            if (array[i].equals(obj)) {
                return true;
            }
        }
        return false;
    }
    
    public void set(final Object[] elements) {
        this.elements = elements;
    }
    
    public ArrayHelper() {
        this.elements = new Object[0];
    }
    
    public void add(final Object o) {
        if (o != null) {
            final Object[] array = new Object[this.size() + 1];
            for (int i = 0; i < array.length; ++i) {
                if (i < this.size()) {
                    array[i] = this.get(i);
                }
                else {
                    array[i] = o;
                }
            }
            this.set(array);
        }
    }
    
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    public ArrayHelper(final Object[] elements) {
        this.elements = elements;
    }
    
    public Object[] array() {
        return this.elements;
    }
}
