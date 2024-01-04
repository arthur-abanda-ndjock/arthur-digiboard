package media.soft.model;
import lombok.Data;

@Data
public class PrdOnlineCourse {
    private int courseID;
    private String title;
    private String instructor;
    private int duration;
    private String description;
    private double price;
}

