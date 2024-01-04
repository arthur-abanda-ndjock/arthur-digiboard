package media.soft.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import media.soft.model.BalanceWeeklyRecap;
import media.soft.model.MarketingCostWeeklyRecap;
import media.soft.model.SalesWeeklyRecap;
import media.soft.model.ShippingFeesMart;
import media.soft.repository.ShippingFeesMartDao;

@Service
public class ECommerceService {

	private ShippingFeesMartDao shippingFeesMartDao;
	private OrdersService ordersService;
	private MarketingCostService marketingCostService;

	Logger logger = LoggerFactory.getLogger(ECommerceService.class);

	public ECommerceService(@Autowired ShippingFeesMartDao shippingFeesMartDao, @Autowired OrdersService ordersService,
			@Autowired MarketingCostService marketingCostService) {
		this.shippingFeesMartDao = shippingFeesMartDao;
		this.ordersService = ordersService;
		this.marketingCostService = marketingCostService;
	}

	public List<ShippingFeesMart> getAllShippingFees() {
		return shippingFeesMartDao.getAllData();
	}

	public List<BalanceWeeklyRecap> getBalanceWeeklyRecap() {

		List<BalanceWeeklyRecap> balanceWeeklyRecaps = new ArrayList<>();
		int maxRecapWeeks = 10;
		List<MarketingCostWeeklyRecap> marketingCostWeeklyRecap = marketingCostService
				.getMarketingCostRecapByWeek(maxRecapWeeks);
		List<SalesWeeklyRecap> salesWeeklyRecap = ordersService.getWeeklySaleRecaps(maxRecapWeeks);

		for (MarketingCostWeeklyRecap marketingRecap : marketingCostWeeklyRecap) {
			for (SalesWeeklyRecap saleWeeklyRecap : salesWeeklyRecap) {
				if (marketingRecap.getWeekId().equalsIgnoreCase(saleWeeklyRecap.getWeekId())) {
					Double balance = saleWeeklyRecap.getPrice().doubleValue() - marketingRecap.getAmount();
					balanceWeeklyRecaps.add(new BalanceWeeklyRecap(marketingRecap.getWeekId(),
							saleWeeklyRecap.getPrice(), marketingRecap.getAmount(), balance));
				}
			}
		}
		return balanceWeeklyRecaps;
	}

	public BigDecimal getBalance() {
		return ordersService.getOrderAmountSum().subtract(marketingCostService.getMarketingCostSum());
	}

}
