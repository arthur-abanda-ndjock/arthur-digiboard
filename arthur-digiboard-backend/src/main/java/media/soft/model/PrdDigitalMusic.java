package media.soft.model;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class PrdDigitalMusic {
    private int trackID;
    private String title;
    private String artist;
    private String album;
    private String genre;
    private Time duration;
    private Date releaseDate;
    private double price;
}
