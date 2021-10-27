package com.Divya.project;

import java.util.List;

public class Tester {

	public static void main(String[] args) {
		Contact contact = new Contact(1,"name","23423444","emailMe@gmail.com","c:/myImage2.jpg","MyRingtone_mySong");
		
		ContactDaoFileSystemImpl fsImpl = new ContactDaoFileSystemImpl();
		
		fsImpl.CreateNewContact(contact);
		List<Contact> contacts = fsImpl.readContact();
		for(Contact cont:contacts)
			System.out.println(cont);
		
		ContactDaoJDBCImpl dbImpl =  new ContactDaoJDBCImpl();
		contact.setImagePath("c://Divya//DSC_0013.jpg");

		//Test create new contact
		dbImpl.CreateNewContact(contact);
		//contact.setName("ABC");
		
		//Test  update
		/*dbImpl.updateContact("JHONY", 1);
		List<Contact> contacts = dbImpl.readContact();
		for(Contact cont:contacts)
			System.out.println(cont); */
		
		//Test Delete
		//dbImpl.deleteContat(1);
		
		
		
		//Test  Thread
		//Thread myThread = new Thread(fsImpl);
		//myThread.start();
	}
}
