//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.sun.crypto.provider;

import java.security.spec.*;
import java.security.*;
import javax.crypto.*;

public final class BlowfishCipher extends CipherSpi
{
    private CipherCore core;
    
    public BlowfishCipher() {
        this.core = null;
        this.core = new CipherCore(new BlowfishCrypt(), 8);
    }
    
    @Override
    protected void engineSetMode(final String mode) throws NoSuchAlgorithmException {
        this.core.setMode(mode);
    }
    
    @Override
    protected void engineSetPadding(final String padding) throws NoSuchPaddingException {
        this.core.setPadding(padding);
    }
    
    @Override
    protected int engineGetBlockSize() {
        return 8;
    }
    
    @Override
    protected int engineGetOutputSize(final int inputLen) {
        return this.core.getOutputSize(inputLen);
    }
    
    @Override
    protected byte[] engineGetIV() {
        return this.core.getIV();
    }
    
    @Override
    protected AlgorithmParameters engineGetParameters() {
        return this.core.getParameters("Blowfish");
    }
    
    @Override
    protected void engineInit(final int opmode, final Key key, final SecureRandom random) throws InvalidKeyException {
        this.core.init(opmode, key, random);
    }
    
    @Override
    protected void engineInit(final int opmode, final Key key, final AlgorithmParameterSpec params, final SecureRandom random) throws InvalidKeyException, InvalidAlgorithmParameterException {
        this.core.init(opmode, key, params, random);
    }
    
    @Override
    protected void engineInit(final int opmode, final Key key, final AlgorithmParameters params, final SecureRandom random) throws InvalidKeyException, InvalidAlgorithmParameterException {
        this.core.init(opmode, key, params, random);
    }
    
    @Override
    protected byte[] engineUpdate(final byte[] input, final int inputOffset, final int inputLen) {
        return this.core.update(input, inputOffset, inputLen);
    }
    
    @Override
    protected int engineUpdate(final byte[] input, final int inputOffset, final int inputLen, final byte[] output, final int outputOffset) throws ShortBufferException {
        return this.core.update(input, inputOffset, inputLen, output, outputOffset);
    }
    
    @Override
    protected byte[] engineDoFinal(final byte[] input, final int inputOffset, final int inputLen) throws IllegalBlockSizeException, BadPaddingException {
        return this.core.doFinal(input, inputOffset, inputLen);
    }
    
    @Override
    protected int engineDoFinal(final byte[] input, final int inputOffset, final int inputLen, final byte[] output, final int outputOffset) throws IllegalBlockSizeException, ShortBufferException, BadPaddingException {
        return this.core.doFinal(input, inputOffset, inputLen, output, outputOffset);
    }
    
    @Override
    protected int engineGetKeySize(final Key key) throws InvalidKeyException {
        return key.getEncoded().length * 8;
    }
    
    @Override
    protected byte[] engineWrap(final Key key) throws IllegalBlockSizeException, InvalidKeyException {
        return this.core.wrap(key);
    }
    
    @Override
    protected Key engineUnwrap(final byte[] wrappedKey, final String wrappedKeyAlgorithm, final int wrappedKeyType) throws InvalidKeyException, NoSuchAlgorithmException {
        return this.core.unwrap(wrappedKey, wrappedKeyAlgorithm, wrappedKeyType);
    }
}
