package MongoDBConnection;

public class Row {

	  // REMEMBER: each attribute is a column
    //
    private final String title;
    private final String description;
    private final String price;
    private final String location;
    private final String date;
    private final String id;
    private final String imagepath;
    private final String genre;
    private final String author;
    private final String foreignkey;
    
    public Row(String title, String description, String price, String location,String date, String id, String imagepath,String __genre,String __author,String Fkey)
    {
        this.title = title;
        this.description = description;
        this.price = price;
        this.location = location;
        this.date =  date;
        this.id = id;
        this.imagepath = imagepath;
        this.genre = __genre;
        this.author = __author;
        this.foreignkey = Fkey;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

    public String getDate() {
    	return date;
    }
    public String getPrice()
    {
        return price;
    }
    
    public String getLocation() {
    	return location;
    }
    public String getID() {
    	System.out.println(id);
    	return id;
    	
    }
    
    public String getImage() {
    	return imagepath;
    }
    
    public String getGenre() {
    	return genre;
    }
    
    public String getAuthor() {
    	return author;
    }
    
    public String getforeignkey() {
    	return foreignkey;
    }

}
