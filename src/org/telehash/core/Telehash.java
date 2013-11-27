package org.telehash.core;

import org.telehash.crypto.Crypto;
import org.telehash.crypto.impl.CryptoImpl;
import org.telehash.network.Network;
import org.telehash.network.impl.NetworkImpl;
import org.telehash.storage.Storage;
import org.telehash.storage.impl.StorageImpl;

public class Telehash {
    
    private Crypto mCrypto;
    private Storage mStorage;
    private Network mNetwork;
    private Identity mIdentity;
    private Switch mSwitch;

    public Telehash() {
        mCrypto = new CryptoImpl();
        mStorage = new StorageImpl();
        mNetwork = new NetworkImpl();
        mIdentity = null;
        mSwitch = null;
    }
    
    public Telehash(Identity identity) {
        mCrypto = new CryptoImpl();
        mStorage = new StorageImpl();
        mNetwork = new NetworkImpl();
        mIdentity = identity;
        mSwitch = null;
    }
    
    public Crypto getCrypto() {
        return mCrypto;
    }
    
    public Storage getStorage() {
        return mStorage;
    }
    
    public Network getNetwork() {
        return mNetwork;
    }
    
    public void setIdentity(Identity identity) {
        mIdentity = identity;
    }
    public Identity getIdentity() {
        return mIdentity;
    }
    
    public void setSwitch(Switch telehashSwitch) {
        mSwitch = telehashSwitch;
    }
    public Switch getSwitch() {
        return mSwitch;
    }
}