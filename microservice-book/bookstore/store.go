package bookstore

type Store struct {
	Id      int    `json:"id" gorm:"primary_key"`
	Trade   string `json:"fantasia"`
	Company string `json:"social"`
	Cnpj    string `json:"cnpj"`
	Cep     string `json:"cep"`
}
