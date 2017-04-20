package services

import "github.com/go-redis/redis"

//RedisClient : wrapper for the redis client
type RedisClient struct {
	Client *redis.Client //Client : redis client

}

//CreateClient creates the redis client
func (c *RedisClient) CreateClient(address string, pwd string, db int) {

	c.Client = redis.NewClient(&redis.Options{Addr: address, Password: pwd, DB: db})
}
