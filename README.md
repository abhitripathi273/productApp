# productApp
Endpoints
GET /shop/products/{productId}
POSt /shop/products

Sample Request and Response Details
Request

curl -X GET \
  http://localhost:8086/shop/products/1 \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 53384288-1f0c-346e-97b4-e9a37201631f'

Response

{
    "id": 1,
    "name": "laptops",
    "description": "laptops",
    "price": 2000,
    "quantity": 500
}

Failure

{
    "id": null,
    "name": null,
    "description": null,
    "price": null,
    "quantity": null
}

Request

curl -X POST \
  http://localhost:8086/shop/products \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 2a9c2716-5b09-67e5-2cf4-38907c9649a2' \
  -d '{
	"name":"laptops",
	"description":"laptops",
	"price":"2000",
	"quantity":"500"
}'

Response

SUCCESS

Failure

FAILED



