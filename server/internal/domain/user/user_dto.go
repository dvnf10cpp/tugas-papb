package user

type UserFetchParam struct {
	ID    int
	Email string
}

type UserRegister struct {
	Fullname string
	Email    string
	Password string
}

type UserLogin struct {
	Email    string
	Password string
}

type UserResponse struct {
	ID       int    `json:"id"`
	Fullname string `json:"fullname"`
	Email    string `json:"email"`
}
