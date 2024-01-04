package media.soft.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderSummary {
    private int orderID;
    private String customerFullName;
    private String productType;
    private LocalDate orderDate;
    private BigDecimal price;
    private OrderStatus orderStatus;

}
