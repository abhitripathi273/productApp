# productApp
Endpoints
GET /shop/products/{productId}
POST /shop/products
GET /shop/products
GET /shop/placeOrder/product/{productId}

Sample Request and Response Details
Request

curl -X GET \
  http://localhost:8086/shop/products/1 \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 53384288-1f0c-346e-97b4-e9a37201631f'

Response

{
	"id": 101,
	"productCategory": "Smartwatches",
	"name": "Verizon GizmoWatch 2",
	"shortDescription": "GizmoWatch 2 is a kid-friendly smartwatch designed with your childs safety in mind.",
	"longDescription": "GizmoWatch 2 is a kid-friendly smartwatch designed with your childs safety in mind.",
	"price": 99
}

Failure

{
    	"id": null
	"productCategory": null,
	"name": null,
	"shortDescription": null,
	"longDescription": null,
	"price": null
}

Request

curl -X POST \
  http://localhost:8086/shop/products \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 2a9c2716-5b09-67e5-2cf4-38907c9649a2' \
  -d '{
	"productCategory": "Smartwatches",
	"name": "Verizon GizmoWatch 2",
	"shortDescription": "GizmoWatch 2 is a kid-friendly smartwatch designed with your childs safety in mind.",
	"longDescription": "GizmoWatch 2 is a kid-friendly smartwatch designed with your childs safety in mind.",
	"price": 99
}'

Response

SUCCESS

Failure

FAILED

Request

curl -X GET \
  http://localhost:8086/shop/products \

Response

[
    {
        "id": 102,
        "productCategory": "Phones",
        "name": "Samsung Galaxy S20 Ultra 5G",
        "shortDescription": "Meet the Samsung Galaxy S20 Ultra 5G. Equipped with a first-of-its-kind 108 MP camera, this revolutionary smartphone gives you more power, speed and options for doing the things you love.",
        "longDescription": "Meet the Samsung Galaxy S20 Ultra 5G. Equipped with a first-of-its-kind 108 MP camera, this revolutionary smartphone gives you more power, speed and options for doing the things you love. Capture every moment with unparalleled camera features like Ultra Bright Night, 100x Space Zoom and Single Take AI.",
        "price": 1399
    },
    {
        "id": 103,
        "productCategory": "Phones",
        "name": "Motorola razr",
        "shortDescription": "Experience the iconic flip phone, totally re-invented.",
        "longDescription": "Experience the iconic flip phone, totally re-invented. The Motorola razr fuses the familiar, pocket-ready flip design with the style and intelligence of the modern smartphone. Shattering the status quo, the ultra-compact, water repellent1 razr opens to a full-sized touchscreen. ",
        "price": 1499
    }
    ]
    
    
Request

curl -X GET \
  http://localhost:8086/shop/placeOrder/product/102 \
 
 Response
 
  {
        "id": 102,
        "productCategory": "Phones",
        "name": "Samsung Galaxy S20 Ultra 5G",
        "shortDescription": "Meet the Samsung Galaxy S20 Ultra 5G. Equipped with a first-of-its-kind 108 MP camera, this revolutionary smartphone gives you more power, speed and options for doing the things you love.",
        "longDescription": "Meet the Samsung Galaxy S20 Ultra 5G. Equipped with a first-of-its-kind 108 MP camera, this revolutionary smartphone gives you more power, speed and options for doing the things you love. Capture every moment with unparalleled camera features like Ultra Bright Night, 100x Space Zoom and Single Take AI.",
        "price": 1399
    }

