package main

import (
	"hamilton/ms-stockRegister/config"
	"hamilton/ms-stockRegister/routes"
	"log"
	"net/http"

	"github.com/gorilla/mux"
)

// @title Book stock API
// @version 1.0
// @description this is an inventory control microservice from a bookstores
// @termsOfService http://swagger.io/terms/
// @contact.name API Support
// @license.name Apache 2.0
// @license.url http://www.apache.org/licenses/LICENSE-2.0.html
// @host localhost:8001
// @BasePath /
func main() {

	log.Println("Server started on: http://estoque:8001")
	config.CreateDatabase()
	route := mux.NewRouter()
	routes.LoadingRoutes(route)
	log.Fatal(http.ListenAndServe(":8001", route))
}
