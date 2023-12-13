package media.soft.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class OrderPriceByDate {

    private LocalDate orderDate;
    private BigDecimal softwarePrice;
    private BigDecimal ebookPrice;
    private BigDecimal digitalMusicPrice;
    private BigDecimal onlineCoursePrice;
    private BigDecimal streamingServicePrice;

    public OrderPriceByDate() {
        this.softwarePrice = BigDecimal.ZERO;
        this.ebookPrice = BigDecimal.ZERO;
        this.digitalMusicPrice = BigDecimal.ZERO;
        this.onlineCoursePrice = BigDecimal.ZERO;
        this.streamingServicePrice = BigDecimal.ZERO;
    }

}
