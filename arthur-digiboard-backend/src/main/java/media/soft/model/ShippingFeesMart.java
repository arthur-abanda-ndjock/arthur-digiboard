package media.soft.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShippingFeesMart {
	private Integer id;
	private BigDecimal totalDailyFees;
	private LocalDateTime asofdate;
}
