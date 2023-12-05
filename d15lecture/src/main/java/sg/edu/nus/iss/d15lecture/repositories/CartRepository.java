package sg.edu.nus.iss.d15lecture.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.d15lecture.Utils;
import sg.edu.nus.iss.d15lecture.model.Item;

@Repository
public class CartRepository {
    
    @Autowired @Qualifier(Utils.BEAN_REDIS)
    private RedisTemplate<String, String> template;

    public Boolean hasCart(String name) {
        return template.hasKey(name);
    }

    public void deleteCart(String name){
        template.delete(name);
    }

    public void addCart(String name, List<Item> cart){

        ListOperations<String, String> list = template.opsForList();
        cart.stream()
            .forEach(item -> {
                list.leftPush(name, "%s, %d".formatted(item.getName(), item.getQuantity()));
            });

    }

    public Item getCart(String name, List<Item> cart){

        ListOperations<String, String> list = template.opsForList();
        Long size = list.size(name);
        for (String items: list.range(name, 0, size)){
            String[] terms = items.split(",");
            Item item = new Item();
            item.setName(terms[Utils.F_NAME]);
            item.setQuantity(Integer.parseInt(terms[Utils.F_QUANTITY]));
        }
        cart.add(item);

        return cart;


    }
    
}
