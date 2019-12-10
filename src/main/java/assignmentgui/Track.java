package assignmentgui;

public class Track {
		private String title;
		private String artist;
		private String time;
		public Track(String __title, String __artist, String __time) {
		// TODO Auto-generated constructor stub
			this.title = __title;
			this.artist = __artist;
			this.time = __time;
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
}


