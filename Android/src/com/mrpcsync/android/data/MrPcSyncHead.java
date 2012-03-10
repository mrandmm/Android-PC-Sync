package com.mrpcsync.android.data;



public class MrPcSyncHead {
    private byte[] mIn;
    private byte[] mOut;
    private final static int headLength = 20;
    private final static int cmdLength = 1024;
    private final static int length = headLength+cmdLength;

    public MrPcSyncHead() {
        mOut = new byte[length];
    }

    public void setReadBuffer(byte[] buffer) {
        this.mIn = buffer;
    }
    
    public String getHeadString() {
        return toString(mIn, 0, headLength);
    }
    
    public byte[] getHeadBuffer() {
        byte[] buff = new byte[headLength];
        for (int i=0; i<headLength; i++){
            buff[i] = mOut[i];
        }
        return buff;
    }
    
    public String getMsgCmd(int i){
    	return getMsgCmd()[i];
    }
    
    public String[] getMsgCmd(){
        return toString(mIn, headLength, cmdLength).split("#", 0);
    }
    
    public byte[] getMsgBuffer() {
        byte[] buff = new byte[cmdLength];
        for (int i=0; i<cmdLength; i++){
            buff[i] = mOut[headLength+i];
        }
        return buff;
    }

    public String getMsgString() {
        return toString(mIn, headLength, cmdLength);
    }

    public void setHead(String head) {
        byte[] buffer = head.getBytes();
        for (int i=0; i<headLength; i++){
            if (i<buffer.length){
                mOut[i] = buffer[i];
            }else{
                mOut[i] = '0';
            }
        }
    }
    
    public void setMsg(String msg) {
        byte[] buffer = msg.getBytes();
        for (int i=0; i<cmdLength; i++){
            if (i<buffer.length){
                mOut[i+headLength] = buffer[i];
            }else{
                mOut[i+headLength] = '0';
            }
        }
    }
    
    public String toString(byte[] buffer, int start, int end){
        String buff = "";
        for (int i=start; i<end ;i++){
            if ((char)buffer[i]!='0'){
                buff+=(char)buffer[i];
            }else{
                break;
            }
        }
        return buff;
    }
    
    public String toString(byte[] buffer){
        String buff = null;
        try {
            buff = new String(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buff;
    }

    public byte[] getBuffer() {
        return mOut;
    }

    public static int getLength() {
        return length;
    }
}
