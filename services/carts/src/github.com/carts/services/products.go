package services

import (
	"log"

	"github.com/carts/models"
)

//Product defines a product such as returned by the products service.
type Product struct {
	ID          int       `json:"id"`
	Reference   string    `json:"reference"`
	Designation string    `json:"designation"`
	Price       float32   `json:"price"`
	Supplier    *Supplier `json:"supplier"`
	SupplierID  int       `json:"idSupplier"`
	Image       string    `json:"image"`
	CategoryID  int       `json:"idCategory"`
}

//Supplier defines a supplier such as returned by the products service.
type Supplier struct {
	ID          int    `json:"id"`
	CompanyName string `json:"company"`
	Email       string `json:"email"`
	PhoneNumber string `json:"phone"`
}

//CheckProductExists verifies if the product of the given element does indeed exist.
func (g *GateWayClient) CheckProductExists(element *models.CartElement) bool {

	_, found := g.GetProduct(element.ProductID)

	return *found
}

//CheckProductPrice checks if the price og the product is the right one.
func (g *GateWayClient) CheckProductPrice(element *models.CartElement) bool {

	product, _ := g.GetProduct(element.ProductID)
	isTheSame := false
	log.Println("product:", *product, " ++++++++ element:", element)
	if product.Price == element.UnitPrice {

		isTheSame = true
		log.Println("product's price is correct")

	}

	return isTheSame
}

//CheckcartValidity checks if the content of a cart is valid.
func (g *GateWayClient) CheckcartValidity(cart *models.Cart) bool {

	valid := true

	for _, element := range cart.CartElements {

		if !g.CheckProductExists(element) && !g.CheckProductPrice(element) {

			valid = false
			log.Println("product isn't valid")
		}
	}

	return valid
}
