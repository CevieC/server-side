package sg.edu.nus.iss.d15lecture.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.d15lecture.Utils;
import sg.edu.nus.iss.d15lecture.model.Item;
import sg.edu.nus.iss.d15lecture.repositories.CartRepository;
import sg.edu.nus.iss.d15lecture.service.CartService;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

    private Logger logger = Logger.getLogger(CartController.class.getName());

    public static final String ATTR_ITEM = "item";
    public static final String ATTR_CART = "cart";
    public List<Item> cart = new LinkedList<>();

    @Autowired
    private CartService cartSvc;

    @GetMapping
    public String getCard(@RequestParam String name, Model model, HttpSession sess) {

        List<Item> cart = cartSvc.getCart(name);

        logger.info("CART: %s - %s\n".formatted(name, cart));

        sess.setAttribute(Utils.ATTR_CART, cart);
        

        return "cart";


    }

    @PostMapping(path = "/checkout")
    public ModelAndView postCartCheckout(HttpSession sess){

        ModelAndView mav = new ModelAndView("cart");

        List<Item> cart = Utils.getCart(sess);
        System.out.printf("checking out cart: %s\n", cart);
        sess.invalidate();


        mav.addObject("Item", new Item());
        mav.setStatus(HttpStatusCode.valueOf(200));

        return mav;

    }

    @PostMapping(path = "/add")
    public ModelAndView postCart(@Valid @ModelAttribute(ATTR_ITEM) Item item, BindingResult bindings, HttpSession sess) {
        System.out.printf("item: %s\n", item);
        System.out.println(bindings.hasErrors());

        ModelAndView mav = new ModelAndView("cart");

        if(bindings.hasErrors()){
            mav.setStatus(HttpStatusCode.valueOf(400));
            return mav;
        }

        List<Item> cart = Utils.getCart(sess);
        cart.add(item);
        
        mav.addObject("Item", new Item());
        mav.setStatus(HttpStatusCode.valueOf(200));
        return mav;
    }
    
}
