package com.Divya.project;

import java.util.List;

public class Contact {

	private long Id;
	private String name;
	private String email;
	private String number;
	private String imagePath;
	private String ringTone;

	public Contact(long id, String name, String email, String number, String imagePath, String ringTone) {
		super();
		this.Id = id;
		this.name = name;
		this.email = email;
		this.number = number;
		this.imagePath = imagePath;
		this.ringTone = ringTone;
	}

	public Contact() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRingtone() {
		return ringTone;
	}

	public void setRingtone(String ringtone) {
		this.ringTone = ringtone;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public long getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

	@Override
	public String toString() {
		return "Contact [Id=" + Id + ", name=" + name + ", email=" + email + ", number=" + number + ", imagePath="
				+ imagePath + ", ringTone=" + ringTone + "]";
	}
	
}
