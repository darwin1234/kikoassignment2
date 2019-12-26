package assignmentgui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.RandomAccessFile;

public class Session {
	
	private String username;
	private int SessionLength;
	private String path = "src/session/userinfo.txt";
	private File file = new File(path);
	private RandomAccessFile raFile = new RandomAccessFile(file, "rw");
	
	public Session() throws IOException {
		
		String[] userData = null;
		StringBuffer buffer = new StringBuffer();
		
		while(raFile.getFilePointer() < raFile.length()) {
			buffer.append(raFile.readLine());
		}
		String contents = buffer.toString();
	
		if(contents.length() > 0) {
			userData = contents.split(",");
			SessionLength =  contents.length();
			username = userData[1];
//			for(int i = 0; i<userData.length; i++) {
//				System.out.println(userData[i]);
//			}
		}
		    
	}
	
	public void WriteSession(String username) throws IOException {
		raFile.writeBytes("1" + "," + username+ "," + "asdasdasd"+ "," + "asdasdasd");
	}
	
	public void clearUserInfoTxt() throws IOException {
		//Deleting data from an instance of the RandomAccessFile 
		raFile.setLength(0);
	}
	
	public String getUsername() {
		
		return username;
	}
	
	public int getSessionLength() {
		
		return SessionLength;
	}
	
}
