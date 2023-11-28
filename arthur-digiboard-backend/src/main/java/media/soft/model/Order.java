package media.soft.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;


@Data
public class Order {
    private int orderID;
    private int customerID;
    private String productType;
    private int productID;
    private LocalDate orderDate;
    private BigDecimal price;
    
}
