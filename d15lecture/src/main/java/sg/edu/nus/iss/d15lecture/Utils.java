package sg.edu.nus.iss.d15lecture;

import java.util.LinkedList;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.d15lecture.model.Item;

public class Utils {

    public final static String ATTR_CART = "item";

    public final static String BEAN_REDIS = "myredis";

    public static final Integer F_NAME = 0;

    public static final Integer F_QUANTITY = 1;

    public static List<Item> getCart(HttpSession sess){

        List<Item> cart = (List<Item>)sess.getAttribute(ATTR_CART);
        if(null==cart){
            cart = new LinkedList<>();
            sess.setAttribute(ATTR_CART, cart);
        }

        
        return cart;

    }
    
}
