package controller

import (
	"github.com/gofiber/fiber/v3"

	"github.com/devanfer02/graphics-guruji/internal/domain/user"
)

type userController struct {
	userSvc user.UserService
}

func InitUserController(router fiber.Router) {
	userCtr := &userController{}
	userR := router.Group("/users")
	
	userR.Get(
		"/register",
		userCtr.Register(),
	)
}

func (c *userController) Register() fiber.Handler {
	return  func(c fiber.Ctx) error {
		return c.Status(200).JSON(map[string]interface{}{
			"message": "hello from fiber!",
		})
	}
}