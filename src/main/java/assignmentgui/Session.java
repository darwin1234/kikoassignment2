package assignmentgui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Session {
	
	private String username;
	private int SessionLength;
	private String path = "src/session/userinfo.txt";
	public Session() throws IOException {
		
		
		File file = new File(path); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String st; 
		String[] userData = null;
		while ((st = br.readLine()) != null) {
			userData = st.split(",");
			SessionLength = userData.length;
			//uncomment for debuging purposes
			//System.out.println(userData[1]);
			username = userData[1];
		}
		br.close();
	}
	
	public void WriteSession(String username) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(path, "UTF-8");
		writer.print("1"+ "," + username + ",asdasd,asdasdasd");
		writer.close();	
	}
	
	public String getUsername() {
		
		return username;
	}
	
	public int getSessionLength() {
		
		return SessionLength;
	}
	
}
