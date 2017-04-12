package models

//CartElement defines an element of a cart
type CartElement struct {
	ElementID int     //id of the element
	ProductID int     //id of the product contained in the element
	Quantity  int     //quantity of the product contained in the element
	UnitPrice float32 //Unit price of the product contained in the element
}

//Cart defines a cart.
type Cart struct {
	ID           int            //unique id of the cart
	CartElements []*CartElement //Slice of cartElements
	TimeStamp    string         //Time at which the cart has been last modified
	CustomerID   int            //id of the customer who's linked to the cart
	TotalPrice   float32        //the total price of the elements contained in the cart
}

//ElementPayload defines a payload sent to add a CartElment.
type ElementPayload struct {
	CustomerID  int          //The ID of the customer
	CartElement *CartElement //The CartElement to be added
}

// AddElement adds a new cartElment to the cart
func (c *Cart) AddElement(element *CartElement) {

	c.CartElements = append(c.CartElements, element)
}

// RemoveElement removes an element from the cart
func (c *Cart) RemoveElement(elementID int) {

	_, _, position := c.GetElement(elementID)
	if position < len(c.CartElements) && position > 0 {

		c.CartElements = append(c.CartElements[:position], c.CartElements[position+1:]...)

	} else if position > len(c.CartElements) && position > 0 {

		c.CartElements = c.CartElements[:position]
	}

}

// ModifyElement modifies an elment from the cart
func (c *Cart) ModifyElement(elementID int, element *CartElement) {

	_, _, position := c.GetElement(elementID)
	c.CartElements[position] = element

}

//GetElement retrieves the pointer of the CartElement situated at the given position
func (c *Cart) GetElement(elementID int) (*CartElement, bool, int) {

	found := false
	foundElement := new(CartElement)
	position := -1

	for i, element := range c.CartElements {

		if element.ElementID == elementID {

			found = true
			foundElement = element
			position = i
		}
	}

	return foundElement, found, position
}

//ComputeTotalPrice actualizes the TotalPrice of the Cart by computing the sum of the CartElements's Unit Prices
func (c *Cart) ComputeTotalPrice() {

	var res float32

	for _, p := range c.CartElements {

		res += p.UnitPrice
	}

	c.TotalPrice = res

}

//FindProduct returns the IDs of elements containing the given product
func (c *Cart) FindProduct(productID int) *[]int {

	var res []int

	if len(c.CartElements) > 0 {

		for _, element := range c.CartElements {

			if element.ProductID == productID {

				res = append(res, element.ElementID)
			}

		}
	}
	return &res
}
