package consul

import (
	"log"
	"net"
	"os"
	"strconv"
	"strings"

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

	host := *GetIP()
	check := &api.AgentServiceCheck{}
	check.HTTP = "http://" + host + ":" + config.Port
	check.Interval = "30s"
	agentR := &api.AgentServiceRegistration{}
	agentR.ID = "carts-service"
	agentR.Name = "carts-service"
	agentR.Tags = []string{"carts", "cart", "cartElement", "service"}
	agentR.Address = host
	log.Println(agentR.Address)
	var err error
	agentR.Port, err = strconv.Atoi(config.Port)
	if err != nil {

		panic(err)
	}
	agentR.EnableTagOverride = true
	agent := c.client.Agent()
	agent.ServiceRegister(agentR)
}

//GetIP retrieves the ip on which the application is running.
func GetIP() *string {

	ifaces, err := net.Interfaces()
	if err != nil {
		panic(err)
	}
	addrs, err := ifaces[1].Addrs()
	log.Println(ifaces)
	if err != nil {
		panic(err)
	}
	ip := addrs[0].String()
	temp := strings.Split(ip, "/")
	ip = temp[0]
	//ip = "10.226.160.79"
	return &ip
}
