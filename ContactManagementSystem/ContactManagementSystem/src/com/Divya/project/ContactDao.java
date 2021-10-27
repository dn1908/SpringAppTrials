package com.Divya.project;

import java.util.List;

public interface ContactDao {
	
	public List<Contact> readContact();
	
	public List<Contact> readContact(String name);
	
	public boolean deleteContat(int id);
	
	public boolean deleleContact(String name);
	
	public boolean updateContact(String name,int id);
	
	public boolean updateContact(int id,String eMail);
	
	public boolean assignPhoto(int id, String imagePath);
	
	public boolean assignRingtone(int id, String ringTone);
	
	public boolean CreateNewContact(Contact contact);

}
