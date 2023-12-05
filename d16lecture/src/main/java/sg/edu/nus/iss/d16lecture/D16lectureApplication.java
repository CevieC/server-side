package sg.edu.nus.iss.d16lecture;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonValue;

@SpringBootApplication
public class D16lectureApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(D16lectureApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// // Get a JsonObjectBuilder
		// JsonObjectBuilder builder = Json.createObjectBuilder();
		// builder = builder.add("firstName", "John");
		// builder = builder.add("lastName", "Doe");
		// builder = builder.add("age", 60);
		// builder = builder.add("married", true);

		// // Create JSON object
		// JsonObject john = builder.build();

		// another way to create

		JsonObject john = Json.createObjectBuilder()
			.add("firstName", "John")
			.add("lastName", "Doe")
			.add("age", 50)
			.add("married", true)
			.build();

		JsonObject wilma = Json.createObjectBuilder()
			.add("firstName", "Wila")
			.add("lastName", "Doe")
			.add("married", true)
			.add("age", 50)
			.add("height", 1.69)
			.add("spouse", john)
			.build();
		
		JsonArray does = Json.createArrayBuilder()
			.add(john)
			.add(wilma)
			.build();


		System.out.printf(">>> John: \n %s\n", john.toString());

		String name = wilma.getString("firstName", "not set");
		Boolean married = wilma.getBoolean("married");
		Integer age = wilma.getInt("age", 0);
		float height = (float)wilma.getJsonNumber("height").doubleValue();
		JsonObject spouse = wilma.getJsonObject("spouse");

		// arrays

		for(int i = 0; i < does.size(); i++){

			JsonObject o = does.getJsonObject(i);
			System.out.printf("%d  >>>>>> %s\n", i, o.toString()); // can .toString() or don't
			
		}

		System.out.println(">>>>>>>>>>>>STREAM<<<<<<<<<<<<");
		does.stream()
			.map(v -> v.asJsonObject())
			.forEach(jo -> System.out.printf("STREAM : %s\n", jo.toString()));
	}


}
