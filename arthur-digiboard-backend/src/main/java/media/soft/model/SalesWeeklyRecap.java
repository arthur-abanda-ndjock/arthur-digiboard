package media.soft.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalesWeeklyRecap {
    private String weekId;
    private BigDecimal price;

}
