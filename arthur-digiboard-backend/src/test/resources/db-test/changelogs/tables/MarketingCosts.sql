-- Table for Marketing Costs
CREATE TABLE MarketingCosts (
    id INT PRIMARY KEY,
    subcategory_id INT,
    cost DECIMAL(10, 2),
    date_recorded DATE,
    FOREIGN KEY (subcategory_id) REFERENCES DigitalMarketingSubcategories(id)
);
