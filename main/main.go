package main

import (
	"encoding/json"
	"fmt"
	"os"

	models "github.com/carts/models/carts"
)

func main() {

	element := &models.CartElement{ElementID: 1, ProductID: 1, Quantity: 1, UnitPrice: 1}
	cart := &models.Cart{CartElements: []*models.CartElement{element}}
	j, err := json.Marshal(cart)
	failOnError(err)
	var ca = models.Cart{}
	err = json.Unmarshal(j, &ca)
	failOnError(err)
	os.Stdout.Write(j)
	fmt.Println(ca.CartElements[0].ProductID)
}

func failOnError(err error) {
	if err != nil {
		panic("ah sos")
	}
}
