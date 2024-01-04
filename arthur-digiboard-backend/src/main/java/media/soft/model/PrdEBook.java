package media.soft.model;

import lombok.Data;

import java.sql.Date;

@Data
public class PrdEBook {
    private int bookID;
    private String title;
    private String author;
    private String genre;
    private String ISBN;
    private double price;
    private Date publicationDate;
}
