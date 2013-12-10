package org.telehash.core;

import java.util.Map;

public class Channel {
    private ChannelIdentifier mChannelIdentifier;
    private String mType;
    private ChannelHandler mChannelHandler;
    
    private Telehash mTelehash;
    private Line mLine;
    private boolean mSentFirstPacket = false;
    
    public Channel(Telehash telehash, Line line, String type) {
        mTelehash = telehash;
        mLine = line;
        mChannelIdentifier = new ChannelIdentifier(
                Util.getCryptoInstance().getRandomBytes(ChannelIdentifier.CHANNEL_IDENTIFIER_SIZE)
        );
        mType = type;
    }
    
    public Channel(Telehash telehash, Line line, ChannelIdentifier channelIdentifer, String type) {
        mTelehash = telehash;
        mLine = line;
        mChannelIdentifier = channelIdentifer;
        mType = type;
    }
    
    public void setLine(Line line) {
        mLine = line;
    }
    
    public Line getLine() {
        return mLine;
    }
    
    public Node getRemoteNode() {
        return mLine.getRemoteNode();
    }
    
    public ChannelIdentifier getChannelIdentifier() {
        return mChannelIdentifier;
    }
    
    public void setChannelIdentifier(ChannelIdentifier channelIdentifier) {
        mChannelIdentifier = channelIdentifier;
    }
    
    public String getType() {
        return mType;
    }
    
    public void setType(String type) {
        mType = type;
    }
    
    public ChannelHandler getChannelHandler() {
        return mChannelHandler;
    }
    
    public void setChannelHandler(ChannelHandler channelHandler) {
        mChannelHandler = channelHandler;
    }
    
    public void send(byte[] body) throws TelehashException {
        send(body, null, false);
    }

    public void send(byte[] body, Map<String,Object> fields, boolean end) throws TelehashException {
        ChannelPacket channelPacket = new ChannelPacket();
        channelPacket.setChannelIdentifier(mChannelIdentifier);
        if (! mSentFirstPacket) {
            // "type" is only sent for the first packet in a channel
            channelPacket.setType(mType);            
            mSentFirstPacket = true;
        }
        if (fields != null) {
            for (Map.Entry<String,Object> field : fields.entrySet()) {
                channelPacket.put(field.getKey(), field.getValue());
            }
        }
        if (end) {
            channelPacket.put("end", true);
            // TODO: remove from Line's channel tracking
        }
        mTelehash.getSwitch().sendLinePacket(
                mLine,
                channelPacket,
                null,
                null
        );

    }
}