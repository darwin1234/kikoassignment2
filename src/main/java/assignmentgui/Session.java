package assignmentgui;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Session {
	
	private String username;
	private int SessionLength;
	private static String path = "src/session/session.txt";
	private static File file = new File(path);
	private RandomAccessFile raFile = new RandomAccessFile(file, "rw");
	
	public Session() throws IOException {
		
		String[] userData = null;
		StringBuffer buffer = new StringBuffer();
		//raFile.setLength(0);
		while(raFile.getFilePointer() < raFile.length()) {
			buffer.append(raFile.readLine());
		}
		String contents = buffer.toString();
		
		if(contents.length() > 0) {
			userData = contents.split(",");
			SessionLength =  contents.length();
			username = userData[1];
		}
		
		    
	}
	
	//Inserting data into an ordered sequential file without reading the entire file into RAM
    public static String readFromRandomAccessFile(int position) {
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
    public static void writeToRandomAccessFile(int position, String record) {
        
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
    
    public static void delete(int position) {
      
        try {
            RandomAccessFile fileStore = new RandomAccessFile(file, "rw");

            // moves file pointer to position specified
            fileStore.seek(position);
        

            fileStore.writeUTF("                         ");

            fileStore.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

       
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
