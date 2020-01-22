package assignmentgui;

public class Track {
		private String title;
		private String artist;
		private String time;
		private String id;
		public Track(String __title, String __artist, String __time,String id) {
		// TODO Auto-generated constructor stub
			this.title = __title;
			this.artist = __artist;
			this.time = __time;
			this.id =  id;
		}
		public String id() {
			return id;
		}
		public String getTitle() {
			return title;
		}
		
		public void setTitle(String title) {
			this.title = title;
		}
		public String getArtist() {
			return artist;
		}
		public void setArtist(String artist) {
			this.artist = artist;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		
		
}


