package MongoDBConnection;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
	
	static MongoDatabase db;
	public MongoConnection() {
		MongoClient mongo_client = new MongoClient("localhost",27017);
		db = mongo_client.getDatabase("assignment");
		
	}
	
	

}
