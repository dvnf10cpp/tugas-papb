package server

import (
	"fmt"

	"github.com/devanfer02/graphics-guruji/internal/app/user/controller"
	"github.com/devanfer02/graphics-guruji/internal/infrastructure/env"
	"github.com/devanfer02/graphics-guruji/internal/middlewares"
	"github.com/devanfer02/graphics-guruji/internal/pkg/log"
	"github.com/gofiber/fiber/v3"
	"github.com/jmoiron/sqlx"
)

type Server interface {
	MountMiddlewares()
	MountRoutes()
	Start()
}



type httpServer struct {
	app *fiber.App
	mdlwr *middlewares.Middleware
	conn *sqlx.DB
}

func NewHttpServer(db *sqlx.DB) Server {
	app := fiber.New()
	mdlwr := middlewares.NewMiddlware(nil)

	return &httpServer{
		app: app,
		mdlwr: mdlwr,
		conn: db,
	}
}

func (s *httpServer) MountMiddlewares() {
	s.app.Use(s.mdlwr.Authentication())
}

func (s *httpServer) MountRoutes() {
	apiR := s.app.Group("/api")


	controller.InitUserController(apiR)
}

func (s *httpServer) Start() {
	if err := s.app.Listen(
		fmt.Sprintf("%s:%s", env.AppEnv.AppAddress, env.AppEnv.AppPort),
	); err != nil {
		log.Fatal(log.LogInfo{
			"err": err,
		}, "[HTTP SERVER][Start] failed to start server")
	}
}
