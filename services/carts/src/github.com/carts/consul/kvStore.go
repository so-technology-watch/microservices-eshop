package consul

import (
	"encoding/json"
	"log"

	"github.com/carts/config"
)

//RetrieveConfig retrieves the configuration of the service from the conul  key value store.
func (c *Client) RetrieveConfig() *config.Config {

	kv := c.client.KV()
	configPair, _, err := kv.Get("config/services/carts", nil)
	if err != nil {
		panic(err)
	}
	configJSON := configPair.Value
	log.Println(string(configPair.Value))
	if err != nil {
		panic(err)
	}


	config := &config.Config{}
	json.Unmarshal(configJSON, config)
	log.Println(config)
	return config
}
