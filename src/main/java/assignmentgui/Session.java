package assignmentgui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Session {
	
	private String username;
	private int SessionLength;

	public Session() throws IOException {
		String path = "src/session/userinfo.txt";
		
		File file = new File(path); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String st; 
		String[] userData = null;
		while ((st = br.readLine()) != null) {
			userData = st.split(",");
			SessionLength = userData.length;
			//uncomment for debuging purposes
			//System.out.println(userData[1]);
		}
		br.close();
	}
	
	public void WriteSession(String username) {
		
	}
	
	public String getUsername() {
		
		return username;
	}
	
	public int getSessionLength() {
		
		return SessionLength;
	}
	
}
