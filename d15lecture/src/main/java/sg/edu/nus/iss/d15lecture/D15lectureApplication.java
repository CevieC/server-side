package sg.edu.nus.iss.d15lecture;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootApplication
public class D15lectureApplication implements CommandLineRunner{

	// asking springboot to look for "myredis "
	@Autowired @Qualifier(Utils.BEAN_REDIS)
	private RedisTemplate<String, String> template;

	public static void main(String[] args) {
		SpringApplication.run(D15lectureApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.printf(">>> Redis Template: %s\n", template);

		// select 0
		ValueOperations<String, String> opsValue = template.opsForValue();
		opsValue.set("name", "fred");
		opsValue.set("email", "barney");
		opsValue.set("promo_code", "abc123");

		String count = opsValue.get("Count");
		System.out.printf("count: %s\n", count);
		String name = opsValue.get("name");
		System.out.printf("name: %s\n", name);
		opsValue.set("promo_code", "abc123", Duration.ofSeconds(30));

		// list
		ListOperations<String, String> opsList = template.opsForList();
		if (template.hasKey("fred_cart")){
			template.delete("fred_cart");
		}

		opsList.leftPush("fred_cart",  "apple");
		opsList.leftPushAll("fred_cart", "orange", "pear" );

		List<String> list = new LinkedList<>();
		list.add("durian");
		list.add("mangoes");
		opsList.rightPushAll("fred_cart", list);

		long size = opsList.size("fred_card");
		List<String> contents = opsList.range("fred_cart", 0, size);
		if(null != contents) {
			for (String c:contents) {
				System.out.printf(">>> %s\n", c);
			}
		}

		opsList.leftPop("fred_cart");
		opsList.rightPop("fred_cart");

		String item = opsList.index("fred_cart", 1L);
		System.out.printf(">>> item: %s\n", item);

		// Maps
		HashOperations<String, String, String> opsHash = template.opsForHash();
		opsHash.put("c001", "name", "fred");
		opsHash.put("c001", "email", "fred1@gmail.com");
		opsHash.put("c002", "name", "barney");
		opsHash.put("c002", "email", "barney@gmail.com");
	}

}
