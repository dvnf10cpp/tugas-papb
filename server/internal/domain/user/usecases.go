package user

import "context"

type UserRepository interface {
	FetchOneByParam(ctx context.Context, param *UserFetchParam) (User, error)
	InsertUser(ctx context.Context, user *UserRegister) error 
}

type UserService interface {
	FetchOneByParam(ctx context.Context, param *UserFetchParam) (UserResponse, error)
	RegisterUser(ctx context.Context, user *UserRegister) error 
	LoginUser(ctx context.Context, user *UserLogin) (string, error) 
}