package env

import (
	"github.com/devanfer02/graphics-guruji/internal/pkg/log"
	"github.com/spf13/viper"
)

type env struct {
	AppAddress string `mapstructure:"APP_ADDRESS"`
	AppPort    string `mapstructure:"APP_PORT"`
	DBHost     string `mapstructure:"DB_HOST"`
	DBUsername string `mapstructure:"DB_USERNAME"`
	DBPassword string `mapstructure:"DB_PASSWORD"`
	DBName     string `mapstructure:"DB_NAME"`
	DBPort     string `mapstructure:"DB_PORT"`
}

var AppEnv = getEnv()

func getEnv() *env {
	env := &env{}

	viper.SetConfigFile(".env")

	if err := viper.ReadInConfig(); err != nil {
		log.Fatal(log.LogInfo{
			"err": err,
		}, "[ENV][getEnv] failed to read config file")
	}

	if err := viper.Unmarshal(env); err != nil {
		log.Fatal(log.LogInfo{
			"err": err,
		}, "[ENV][getEnv] failed to unmarshal struct")
	}

	return env
}
