package media.soft.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MarketingCostWeeklyRecap {

    private String weekId;
    private double amount;
}
