DROP TABLE product IF EXISTS;

CREATE TABLE product
(ID   BIGINT, 
 product_category VARCHAR(100),
 name VARCHAR(100),
 short_description VARCHAR(255),
 long_description longtext,
 price decimal
 
);
insert into product(id, product_category,name,short_Description,long_Description,price)
values (101, 'Phones & Devices','Apple iphone SE','iPhone SE is the most powerful 4.7-inch iPhone ever.1 Featuring A13 Bionic, the fastest chip in a smartphone, for incredible performance in apps, games, and photography.',
'iPhone SE is the most powerful 4.7-inch iPhone ever.1 Featuring A13 Bionic, the fastest chip in a smartphone, for incredible performance in apps, games, and photography. Portrait mode for studio-quality portraits and six lighting effects. Next-generation Smart HDR for incredible detail across highlights and shadows. Cinematic-quality 4K video. And all the advanced features of iOS. With long battery life2 and water resistance,3 its so much of the iPhone you love, in a not so big size. So much to love, so little to hold with the iPhone SE and Verizon, Americas most awarded network.',
400);
insert into product(id, product_category,name,short_Description,long_Description,price)
values (102, 'Phones & Devices','Samsung Galaxy S20 Ultra 5G','Meet the Samsung Galaxy S20 Ultra 5G. Equipped with a first-of-its-kind 108 MP camera, this revolutionary smartphone gives you more power, speed and options for doing the things you love.',
'Meet the Samsung Galaxy S20 Ultra 5G. Equipped with a first-of-its-kind 108 MP camera, this revolutionary smartphone gives you more power, speed and options for doing the things you love. Capture every moment with unparalleled camera features like Ultra Bright Night, 100x Space Zoom and Single Take AI.',
1399);
insert into product(id, product_category,name,short_Description,long_Description,price)
values (103, 'Phones & Devices','Motorola razr','Experience the iconic flip phone, totally re-invented.',
'Experience the iconic flip phone, totally re-invented. The Motorola razr fuses the familiar, pocket-ready flip design with the style and intelligence of the modern smartphone. Shattering the status quo, the ultra-compact, water repellent1 razr opens to a full-sized touchscreen. ',
1499);
insert into product(id, product_category,name,short_Description,long_Description,price)
values (201, 'Tablets','Apple 11-inch iPad® Pro','This is the new iPad Pro. The 11-inch iPad Pro features an immersive, edge-to-edge Liquid Retina display',
'This is the new iPad Pro. The 11-inch iPad Pro features an immersive, edge-to-edge Liquid Retina display.¹ The new pro cameras, Wide and new Ultra Wide, combined with the all-new LiDAR Scanner enable entirely new experiences in the next generation of augmented reality (AR) apps. A12Z Bionic chip for powering essential apps and graphics-intensive games. Support for the new Magic Keyboard with trackpad2 and Apple Pencil.',
949);
insert into product(id, product_category,name,short_Description,long_Description,price)
values (202, 'Tablets','Apple iPad® 10.2','The new iPad combines the power and capability of a computer with the ease of use and versatility youd never expect from one.',
'The new iPad combines the power and capability of a computer with the ease of use and versatility youd never expect from one. And now its even more versatile, with a larger 10.2-inch Retina display, support for the full-size Smart Keyboard and the amazing new capabilities of iPadOS. Its unbelievably fun. And unmistakably iPad.',
949);
insert into product(id, product_category,name,short_Description,long_Description,price)
values (203, 'Tablets','Samsung Galaxy Tab S5e','The ultra-slim Samsung Galaxy Tab S5e is the perfect tablet for your on-the-go life. Its thin metal design makes it easy to hold without weighing you down.',
'The ultra-slim Samsung Galaxy Tab S5e is the perfect tablet for your on-the-go life. Its thin metal design makes it easy to hold without weighing you down. Slip it into your bag on your way to class or snap on a Samsung keyboard when its time to get down to business',
479);
insert into product(id, product_category,name,short_Description,long_Description,price)
values (301, 'Smartwatches','Verizon GizmoWatch 2','GizmoWatch 2 is a kid-friendly smartwatch designed with your childs safety in mind. ',
'GizmoWatch 2 is a kid-friendly smartwatch designed with your childs safety in mind. GizmoWatch 2 is chock-full of features parents will love, like a GPS locator.',
99);
insert into product(id, product_category,name,short_Description,long_Description,price)
values (302, 'Smartwatches','Apple® Watch Series 3 Nike+ Aluminum 38mm Case with Sport Band','Smart activity coaching. An enhanced Heart Rate app. Your favorite playlists right on your wrist',
'Smart activity coaching. An enhanced Heart Rate app. Your favorite playlists right on your wrist',
379);
insert into product(id, product_category,name,short_Description,long_Description,price)
values (303, 'Smartwatches','Samsung Galaxy Watch 42mm','Get the tools you need to live a smarter, stronger life with the Samsung Galaxy Watch.',
'Get the tools you need to live a smarter, stronger life with the Samsung Galaxy Watch.',
299);