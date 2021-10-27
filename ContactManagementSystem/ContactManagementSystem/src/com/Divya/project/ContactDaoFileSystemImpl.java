package com.Divya.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoFileSystemImpl implements ContactDao,Runnable {

	private String contactFileStorePath = "C:\\Divya\\myFile.txt";
	private final String COMMA_DELIMIT = ",";

	private final int CONTACT_ID_IDX = 0;
	private final int CONTACT_NAME_IDX = 1;
	private final int CONTACT_NUMBER_IDX = 2;
	private final int CONTACT_EMAIL_IDX = 3;
	private final int CONTACT_PHOTO = 4;
	private final int CONTACT_RINGTONE = 5;

	@Override
	public List<Contact> readContact() {
		FileReader fin = null;
		List<Contact> contactList = new ArrayList<Contact>();
		try {
			fin = new FileReader(contactFileStorePath);

			BufferedReader br = new BufferedReader(fin);
			String lineStr = null;
			while ((lineStr = br.readLine()) != null) {
				String[] strValues = lineStr.split(COMMA_DELIMIT);
				if (strValues.length > 0) {
					Contact contact = new Contact(Long.parseLong(strValues[CONTACT_ID_IDX]),
							strValues[CONTACT_NAME_IDX], strValues[CONTACT_NUMBER_IDX], strValues[CONTACT_EMAIL_IDX],
							strValues[CONTACT_PHOTO], strValues[CONTACT_RINGTONE]);
					contactList.add(contact);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return contactList;
	}

	@Override
	public List<Contact> readContact(String name) {

		final int CONTACT_ID_IDX = 0;
		final int CONTACT_NAME_IDX = 1;
		final int CONTACT_NUMBER_IDX = 2;
		final int CONTACT_EMAIL_IDX = 3;
		final int CONTACT_PHOTO = 4;
		final int CONTACT_RINGTONE = 5;

		FileReader fin = null;
		List<Contact> contactList = new ArrayList<Contact>();
		try {
			fin = new FileReader(contactFileStorePath);

			BufferedReader br = new BufferedReader(fin);
			String lineStr = null;
			while ((lineStr = br.readLine()) != null) {
				if (lineStr.contains(name)) {
					String[] strValues = lineStr.split(COMMA_DELIMIT);
					if (strValues.length > 0) {
						Contact contact = new Contact(Long.parseLong(strValues[CONTACT_ID_IDX]),
								strValues[CONTACT_NAME_IDX], strValues[CONTACT_NUMBER_IDX],
								strValues[CONTACT_EMAIL_IDX], strValues[CONTACT_PHOTO], strValues[CONTACT_RINGTONE]);
						contactList.add(contact);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return contactList;
	}

	@Override
	public boolean deleteContat(int id) {
		FileWriter fTempWriter = null;
		String tmpFileName = contactFileStorePath;
		File origFile = new File(contactFileStorePath);
		File tempFile = new File(tmpFileName.replace(".txt", "_copy.txt"));

		try {
			fTempWriter = new FileWriter(tempFile);
			BufferedReader br = new BufferedReader(new FileReader(origFile));
			String lineStr = null;
			while ((lineStr = br.readLine()) != null) {
				if (!lineStr.contains(String.valueOf(id))) {
					fTempWriter.write(lineStr);
					fTempWriter.flush();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fTempWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		tempFile.renameTo(origFile);
		return true;
	}

	@Override
	public boolean deleleContact(String name) {
		FileWriter fTempWriter = null;
		String tmpFileName = contactFileStorePath;
		File origFile = new File(contactFileStorePath);
		File tempFile = new File(tmpFileName.replace(".txt", "_copy.txt"));
		BufferedReader br = null;
		try {
			// fin = new FileReader(contactFileStorePath);
			fTempWriter = new FileWriter(tempFile);
			br = new BufferedReader(new FileReader(origFile));
			String lineStr = null;
			while ((lineStr = br.readLine()) != null) {
				if (!lineStr.contains(String.valueOf(name))) {
					fTempWriter.write(lineStr);
					fTempWriter.flush();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fTempWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		tempFile.renameTo(origFile);
		return true;
	}

	@Override
	public boolean updateContact(String name, int id) {
		FileWriter fTempWriter = null;
		String tmpFileName = contactFileStorePath;
		File origFile = new File(contactFileStorePath);
		File tempFile = new File(tmpFileName.replace(".txt", "_copy.txt"));
		BufferedReader br = null;
		try {
			fTempWriter = new FileWriter(tempFile);
			br = new BufferedReader(new FileReader(origFile));
			String lineStr = null;
			while ((lineStr = br.readLine()) != null) {
				if (lineStr.contains(String.valueOf(id))) {
					final int CONTACT_NAME_IDX = 1;
					String[] strValues = lineStr.split(COMMA_DELIMIT);
					String strNameToReplace = strValues[CONTACT_NAME_IDX];
					lineStr.replace(strNameToReplace, name);
					fTempWriter.write(lineStr);
					fTempWriter.flush();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fTempWriter.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		tempFile.renameTo(origFile);
		return true;
	}

	@Override
	public boolean updateContact(int id, String eMail) {

		FileWriter fTempWriter = null;
		BufferedReader br = null;

		String tmpFileName = contactFileStorePath;
		File origFile = new File(contactFileStorePath);
		File tempFile = new File(tmpFileName.replace(".txt", "_copy.txt"));

		try {
			fTempWriter = new FileWriter(tempFile);
			br = new BufferedReader(new FileReader(origFile));
			String lineStr = null;
			while ((lineStr = br.readLine()) != null) {
				if (lineStr.contains(String.valueOf(id))) {
					String[] strValues = lineStr.split(COMMA_DELIMIT);
					String strNameToReplace = strValues[CONTACT_EMAIL_IDX];
					lineStr.replace(strNameToReplace, eMail);
					fTempWriter.write(lineStr);
					fTempWriter.flush();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fTempWriter.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		tempFile.renameTo(origFile);
		return true;
	}

	@Override
	public boolean assignPhoto(int id, String imagePath) {

		FileWriter fTempWriter = null;
		BufferedReader br = null;

		String tmpFileName = contactFileStorePath;
		File origFile = new File(contactFileStorePath);
		File tempFile = new File(tmpFileName.replace(".txt", "_copy.txt"));

		try {
			fTempWriter = new FileWriter(tempFile);
			br = new BufferedReader(new FileReader(origFile));
			String lineStr = null;
			while ((lineStr = br.readLine()) != null) {
				if (lineStr.contains(String.valueOf(id))) {
					String[] strValues = lineStr.split(COMMA_DELIMIT);
					String strNameToReplace = strValues[CONTACT_PHOTO];
					lineStr.replace(strNameToReplace, imagePath);
					fTempWriter.write(lineStr);
					fTempWriter.flush();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fTempWriter.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		tempFile.renameTo(origFile);
		return true;

	}

	@Override
	public boolean assignRingtone(int id, String ringTone) {
		FileWriter fTempWriter = null;
		BufferedReader br = null;

		String tmpFileName = contactFileStorePath;
		File origFile = new File(contactFileStorePath);
		File tempFile = new File(tmpFileName.replace(".txt", "_copy.txt"));

		try {
			fTempWriter = new FileWriter(tempFile);
			br = new BufferedReader(new FileReader(origFile));
			String lineStr = null;
			while ((lineStr = br.readLine()) != null) {
				if (lineStr.contains(String.valueOf(id))) {
					String[] strValues = lineStr.split(COMMA_DELIMIT);
					String strNameToReplace = strValues[CONTACT_RINGTONE];
					lineStr.replace(strNameToReplace, ringTone);
					fTempWriter.write(lineStr);
					fTempWriter.flush();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fTempWriter.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		tempFile.renameTo(origFile);
		return true;
	}

	public boolean AppendHeader(String contactFileStorePath) {
		File file = new File(contactFileStorePath);
		if (file.exists()) {
			FileReader fr;
			try {
				fr = new FileReader(file);
				if (fr.read() == -1) {
					return true;
				} else
					return false;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean CreateNewContact(Contact contact) {
		final String NEWLINE_DELIMIT = "\\n";
		final String FILE_HEADER = "Id,Name,Number,Email,Photo,Ringtone \n";
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(contactFileStorePath);

			if (AppendHeader(contactFileStorePath)) {
				fileWriter.append(FILE_HEADER.toString());
				fileWriter.append(NEWLINE_DELIMIT);
			}

			fileWriter.append(String.valueOf(contact.getId()));
			fileWriter.append(COMMA_DELIMIT);
			fileWriter.append(contact.getName());
			fileWriter.append(COMMA_DELIMIT);
			fileWriter.append(contact.getNumber());
			fileWriter.append(COMMA_DELIMIT);
			fileWriter.append(contact.getEmail());
			fileWriter.append(COMMA_DELIMIT);
			fileWriter.append(contact.getImagePath());
			fileWriter.append(COMMA_DELIMIT);
			fileWriter.append(contact.getRingtone());
			fileWriter.append(NEWLINE_DELIMIT);

			fileWriter.flush();
			fileWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void run() {
		List<Contact> contactList = readContact();
		for(Contact cont:contactList)
			System.out.println(cont);
	}

}
