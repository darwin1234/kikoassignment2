package MongoDBConnection;

public class trackRow {
	
	private String title;
	private String artist;
	private String time;
	private String trackID;
	public trackRow(String __title,String __artist, String __time, String __trackid) {
		this.title = __title;
		this.artist = __artist;
		this.time = __time;
		this.trackID = __trackid;
	}
	
	public String getTitle() {
		
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getTime() {
		return time;
	}
	
	public String getTrackID() {
		return trackID;
	}
}
