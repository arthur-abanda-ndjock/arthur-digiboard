package media.soft.model;

import lombok.Data;

import java.sql.Date;

@Data
public class PrdSoftwareApplication {
    private int softwareID;
    private String name;
    private String version;
    private String developer;
    private String licenseType;
    private double price;
    private Date releaseDate;
    private String platform;
}
