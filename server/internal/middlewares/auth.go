package middlewares

import "github.com/gofiber/fiber/v3"

func (m *Middleware) Authentication() fiber.Handler {
	return func(c fiber.Ctx) error {
		return c.Next()
	}
}	