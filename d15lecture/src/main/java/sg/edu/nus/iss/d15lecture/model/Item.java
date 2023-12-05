package sg.edu.nus.iss.d15lecture.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Item {
    
    @NotNull
    private String name;

    @Min(value = 1, message = "you must order at least one item")
    @Max(value = 10, message = "you canot order more than 10 items")
    private Integer quantity;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item [name=" + name + ", quantity=" + quantity + "]";
    }
    
}
