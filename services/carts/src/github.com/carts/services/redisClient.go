package services

import "github.com/go-redis/redis"

const address = "10.226.159.191:6379"
const pwd = ""
const db = 0

//Client : wrapper for the redis client
type RedisClient struct {
	Client *redis.Client //Client : redis client
}

//CreateClient creates the redis client
func (c *RedisClient) CreateClient() {

	c.Client = redis.NewClient(&redis.Options{Addr: address, Password: pwd, DB: db})
}
