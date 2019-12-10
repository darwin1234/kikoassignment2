package MongoDBConnection;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class Login extends MongoConnection{
	
	public Login() {
		// TODO Auto-generated constructor stub
	
	}
	
	
	public boolean verify(String username, String password) {
		boolean res = false;
		MongoCollection<Document> UserLogin  = db.getCollection("users");
		
		BasicDBObject obj = new BasicDBObject();
		obj.put("username", username);
		obj.put("password", password );
	
		Document result = UserLogin.find(obj).first();
		if(result != null) {
			String resultJsonString = result.toJson();
			if(resultJsonString.isEmpty()) {
				res = false;
			}else {
				res = true;
			}
	
		}

		return res;
	}

}
