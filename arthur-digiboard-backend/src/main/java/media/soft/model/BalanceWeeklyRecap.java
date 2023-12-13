package media.soft.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BalanceWeeklyRecap {

    private String weekId;
    private BigDecimal eowPrice;
    private double eowCost;
    private double eowBalance;
}
