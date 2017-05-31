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
	consulConfig.Address = os.Getenv("CONSUL_CLIENT")

	c.client, err = api.NewClient(consulConfig)
	if err != nil {
		panic(err)
	}
}

//Register registers the new service to consul.
func (c *Client) Register(config *config.Config) {

	host := *GetIP(&config.Interface)
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
func GetIP(inter *string) *string {

	log.Println("salut ", inter)

	if *inter != "" {
		ifaces, err := net.Interfaces()
		if err != nil {
			panic(err)
		}
		index := findIface(ifaces, inter)
		log.Println(index)
		addrs, err := ifaces[index].Addrs()
		log.Println(ifaces)
		if err != nil {
			panic(err)
		}
		index = findIPV4(addrs)
		ip := addrs[index].String()
		temp := strings.Split(ip, "/")
		ip = temp[0]
		return &ip
	} else {

		ip := "0.0.0.0"
		log.Println(ip)
		return &ip
	}
}


func findIface(interfaces []net.Interface, name *string) int{

	for i, iface := range interfaces {
		if iface.Name == *name {
			log.Println(iface.Name)
			return i
		}
	}
return 0
}

func findIPV4(adresses []net.Addr) int {
	for i, adresse := range adresses {
		if strings.Contains(adresse.String(), "."){
			return i
		}
	}
	return 0
}
