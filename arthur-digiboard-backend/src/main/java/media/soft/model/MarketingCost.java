package media.soft.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MarketingCost {

    private int id;
    private int subcategoryId;
    private double cost;
    private LocalDate dateRecorded;

}
