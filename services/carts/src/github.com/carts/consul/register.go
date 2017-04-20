package consul

import (
	"os"
	"strconv"

	"github.com/carts/config"
	"github.com/hashicorp/consul/api"
)

//Client wraps the consul client api type.
type Client struct {
	client *api.Client
}

//CreateClient creates a new consul api client.
func (c *Client) CreateClient() {
	var err error
	consulConfig := api.DefaultConfig()
	consulConfig.Address = os.Getenv("consul")

	c.client, err = api.NewClient(consulConfig)
	if err != nil {
		panic(err)
	}
}

//Register registers the new service to consul.
func (c *Client) Register(config *config.Config) {

	check := &api.AgentServiceCheck{}
	check.HTTP = "http://" + config.Host + ":" + config.Port
	check.Interval = "30s"
	agentR := &api.AgentServiceRegistration{}
	agentR.ID = "carts"
	agentR.Name = "Carts service"
	agentR.Tags = []string{"carts", "cart", "cartElement", "service"}
	var err error
	agentR.Port, err = strconv.Atoi(config.Port)
	if err != nil {

		panic(err)
	}
	agentR.Address = config.Host
	agentR.EnableTagOverride = true
	agent := c.client.Agent()
	agent.ServiceRegister(agentR)
}
