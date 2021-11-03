package entities

type Stock struct {

	Id       int    `json:"id" gorm:"primary_key"`
	Name     string `json:"nome"`
	IBSN     string `json:"ibsn"`
	Author   string `json:"autor"`
	Quantity string `json:"quantidade"`
	Price    string `json:"preco"`
	Store    int64  `json:"loja"`
}

type ErrorResponse struct {
	Code    int
	Message string
}
