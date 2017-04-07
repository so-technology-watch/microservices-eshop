package handlers

import (
	"os"
	"testing"

	"github.com/carts/services"
)

var client = &services.RedisClient{}

//TestMain is the main function is the main function called when the test are executed.
func TestMain(m *testing.M) {

	client.CreateClient()

	exitVal := m.Run()

	os.Exit(exitVal)
}
