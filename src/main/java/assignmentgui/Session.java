package assignmentgui;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Session {
	
	private String username;
	private int SessionLength;
	private String path = "src/session/session.txt";
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
			//for(int i = 0; i<userData.length; i++) {
				//System.out.println(userData[i]);
			//}
		}
		
		
		//uses
		/*
		 * 
		 *     String data = "asdasdasdasdsd";
       writeToRandomAccessFile(path,50, data);
       System.out.println("String written into RandomAccessFile from Java Program : " + data);

        String fromFile = readFromRandomAccessFile(path, 50);
        System.out.println("String read from RandomAccessFile in Java : " + fromFile);
		 */
		    
	}
	
	
	
	 /*
     * Utility method to read from RandomAccessFile in Java
     */
    public static String readFromRandomAccessFile(String file, int position) {
        String record = null;
        try {
            RandomAccessFile fileStore = new RandomAccessFile(file, "rw");

            // moves file pointer to position specified
            fileStore.seek(position);

            // reading String from RandomAccessFile
            record = fileStore.readLine();

            fileStore.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return record;
    }

   /*
    * Utility method for writing into RandomAccessFile in Java
    */  
    public static void writeToRandomAccessFile(String file, int position, String record) {
        try {
            RandomAccessFile fileStore = new RandomAccessFile(file, "rw");

            // moves file pointer to position specified
            fileStore.seek(position);

            // writing String to RandomAccessFile
            fileStore.writeUTF(record);

            fileStore.close();

        } catch (IOException e) {
            e.printStackTrace();
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
	
	public void readoffset() {
		
	}
}
