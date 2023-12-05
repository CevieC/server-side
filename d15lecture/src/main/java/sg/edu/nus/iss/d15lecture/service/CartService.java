package sg.edu.nus.iss.d15lecture.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.d15lecture.Utils;
import sg.edu.nus.iss.d15lecture.model.Item;
import sg.edu.nus.iss.d15lecture.repositories.CartRepository;

@Service
public class CartService {

    @Autowired @Qualifier(Utils.BEAN_REDIS)
    private CartRepository cartRepo;

    public List<Item> getCart(String name) {
        if(cartRepo.hasCart(name)){
            return cartRepo.getCart(name);
        }
        return new LinkedList<>(); 
        
    }
    
}
