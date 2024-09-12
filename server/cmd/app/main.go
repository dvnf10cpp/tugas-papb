package main

import (
	postgre_db "github.com/devanfer02/graphics-guruji/internal/infrastructure/database/postgre"
	"github.com/devanfer02/graphics-guruji/internal/infrastructure/server"
)

func main() {
	db := postgre_db.NewPgsqlConn()
	server := server.NewHttpServer(db)

	server.MountMiddlewares()
	server.MountRoutes()
	server.Start()
}