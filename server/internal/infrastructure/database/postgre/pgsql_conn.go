package postgre_db

import (
	"fmt"

	"github.com/jmoiron/sqlx"
	_ "github.com/lib/pq"

	"github.com/devanfer02/graphics-guruji/internal/infrastructure/env"
	"github.com/devanfer02/graphics-guruji/internal/pkg/log"
)

func NewPgsqlConn() *sqlx.DB {
	dsn := fmt.Sprintf(
		"host=%s port=%s user=%s password=%s dbname=%s sslmode=disable",
		env.AppEnv.DBHost,
		env.AppEnv.DBPort,
		env.AppEnv.DBUsername,
		env.AppEnv.DBPassword,
		env.AppEnv.DBName,
	)

	db, err := sqlx.Connect("postgres", dsn)

	if err != nil {
		log.Fatal(log.LogInfo{
			"err": err,
		}, "[PGSQL CONN][NewPgsqlConn] failed to connect to database")
	}

	return db 
	 
}