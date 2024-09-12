package middlewares

import "github.com/devanfer02/graphics-guruji/internal/domain/user"

type Middleware struct {
	userRepo user.UserRepository
}

func NewMiddlware(
	userRepo user.UserRepository,
) *Middleware  {
	return &Middleware{
		userRepo: userRepo,
	}
}