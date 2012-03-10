package com.mrpcsync.android.data.contact;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;

public class MrPcSyncContact {
	private static MrPcSyncContact _instance = null;
	private ContentResolver mResolver;
	private String[] CONTACTS_PROJECT = { Contacts._ID, Contacts.DISPLAY_NAME};
	private String[] CONTACTS_PHONE = { Phone.NUMBER, Phone.TYPE, Phone.LABEL };
	private String[] CONTACTS_DISPLAYNAME = { StructuredName.DISPLAY_NAME };
	private String[] CONTACTS_EMAIL = { Email.DATA1, Email.TYPE, Email.LABEL };
	
	private ArrayList<String[]> displayNameList = new ArrayList<String[]>();
	private ArrayList<String[]> phoneList = new ArrayList<String[]>();
	private ArrayList<String[]> emailList = new ArrayList<String[]>();
	
	private ArrayList<String[]> contactList= new ArrayList<String[]>();

	private MrPcSyncContact(Context context) {
		mResolver = context.getContentResolver();
	}

	public static MrPcSyncContact Instance(Context context) {
		if (_instance == null) {
			_instance = new MrPcSyncContact(context);
		}
		return _instance;
	}
	
	public void getContacts(String contactId){
		Cursor cursor = null;
		try{
			cursor = mResolver.query(Data.CONTENT_URI, null, Data.CONTACT_ID+"=?", new String[]{contactId}, null);
			
			String mimeType = null;
			String[] contentValue = null;
			
			while (cursor.moveToNext()){
				mimeType = cursor.getString(cursor.getColumnIndex(Data.MIMETYPE));
				if (StructuredName.CONTENT_ITEM_TYPE.equals(mimeType)){
					contentValue = getStringInContactCursor(CONTACTS_DISPLAYNAME, cursor);
					displayNameList.add(contentValue);
				}else if(Phone.CONTENT_ITEM_TYPE.equals(mimeType)){
					contentValue = getStringInContactCursor(CONTACTS_PHONE, cursor);
					phoneList.add(contentValue);
				}else if(Email.CONTENT_TYPE.equals(mimeType)){
					contentValue = getStringInContactCursor(CONTACTS_EMAIL, cursor);
					emailList.add(contentValue);
				}
			}
		}catch (Exception e) {
		}finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}
	
	public void deleteContact(long contactid){
        mResolver.delete(ContentUris.withAppendedId(RawContacts.CONTENT_URI, contactid), null, null);
	}
	
	public void deleteContact(String name){
		String contactid;
		for (int i=0; i<contactList.size(); i++){
			String[] item = contactList.get(i);
			if (item[1].equals(name)){
				contactid = item[0];
				mResolver.delete(ContentUris.withAppendedId(RawContacts.CONTENT_URI, Long.getLong(contactid)), null, null);
			}
		}
	}

	public ArrayList<String[]> getContacts() {
		Cursor cursor = null;
		try {
			cursor = mResolver.query(ContactsContract.Contacts.CONTENT_URI, CONTACTS_PROJECT, Contacts.IN_VISIBLE_GROUP + "=1", null, null);
			int[] indexs = getColumnIndexs(CONTACTS_PROJECT, cursor);

			while (cursor.moveToNext()) {
				String[] item = new String[CONTACTS_PROJECT.length];
				for (int i=0; i<CONTACTS_PROJECT.length; i++){
					item[i] = cursor.getString(indexs[i]);
				}
				contactList.add(item);
			}
		} catch (Exception e) {
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return contactList;
	}
	
	public void insertContacts(String displayName, ArrayList<String[]> phone, ArrayList<String[]> email){
		ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
		long contactId = insertRawContacts();
		String id = Long.toString(contactId);
		if (displayName!=null){
			insertContactDisplayname(ops, StructuredName.CONTENT_ITEM_TYPE, id, displayName);
		}
		
		if (phone!=null){
			for (int i=0; i<phone.size(); i++){
				String[] item = phone.get(i);
				insertItemContacts(ops, Phone.CONTENT_ITEM_TYPE, id, CONTACTS_PHONE, item);
			}
		}
		if (email!=null){
			for (int i=0; i<phone.size(); i++){
				String[] item = phone.get(i);
				insertItemContacts(ops, Phone.CONTENT_ITEM_TYPE, id, CONTACTS_PHONE, item);
			}
		}
	}
	
	private String[] getStringInContactCursor(String[] contacts, Cursor cur){
		String[] err = new String[contacts.length];
		for (int i=0; i<err.length; i++){
			String value = cur.getString(cur.getColumnIndex(contacts[i]));
			if (value==null){
				err[i] = "";
			}else{
				err[i] = value;
			}
		}
		return err;
	}
	
	private long insertRawContacts(){
		ContentValues values = new ContentValues();
		values.put(RawContacts.ACCOUNT_NAME, "");
		values.put(RawContacts.CONTENT_TYPE, "");
		Uri uri = mResolver.insert(RawContacts.CONTENT_URI, values);
		return ContentUris.parseId(uri);
	}
	
	private void insertContactDisplayname(ArrayList<ContentProviderOperation> ops, String type, String id, String displayName){
		Builder builder = ContentProviderOperation.newInsert(Data.CONTENT_URI);
		builder.withYieldAllowed(true);
		builder.withValue(Data.MIMETYPE, type);
		builder.withValue(Data.RAW_CONTACT_ID, id);
		builder.withValue(StructuredName.DISPLAY_NAME, displayName);
		ops.add(builder.build());
	}
	
	private void insertItemContacts(ArrayList<ContentProviderOperation> ops, String mimeType, String id, String[] contact, String[] item){
		Builder builder = ContentProviderOperation.newInsert(Data.CONTENT_URI);
		builder.withYieldAllowed(true);
		builder.withValue(Data.RAW_CONTACT_ID, id);
		builder.withValue(Data.MIMETYPE, mimeType);
		for (int i=0; i<contact.length; i++){
			builder.withValue(contact[i], item[i]);
		}
		ops.add(builder.build());
	}

	private int[] getColumnIndexs(String[] contacts, Cursor cur) {
		int[] err = new int[contacts.length];
		for (int i = 0; i < err.length; i++) {
			err[i] = cur.getColumnIndex(contacts[i]);
		}
		return err;
	}
}
