-- Insert sample data into Orders table
-- Insert sample data into Orders table with Price field
INSERT INTO Orders (OrderID, CustomerID, ProductType, ProductID, OrderDate, Price)
VALUES
    (1, 1, 'Software', 1, '2023-09-05', 29.99),
    (2, 2, 'EBook', 2, '2023-09-10', 9.99),
    (3, 3, 'DigitalMusic', 1, '2023-09-15', 0.99),
    (4, 1, 'OnlineCourse', 1, '2023-09-20', 49.99),
    (5, 2, 'StreamingService', 2, '2023-09-25', 14.99),
    (6, 2, 'Software', 4, '2023-09-07', 39.99),
    (7, 3, 'EBook', 3, '2023-09-12', 12.99),
    (8, 1, 'DigitalMusic', 4, '2023-09-17', 1.99),
    (9, 4, 'OnlineCourse', 3, '2023-09-22', 54.99),
    (10, 5, 'StreamingService', 3, '2023-09-28', 16.99),
    (11, 6, 'Software', 3, '2023-09-03', 29.99),
    (12, 1, 'EBook', 4, '2023-09-14', 12.99),
    (13, 2, 'DigitalMusic', 3, '2023-09-19', 1.99),
    (14, 3, 'OnlineCourse', 4, '2023-09-24', 59.99),
    (15, 4, 'StreamingService', 4, '2023-09-30', 19.99),
    (16, 7, 'Software', 5, '2023-09-03', 34.99),
    (17, 8, 'EBook', 6, '2023-09-08', 9.99),
    (18, 9, 'DigitalMusic', 5, '2023-09-13', 1.99),
    (19, 7, 'OnlineCourse', 5, '2023-09-18', 49.99),
    (20, 8, 'StreamingService', 5, '2023-09-23', 14.99),
    (21, 9, 'Software', 6, '2023-09-28', 39.99),
    (22, 7, 'EBook', 5, '2023-09-02', 7.99),
    (23, 8, 'DigitalMusic', 6, '2023-09-07', 1.99),
    (24, 9, 'OnlineCourse', 6, '2023-09-12', 59.99),
    (25, 7, 'StreamingService', 6, '2023-09-17', 19.99),
    (26, 8, 'Software', 3, '2023-09-22', 29.99),
    (27, 9, 'EBook', 4, '2023-09-27', 12.99),
    (28, 7, 'DigitalMusic', 3, '2023-09-01', 0.99),
    (29, 8, 'OnlineCourse', 4, '2023-09-06', 54.99),
    (30, 9, 'StreamingService', 4, '2023-09-11', 16.99),
    (31, 10, 'Software', 5, '2023-09-05', 29.99),
    (32, 11, 'EBook', 6, '2023-09-10', 9.99),
    (33, 12, 'DigitalMusic', 5, '2023-09-15', 0.99),
    (34, 10, 'OnlineCourse', 5, '2023-09-20', 49.99),
    (35, 11, 'StreamingService', 5, '2023-09-25', 14.99),
    (36, 12, 'Software', 6, '2023-09-30', 39.99),
    (37, 10, 'EBook', 5, '2023-09-03', 7.99),
    (38, 11, 'DigitalMusic', 6, '2023-09-08', 1.99),
    (39, 12, 'OnlineCourse', 6, '2023-09-13', 59.99),
    (40, 10, 'StreamingService', 6, '2023-09-18', 19.99),
    (41, 11, 'Software', 3, '2023-09-23', 29.99),
    (42, 12, 'EBook', 4, '2023-09-28', 12.99),
    (43, 10, 'DigitalMusic', 3, '2023-09-02', 0.99),
    (44, 11, 'OnlineCourse', 4, '2023-09-07', 54.99),
    (45, 12, 'StreamingService', 4, '2023-09-12', 16.99),
    (46, 10, 'Software', 4, '2023-09-17', 34.99),
    (47, 11, 'EBook', 5, '2023-09-22', 9.99),
    (48, 12, 'DigitalMusic', 4, '2023-09-27', 1.99),
    (49, 10, 'OnlineCourse', 5, '2023-09-01', 49.99),
    (50, 11, 'StreamingService', 5, '2023-09-06', 14.99),
    (51, 13, 'Software', 5, '2023-09-10', 29.99),
    (52, 14, 'EBook', 6, '2023-09-15', 9.99),
    (53, 15, 'DigitalMusic', 5, '2023-09-20', 0.99),
    (54, 13, 'OnlineCourse', 5, '2023-09-25', 49.99),
    (55, 14, 'StreamingService', 5, '2023-09-23',30.00),
    (56, 15, 'Software', 6, '2023-09-05', 39.99),
    (57, 13, 'EBook', 5, '2023-09-11', 7.99),
    (58, 14, 'DigitalMusic', 6, '2023-09-16', 1.99),
    (59, 15, 'OnlineCourse', 6, '2023-09-21', 59.99),
    (60, 13, 'StreamingService', 6, '2023-09-26', 19.99),
    (61, 14, 'Software', 3, '2023-09-01', 29.99),
    (62, 15, 'EBook', 4, '2023-09-06', 12.99),
    (63, 13, 'DigitalMusic', 3, '2023-09-11', 0.99),
    (64, 14, 'OnlineCourse', 4, '2023-09-16', 54.99),
    (65, 15, 'StreamingService', 4, '2023-09-21', 16.99),
    (66, 13, 'Software', 4, '2023-09-26', 34.99),
    (67, 14, 'EBook', 5, '2023-09-02', 9.99),
    (68, 15, 'DigitalMusic', 4, '2023-09-07', 1.99),
    (69, 13, 'OnlineCourse', 5, '2023-09-12', 49.99),
    (70, 14, 'StreamingService', 5, '2023-09-17', 14.99),
    (71, 13, 'Software', 3, '2023-09-22', 29.99),
    (72, 14, 'EBook', 4, '2023-09-27', 12.99),
    (73, 15, 'DigitalMusic', 3, '2023-09-01', 0.99),
    (74, 13, 'OnlineCourse', 4, '2023-09-06', 54.99),
    (75, 14, 'StreamingService', 4, '2023-09-11', 16.99),
    (76, 15, 'Software', 5, '2023-09-16', 29.99),
    (77, 13, 'EBook', 6, '2023-09-21', 9.99),
    (78, 14, 'DigitalMusic', 5, '2023-09-26', 0.99),
    (79, 15, 'OnlineCourse', 5, '2023-09-02', 49.99),
    (80, 13, 'StreamingService', 5, '2023-09-07', 14.99),
    (81, 14, 'Software', 6, '2023-09-12', 39.99),
    (82, 15, 'EBook', 5, '2023-09-17', 7.99),
    (83, 13, 'DigitalMusic', 6, '2023-09-22', 1.99),
    (84, 14, 'OnlineCourse', 6, '2023-09-27', 59.99),
    (85, 15, 'StreamingService', 6, '2023-09-01', 19.99),
    (86, 13, 'Software', 4, '2023-09-06', 34.99),
    (87, 14, 'EBook', 5, '2023-09-11', 9.99),
    (88, 15, 'DigitalMusic', 4, '2023-09-16', 1.99),
    (89, 13, 'OnlineCourse', 5, '2023-09-21', 49.99),
    (90, 14, 'StreamingService', 5, '2023-09-26', 16.99),
    (91, 13, 'Software', 5, '2023-09-30', 29.99),
    (92, 14, 'EBook', 6, '2023-09-05', 9.99),
    (93, 15, 'DigitalMusic', 5, '2023-09-10', 0.99),
    (94, 13, 'OnlineCourse', 5, '2023-09-15', 49.99),
    (95, 14, 'StreamingService', 5, '2023-09-20', 14.99),
    (96, 15, 'Software', 6, '2023-09-25', 39.99),
    (97, 13, 'EBook', 5, '2023-09-11', 7.99),
    (98, 14, 'DigitalMusic', 6, '2023-09-16', 1.99),
    (99, 15, 'OnlineCourse', 6, '2023-09-21', 59.99),
    (100, 13, 'StreamingService', 6, '2023-09-26', 19.99),
    (101, 14, 'Software', 3, '2023-09-01', 29.99),
    (102, 15, 'EBook', 4, '2023-09-06', 12.99),
    (103, 13, 'DigitalMusic', 3, '2023-09-11', 0.99),
    (104, 14, 'OnlineCourse', 4, '2023-09-16', 54.99),
    (105, 15, 'StreamingService', 4, '2023-09-21', 16.99),
    (106, 13, 'Software', 4, '2023-09-26', 34.99),
    (107, 14, 'EBook', 5, '2023-09-02', 9.99),
    (108, 15, 'DigitalMusic', 4, '2023-09-07', 1.99),
    (109, 13, 'OnlineCourse', 5, '2023-09-12', 49.99),
    (110, 14, 'StreamingService', 5, '2023-09-17', 14.99),
    (111, 15, 'Software', 6, '2023-09-22', 39.99),
    (112, 13, 'EBook', 4, '2023-09-27', 7.99),
    (113, 14, 'DigitalMusic', 3, '2023-09-01', 0.99),
    (114, 15, 'OnlineCourse', 4, '2023-09-06', 54.99),
    (115, 13, 'StreamingService', 4, '2023-09-11', 16.99),
    (116, 14, 'Software', 5, '2023-09-16', 29.99),
    (117, 15, 'EBook', 6, '2023-09-21', 9.99),
    (118, 13, 'DigitalMusic', 5, '2023-09-26', 0.99),
    (119, 14, 'OnlineCourse', 5, '2023-09-02', 49.99),
    (120, 15, 'StreamingService', 5, '2023-09-07', 14.99),
    (121, 13, 'Software', 6, '2023-09-12', 39.99),
    (122, 14, 'EBook', 5, '2023-09-17', 7.99),
    (123, 15, 'DigitalMusic', 6, '2023-09-22', 1.99),
    (124, 13, 'OnlineCourse', 6, '2023-09-27', 59.99),
    (125, 14, 'StreamingService', 6, '2023-09-01', 19.99),
    (126, 15, 'Software', 4, '2023-09-06', 34.99),
    (127, 13, 'EBook', 5, '2023-09-11', 9.99),
    (128, 14, 'DigitalMusic', 4, '2023-09-16', 1.99),
    (129, 15, 'OnlineCourse', 5, '2023-09-21', 49.99),
    (130, 13, 'StreamingService', 5, '2023-09-26', 16.99),
    (131, 14, 'Software', 5, '2023-09-30', 29.99),
    (132, 15, 'EBook', 6, '2023-09-05', 9.99),
    (133, 13, 'DigitalMusic', 5, '2023-09-10', 0.99),
    (134, 14, 'OnlineCourse', 5, '2023-09-15', 49.99),
    (135, 15, 'StreamingService', 5, '2023-09-20', 14.99),
    (136, 13, 'Software', 6, '2023-09-25', 39.99),
    (137, 14, 'EBook', 5, '2023-09-11', 7.99),
    (138, 15, 'DigitalMusic', 6, '2023-09-16', 1.99),
    (139, 13, 'OnlineCourse', 6, '2023-09-21', 59.99),
    (140, 14, 'StreamingService', 6, '2023-09-26', 19.99),
    (141, 14, 'Software', 3, '2023-09-01', 29.99),
    (142, 15, 'EBook', 4, '2023-09-06', 12.99),
    (143, 13, 'DigitalMusic', 3, '2023-09-11', 0.99),
    (144, 14, 'OnlineCourse', 4, '2023-09-16', 54.99),
    (145, 15, 'StreamingService', 4, '2023-09-21', 16.99),
    (146, 13, 'Software', 4, '2023-09-26', 34.99),
    (147, 14, 'EBook', 5, '2023-09-02', 9.99),
    (148, 15, 'DigitalMusic', 4, '2023-09-07', 1.99),
    (149, 13, 'OnlineCourse', 5, '2023-09-12', 49.99),
    (150, 14, 'StreamingService', 5, '2023-09-17', 14.99),
    (151, 15, 'Software', 6, '2023-09-22', 39.99),
    (152, 13, 'EBook', 4, '2023-09-27', 7.99),
    (153, 14, 'DigitalMusic', 3, '2023-09-01', 0.99),
    (154, 15, 'OnlineCourse', 4, '2023-09-06', 54.99),
    (155, 13, 'StreamingService', 4, '2023-09-11', 16.99),
    (156, 14, 'Software', 5, '2023-09-16', 29.99),
    (157, 15, 'EBook', 6, '2023-09-21', 9.99),
    (158, 13, 'DigitalMusic', 5, '2023-09-26', 0.99),
    (159, 14, 'OnlineCourse', 5, '2023-09-02', 49.99),
    (160, 15, 'StreamingService', 5, '2023-09-07', 14.99),
    (161, 13, 'Software', 6, '2023-09-12', 39.99),
    (162, 14, 'EBook', 5, '2023-09-17', 7.99),
    (163, 15, 'DigitalMusic', 6, '2023-09-22', 1.99),
    (164, 13, 'OnlineCourse', 6, '2023-09-27', 59.99),
    (165, 14, 'StreamingService', 6, '2023-09-01', 19.99),
    (166, 15, 'Software', 4, '2023-09-06', 34.99),
    (167, 16, 'Software', 5, '2023-10-05', 29.99),
    (168, 17, 'EBook', 6, '2023-10-10', 9.99),
    (169, 18, 'DigitalMusic', 5, '2023-10-15', 0.99),
    (170, 19, 'OnlineCourse', 5, '2023-10-20', 49.99),
    (171, 20, 'StreamingService', 5, '2023-10-25', 14.99),
    (172, 16, 'Software', 6, '2023-10-03', 39.99),
    (173, 17, 'EBook', 5, '2023-10-08', 7.99),
    (174, 18, 'DigitalMusic', 6, '2023-10-13', 1.99),
    (175, 19, 'OnlineCourse', 6, '2023-10-18', 59.99),
    (176, 20, 'StreamingService', 6, '2023-10-23', 19.99),
    (177, 16, 'Software', 4, '2023-10-01', 24.99),
    (178, 17, 'EBook', 3, '2023-10-06', 12.99),
    (179, 18, 'DigitalMusic', 4, '2023-10-11', 0.99),
    (180, 19, 'OnlineCourse', 3, '2023-10-16', 34.99),
    (181, 20, 'StreamingService', 3, '2023-10-21', 17.99),
    (182, 16, 'Software', 3, '2023-10-26', 19.99),
    (183, 17, 'EBook', 4, '2023-10-02', 10.99),
    (184, 18, 'DigitalMusic', 3, '2023-10-07', 1.99),
    (185, 19, 'OnlineCourse', 4, '2023-10-12', 44.99),
    (186, 20, 'StreamingService', 4, '2023-10-17', 22.99),
    (187, 16, 'Software', 3, '2023-10-14', 29.99),
    (188, 17, 'EBook', 4, '2023-10-19', 14.99),
    (189, 18, 'DigitalMusic', 3, '2023-10-24', 2.99),
    (190, 19, 'OnlineCourse', 4, '2023-10-29', 49.99),
    (191, 20, 'StreamingService', 4, '2023-10-05', 24.99),
    (192, 16, 'Software', 5, '2023-10-10', 34.99),
    (193, 17, 'EBook', 6, '2023-10-15', 18.99),
    (194, 18, 'DigitalMusic', 5, '2023-10-20', 3.99),
    (195, 19, 'OnlineCourse', 5, '2023-10-25', 54.99),
    (196, 20, 'StreamingService', 5, '2023-10-30', 29.99),
    (197, 21, 'Software', 6, '2023-10-04', 39.99),
    (198, 22, 'EBook', 5, '2023-10-09', 22.99),
    (199, 23, 'DigitalMusic', 6, '2023-10-14', 4.99),
    (200, 24, 'OnlineCourse', 6, '2023-10-19', 59.99),
    (201, 25, 'StreamingService', 6, '2023-10-24', 34.99),
    (202, 21, 'Software', 3, '2023-10-29', 44.99),
    (203, 22, 'EBook', 4, '2023-10-05', 26.99),
    (204, 23, 'DigitalMusic', 3, '2023-10-10', 5.99),
    (205, 24, 'OnlineCourse', 4, '2023-10-15', 64.99),
    (206, 25, 'StreamingService', 4, '2023-10-20', 39.99),
    (207, 16, 'Software', 1, '2023-11-02', 49.99),
    (208, 17, 'EBook', 2, '2023-11-07', 29.99),
    (209, 18, 'DigitalMusic', 1, '2023-11-12', 7.99),
    (210, 19, 'OnlineCourse', 1, '2023-11-15', 74.99),
    (211, 20, 'StreamingService', 2, '2023-11-11', 44.99),
    (212, 16, 'Software', 4, '2023-11-03', 54.99),
    (213, 17, 'EBook', 3, '2023-11-08', 32.99),
    (214, 18, 'DigitalMusic', 4, '2023-11-13', 8.99),
    (215, 19, 'OnlineCourse', 3, '2023-11-16', 79.99),
    (216, 20, 'StreamingService', 3, '2023-11-10', 49.99),
    (217, 16, 'Software', 1, '2023-11-02', 49.99),
    (218, 17, 'EBook', 2, '2023-11-07', 29.99),
    (219, 18, 'DigitalMusic', 1, '2023-11-12', 7.99),
    (220, 19, 'OnlineCourse', 1, '2023-11-15', 74.99),
    (221, 20, 'StreamingService', 2, '2023-11-11', 44.99),
    (223, 16, 'Software', 4, '2023-11-03', 54.99),
    (224, 17, 'EBook', 3, '2023-11-08', 32.99),
    (225, 18, 'DigitalMusic', 4, '2023-11-13', 8.99),
    (226, 19, 'OnlineCourse', 3, '2023-11-16', 79.99),
    (227, 20, 'StreamingService', 3, '2023-11-10', 49.99)