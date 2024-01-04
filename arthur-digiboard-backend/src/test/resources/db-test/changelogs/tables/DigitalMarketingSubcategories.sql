-- Table for Digital Marketing Subcategories
CREATE TABLE DigitalMarketingSubcategories (
    id INT PRIMARY KEY,
    subcategory_name VARCHAR(255) NOT NULL,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES DigitalMarketingCategories(id)
);