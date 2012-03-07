package com.mr;


public class MrPcSyncHead {
    private byte[] mOut;
    private int headLength = 128;
    private int totalLength = 2048+headLength;
    public MrPcSyncHead(){
        mOut = new byte[totalLength];
    }
    
    public void setHead(String head){
        byte[] buffhead = head.getBytes();
        try {
            for (int i=0; i<headLength; i++){
                if (i<buffhead.length){
                	mOut[i] = buffhead[i];
                }else{
                	mOut[i] = '\0';
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setMsg(String head){
        try {
            byte[] buffer = head.getBytes();
            for (int i=0; i<totalLength-headLength; i++){
               if (i<buffer.length){
            	   mOut[i+headLength] = buffer[i];
               }else{
            	   mOut[i+headLength] = '\0';
               }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] getBuffer() {
        return mOut;
    }
    
    public String getHead(byte[] buff){
    	byte[] buffer = new byte[headLength];
        for (int i=0; i<buffer.length; i++){
        	buffer[i] = buff[i];
        }
        return buffer.toString();
    }
    
    public String getMsg(byte[] buff){
    	byte[] buffer = new byte[totalLength-headLength];
        for (int i=headLength; i<buffer.length; i++){
        	buffer[i] = buff[i];
        }
        return buffer.toString();
    }
}
