package models

//CartElement : Type defining an element of a cart
type CartElement struct {
	ElementID int     //id of the element
	ProductID int     //id of the product contained in the element
	Quantity  int     //quantity of the product contained in the element
	UnitPrice float32 //Unit price of the product contained in the element
}

//Cart : Type defining a cart.
type Cart struct {
	ID           int            //unique id of the cart
	CartElements []*CartElement //Slice of cartElements
	TimeStamp    string         //Time at which the cart has been last modified
	Customerid   int            //id of the customer who's linked to the cart
	TotalPrice   float32        //the total price of the elements contained in the cart
}

// AddElement : Method that adds a new cartElment to the cart
func (c *Cart) AddElement(element *CartElement) {

	c.CartElements = append(c.CartElements, element)
}

// RemoveElement : Method that removes an element from the cart
func (c *Cart) RemoveElement(i int) {

	c.CartElements = append(c.CartElements[:i], c.CartElements[i+1:]...)
}

// ModifyElement : Method that modifies an elment from the cart
func (c *Cart) ModifyElement(i int, element *CartElement) {

	c.CartElements[i] = element
}
