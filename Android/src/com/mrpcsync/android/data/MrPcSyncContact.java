package com.mrpcsync.android.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;

public class MrPcSyncContact {
    private static MrPcSyncContact _instance = null;
    private ContentResolver mResolver;
    private Uri mUri;
    private ContentValues mValues;
    private static long mId;
    private Cursor mCursor;
    private List<Contact> mContacts = new ArrayList<Contact>();
    private String[] contacts = {Contacts._ID, Contacts.HAS_PHONE_NUMBER, Contacts.DISPLAY_NAME};

    private MrPcSyncContact(Context context) {
        mResolver = context.getContentResolver();
    }
    
    public byte[] getContactByte(){
        try{
            mCursor = mResolver.query(ContactsContract.Contacts.CONTENT_URI, contacts, Contacts.IN_VISIBLE_GROUP+"1", null, null);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        CharArrayBuffer charBuffer = new CharArrayBuffer(mCursor.getColumnCount());
        mCursor.copyStringToBuffer(mCursor.getColumnCount(), charBuffer);
        return charBuffer.data.toString().getBytes();
    }
    
    public Contact getItemContact(){
        Contact contact = new Contact();
        String id = mCursor.getString(mCursor.getColumnIndex(ContactsContract.Contacts._ID));
        contact.setId(id);
        String name = mCursor.getString(mCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        contact.setName(name);
        String phone = mCursor.getString(mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        contact.setPhone(phone);
        mContacts.add(contact);
        return contact;
    }
    
    public List<Contact> getContact(){
        while (mCursor.moveToNext()){
            getItemContact();
        }
        mCursor.close();
        return mContacts;
    }

    public static MrPcSyncContact Instance(Context context) {
        if (_instance == null) {
            _instance = new MrPcSyncContact(context);
        }
        return _instance;
    }

    private void nextInsert() {
        mValues = new ContentValues();
        mUri = mResolver.insert(RawContacts.CONTENT_URI, mValues);
        mId = ContentUris.parseId(mUri);
    }

    public void deleteContact(long contactid) {
        mResolver.delete(
                ContentUris.withAppendedId(RawContacts.CONTENT_URI, contactid),
                null, null);
    }

    public void insertContact(Contact contact) {
        nextInsert();

        if (contact.getName() != null) {
            mValues.clear();
            mValues.put(Data.RAW_CONTACT_ID, mId);
            mValues.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
            mValues.put(StructuredName.GIVEN_NAME, contact.getName());
            mResolver.insert(ContactsContract.Data.CONTENT_URI, mValues);
        }

        if (contact.getPhone() != null) {
            mValues.clear();
            mValues.put(Data.RAW_CONTACT_ID, mId);
            mValues.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
            mValues.put(Phone.NUMBER, contact.getPhone());
            mValues.put(Phone.TYPE, Phone.TYPE_MOBILE);
            mResolver.insert(ContactsContract.Data.CONTENT_URI, mValues);
        }

        if (contact.getEmail() != null) {
            mValues.clear();
            mValues.put(Data.RAW_CONTACT_ID, mId);
            mValues.put(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE);
            mValues.put(Email.DATA, contact.getEmail());
            mValues.put(Email.TYPE, Email.TYPE_HOME);
            mResolver.insert(ContactsContract.Data.CONTENT_URI, mValues);
        }
    }

    public static class Contact {
        private String id;
        private String name;
        private String phone;
        private String email;
        
        public Contact(){
        }

        public Contact(String name, String phone, String email) {
            setId(mId+"");
            setName(name);
            setPhone(phone);
            setEmail(email);
        }
        
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
