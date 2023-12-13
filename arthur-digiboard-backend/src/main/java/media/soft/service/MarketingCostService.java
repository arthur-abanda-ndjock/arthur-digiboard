package media.soft.service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import media.soft.model.CategoryCost;
import media.soft.model.MarketingCost;
import media.soft.model.MarketingCostRecap;
import media.soft.model.MarketingCostWeeklyRecap;
import media.soft.repository.MarketingCostsRepositoryDao;

@Service
public class MarketingCostService {

    Logger logger = LoggerFactory.getLogger(MarketingCostService.class);
    private final MarketingCostsRepositoryDao marketingCostDao;

    public MarketingCostService(@Autowired MarketingCostsRepositoryDao repositoryDao) {
        this.marketingCostDao = repositoryDao;
    }

    public void insertPrdStreamingServices(MarketingCost cost) {
        marketingCostDao.insert(cost);
    }

    public void updatePrdStreamingServicesById(int id, MarketingCost cost) {
        marketingCostDao.updateById(id, cost);
    }

    public void deletePrdStreamingServicesById(int id) {
        marketingCostDao.deleteById(id);
    }

    public List<MarketingCost> getAll() {
        return marketingCostDao.getAll();
    }

    public List<CategoryCost> getTotalAmountsByCategory() {
        return marketingCostDao.getTotalAmountByCategory();
    }

    public List<CategoryCost> getTotalAmountsBySubcategory() {
        return marketingCostDao.getTotalAmountBySubcategory();
    }

    public List<MarketingCostRecap> getRecentCost() {
        return marketingCostDao.getRecentCost();
    }

    public Map<String, List<CategoryCost>> getTotalsByAllCategories() {

        LinkedHashMap<String, List<CategoryCost>> map = new LinkedHashMap<>();

        map.put("categories", marketingCostDao.getTotalAmountByCategory());
        map.put("subcategories", marketingCostDao.getTotalAmountBySubcategory());

        return map;
    }

    public List<MarketingCostWeeklyRecap> getMarketingCostRecapByWeek(int maxRecapWeeks) {

        List<MarketingCostWeeklyRecap> recap = new ArrayList<>();
        int maxWeeks = maxRecapWeeks;

        for (int i = maxWeeks; i >= 0; i--) {
            LocalDate latestOrderDate = marketingCostDao.getLatestMarketingCostDate();
            LocalDate startOfWeek = latestOrderDate
                    .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                    .minusWeeks(i);
            LocalDate endOfWeek = latestOrderDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).minusWeeks(i);
            logger.info("{}. week start {} \t week end {}", i, startOfWeek, endOfWeek);

            List<MarketingCost> costs = marketingCostDao.getMarketingCostsByPeriod(startOfWeek, endOfWeek);
            Double weeklySale = costs.stream().map(MarketingCost::getCost).reduce(0D, Double::sum);
            logger.info("week cost count: {}", costs.size());
            logger.info("week cost price: {}", weeklySale);

            String weekId = (i == 0) ? "this week" : "W-" + i;
            recap.add(new MarketingCostWeeklyRecap(weekId, weeklySale));
        }
        return recap;
    }

    public BigDecimal getMarketingCostSum() {
        return marketingCostDao.getMarketingCostSum();
    }
}
