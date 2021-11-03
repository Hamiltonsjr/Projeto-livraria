package routes

import (
	"hamilton/ms-stockRegister/controllers"
	_ "hamilton/ms-stockRegister/docs"

	"github.com/gorilla/mux"
	httpSwagger "github.com/swaggo/http-swagger"
)

func LoadingRoutes(route *mux.Router) {

	route.HandleFunc("/estoque", controllers.GetAll).Methods("GET")
	route.HandleFunc("/estoque/insert", controllers.Insert).Methods("POST")
	route.HandleFunc("/estoque/update", controllers.Update).Methods("PUT")
	route.HandleFunc("/estoque/delete/{id}", controllers.Delete).Methods("DELETE")
	route.HandleFunc("/estoque/{id}", controllers.GetId).Methods("GET")
	route.HandleFunc("/estoque/ibsn/{ibsn}/qtd/{quantidade}", controllers.GetParameters).Methods("GET")
	route.PathPrefix("/estoque/swagger").Handler(httpSwagger.WrapHandler)
}
