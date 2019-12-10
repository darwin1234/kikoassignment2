package MongoDBConnection;

public class trackRow {
	
	private String title;
	private String artist;
	private String time;
	private String trackID;
	
	public trackRow() {
		
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setTrackID(String trackID) {
		this.trackID = trackID;
	}
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
