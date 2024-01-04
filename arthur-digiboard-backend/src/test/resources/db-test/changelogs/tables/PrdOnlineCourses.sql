-- Online Courses table
CREATE TABLE PrdOnlineCourses (
    CourseID INT PRIMARY KEY,
    Title VARCHAR(100),
    Instructor VARCHAR(100),
    Duration INT, -- Duration in minutes, for example
    Description VARCHAR(500),
    Price DECIMAL(10, 2)
);