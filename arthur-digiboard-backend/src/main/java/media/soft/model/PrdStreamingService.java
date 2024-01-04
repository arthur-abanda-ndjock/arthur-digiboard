package media.soft.model;

import lombok.Data;

@Data
public class PrdStreamingService {
    private int serviceID;
    private String name;
    private String platform;
    private double subscriptionCost;
    private int contentLibrarySize;
}
