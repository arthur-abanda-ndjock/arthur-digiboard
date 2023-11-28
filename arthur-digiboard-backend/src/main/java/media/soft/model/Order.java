package media.soft.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Order {
    private int orderID;
    private int customerID;
    private String productType;
    private int productID;
    private Date orderDate;
}
