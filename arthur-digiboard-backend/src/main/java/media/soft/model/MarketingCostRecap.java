package media.soft.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MarketingCostRecap {

    private int costId;
    private String subcategoryName;
    private String categoryName;
    private double cost;
    private LocalDate dateRecorded;
}
