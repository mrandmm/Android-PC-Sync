package com.mr;

import android.telephony.SmsManager;

public class MrPcSyncMessage {
    private static MrPcSyncMessage _instance = null;
    private SmsManager mSms;

    private MrPcSyncMessage() {
        mSms = SmsManager.getDefault();
    }

    public static MrPcSyncMessage Instance() {
        if (_instance == null) {
            _instance = new MrPcSyncMessage();
        }
        return _instance;
    }

    public void sendMessage(Contact contact) {
        mSms.sendTextMessage(contact.getPhone(), null, contact.getMessage(),
                null, null);
    }
    static class Contact {

        public Contact() {
        }

        public Contact(String phone, String message) {
            setPhone(phone);
            setMessage(message);
        }

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        private String phone;
        private String message;
    }
}
