package config

//Config contains the configaration of the service.
type Config struct {
	Host         string          `json:"host"`
	Port         string          `json:"port"`
	RabbitMQConf *RabbitMQConfig `json:"rabbitmq"`
	RedisConf    *RedisConfig    `json:"redis"`
	GateWayConf  *GateWayConfig  `json:"gateway"`
}

//RabbitMQConfig contains the configuration concerning RabbitMQ.
type RabbitMQConfig struct {
	Host       string `json:"host"`
	RoutingKey string `json:"key"`
}

//RedisConfig contains the configuration concerning Redis.
type RedisConfig struct {
	Address string `json:"address"`
	PWD     string `json:"pwd"`
	DB      int    `json:"db"`
}

//GateWayConfig contains the configuration concerning the Gateway.
type GateWayConfig struct {
	Host string `json:"host"`
	Port string `json:"port"`
}
