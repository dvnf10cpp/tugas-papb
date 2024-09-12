package user

type User struct {
	ID       int    `json:"id" db:"id"`
	Fullname string `json:"fullname" db:"fullname"`
	Email    string `json:"email" db:"email"`
	Password string `json:"password" db:"password"`
}
