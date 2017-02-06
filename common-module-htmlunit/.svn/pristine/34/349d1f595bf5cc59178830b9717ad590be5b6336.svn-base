package test.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonTest {
	
	public static void main(String[] args) {
		People people = new People();
		people.setFirstname("aaa");
		people.setLastname("bbb");
		people.setAge(null);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(people)); 
		
	}

}
