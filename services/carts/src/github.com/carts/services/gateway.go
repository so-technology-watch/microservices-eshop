package services

import (
	"encoding/json"
	"io/ioutil"
	"log"
	"net/http"
	"strconv"

	"github.com/carts/config"
)

//GateWayClient is a client for the api gateway.
type GateWayClient struct {
	Host       string
	Port       string
	HTTPClient *http.Client
}

//CreateClient  creates the client that will communicate with the gateway.
func (g *GateWayClient) CreateClient(config *config.Config) {

	g.Host = config.GateWayConf.Host
	g.Port = config.GateWayConf.Port
	g.HTTPClient = &http.Client{}

}

//GetProduct retrieves a product from the products service by it's ID.
func (g *GateWayClient) GetProduct(ID int) (*Product, *bool) {

	found := false
	url := g.Host + ":" + g.Port + "/api/v1/products/" + strconv.Itoa(ID)
	request, err := http.NewRequest("GET", url, nil)
	failOnError(err)
	response, err := g.HTTPClient.Do(request)
	failOnError(err)
	productJSON, err := ioutil.ReadAll(response.Body)
	log.Println(string(productJSON))
	var product *Product

	if len(productJSON) > 0 {
		failOnError(err)
		product = &Product{}
		json.Unmarshal(productJSON, product)
		found = true
	}
	log.Println(found)
	return product, &found
}
