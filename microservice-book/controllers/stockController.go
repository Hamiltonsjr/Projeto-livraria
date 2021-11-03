package controllers

import (
	"encoding/json"
	"hamilton/ms-stockRegister/entities"
	"hamilton/ms-stockRegister/services"
	"net/http"

	_ "github.com/go-sql-driver/mysql"
	"github.com/gorilla/mux"
)

// GetAll
// @Summary Show a Book in database
// @Description get string all book in database
// @Accept  json
// @Produce  json
// @Success 200 {array} entities.Stock
// @Failure 400,404 {object} entities.ErrorResponse
// @Failure 500 {object} entities.ErrorResponse
// @Failure default {object} entities.ErrorResponse
// @Router /estoque [get]
func GetAll(w http.ResponseWriter, r *http.Request) {
	var httpError = entities.ErrorResponse{
		Code: http.StatusInternalServerError, Message: "NO BOOK FOUND IN THE DATABASE",
	}
	jsonResponse := services.GetAllBook()
	if jsonResponse == nil {
		ReturnErrorResponse(w, r, httpError)
	} else {
		w.Header().Set("Content-Type", "application/json")
		json.NewEncoder(w).Encode(jsonResponse)
	}
}

// Insert
// @Summary Show a Book in database
// @Description get string all book in database
// @Accept  json
// @Produce  json
// @Success 200 {array} entities.Stock
// @Failure 400,404 {object} entities.ErrorResponse
// @Failure 500 {object} entities.ErrorResponse
// @Failure default {object} entities.ErrorResponse
// @Router /estoque/insert [post]
func Insert(w http.ResponseWriter, r *http.Request) {
	var httpError = entities.ErrorResponse{
		Code: http.StatusInternalServerError, Message: "NO INSERT BOOK IN THE DATABASE",
	}
	var newBook entities.Stock
	decoder := json.NewDecoder(r.Body)
	err := decoder.Decode(&newBook)
	if err != nil {
		ReturnErrorResponse(w, r, httpError)
	} else {
		httpError.Code = http.StatusBadRequest
		if newBook.Name == "" {
			httpError.Message = "Name cannot be null"
			ReturnErrorResponse(w, r, httpError)
		} else if newBook.IBSN == "" {
			httpError.Message = "IBSN cannot be null"
			ReturnErrorResponse(w, r, httpError)
		} else if newBook.Author == "" {
			httpError.Message = "Author cannot be null"
			ReturnErrorResponse(w, r, httpError)
		} else if newBook.Quantity == "" {
			httpError.Message = "Quantity cannot be null"
			ReturnErrorResponse(w, r, httpError)
		} else if newBook.Price == "" {
			httpError.Message = "Price cannot be null"
			ReturnErrorResponse(w, r, httpError)
		} else if newBook.Store == 0 {
			httpError.Message = "Store cannot be null"
			ReturnErrorResponse(w, r, httpError)
		} else {
			isInsert, err := services.InsertBook(newBook)
			if err != nil {
				httpError.Code = http.StatusBadRequest
				httpError.Message = "Livrária não existe"
			}

			if isInsert {
				jsonResponse := services.GetAllBook()
				if jsonResponse == nil {
					ReturnErrorResponse(w, r, httpError)
				} else {
					w.Header().Set("Content-Type", "application/json")
					json.NewEncoder(w).Encode(jsonResponse)
				}
			} else {
				ReturnErrorResponse(w, r, httpError)
			}
		}
	}
}

// Update
// @Summary Show a Book in database
// @Description get string all book in database
// @Accept  json
// @Produce  json
// @Success 200 {array} entities.Stock
// @Failure 400,404 {object} entities.ErrorResponse
// @Failure 500 {object} entities.ErrorResponse
// @Failure default {object} entities.ErrorResponse
// @Router /estoque/update [put]
func Update(w http.ResponseWriter, r *http.Request) {
	var httpError = entities.ErrorResponse{
		Code: http.StatusInternalServerError, Message: "NO UPDATE BOOK IN THE DATABASE",
	}
	var bookDetails entities.Stock
	decoder := json.NewDecoder(r.Body)
	err := decoder.Decode(&bookDetails)
	defer r.Body.Close()
	if err != nil {
		ReturnErrorResponse(w, r, httpError)
	} else {
		httpError.Code = http.StatusBadRequest
		if bookDetails.Name == "" {
			httpError.Message = "Name cannot be null"
			ReturnErrorResponse(w, r, httpError)
		} else if bookDetails.IBSN == "" {
			httpError.Message = "IBSN cannot be null"
			ReturnErrorResponse(w, r, httpError)
		} else if bookDetails.Author == "" {
			httpError.Message = "Author cannot be null"
			ReturnErrorResponse(w, r, httpError)
		} else if bookDetails.Quantity == "" {
			httpError.Message = "Quantity cannot be null"
			ReturnErrorResponse(w, r, httpError)
		} else if bookDetails.Price == "" {
			httpError.Message = "Price cannot be null"
			ReturnErrorResponse(w, r, httpError)
		} else if bookDetails.Store == 0 {
			httpError.Message = "Store cannot be null"
			ReturnErrorResponse(w, r, httpError)
		} else {
			isInsert, err := services.UpdateBook(bookDetails)
			if err != nil {
				httpError.Code = http.StatusBadRequest
				httpError.Message = "Livrária não existe"

			}
			if isInsert {
				jsonResponse := services.GetAllBook()
				if jsonResponse == nil {
					ReturnErrorResponse(w, r, httpError)
				} else {
					w.Header().Set("Content-Type", "application/json")
					json.NewEncoder(w).Encode(jsonResponse)
				}
			} else {
				ReturnErrorResponse(w, r, httpError)
			}
		}
	}
}

// Delete
// @Summary Show a Book in database
// @Description get string all book in database
// @Accept  json
// @Produce  json
// @Success 200 {array} entities.Stock
// @Failure 400,404 {object} entities.ErrorResponse
// @Failure 500 {object} entities.ErrorResponse
// @Failure default {object} entities.ErrorResponse
// @Router /estoque/delete/{id} [delete]
func Delete(w http.ResponseWriter, r *http.Request) {
	var httpError = entities.ErrorResponse{
		Code: http.StatusInternalServerError, Message: "NO DELETE BOOK IN THE DATABASE",
	}

	bookID := mux.Vars(r)["id"]
	if bookID == "" {
		httpError.Message = "Book id can be empty"
		ReturnErrorResponse(w, r, httpError)
	} else {
		isDeleted := services.DeleteBook(bookID)
		if isDeleted {

			jsonResponse := services.GetAllBook()
			if jsonResponse == nil {
				ReturnErrorResponse(w, r, httpError)
			} else {
				w.Header().Set("Content-Type", "application/json")
				json.NewEncoder(w).Encode(jsonResponse)
			}
		} else {
			ReturnErrorResponse(w, r, httpError)
		}
	}
}

// GetId
// @Summary Show a Book in database
// @Description get string by ID
// @ID get-string-by-int
// @Accept  json
// @Produce  json
// @Param id path int true "Account ID"
// @Success 200 {array} entities.Stock
// @Failure 400,404 {object} entities.ErrorResponse
// @Failure 500 {object} entities.ErrorResponse
// @Failure default {object} entities.ErrorResponse
// @Router /estoque/{id} [get]
func GetId(w http.ResponseWriter, r *http.Request) {
	var httpError = entities.ErrorResponse{
		Code: http.StatusInternalServerError, Message: "NO BOOK ID IN THE DATABASE",
	}
	bookID := mux.Vars(r)["id"]

	if bookID == "" {
		httpError.Message = "Book id can be empty"
		ReturnErrorResponse(w, r, httpError)

	} else {
		getBook := services.GetBookById(bookID)
		w.Header().Set("Content-Type", "application/json")
		json.NewEncoder(w).Encode(getBook)
	}

}

func GetParameters(w http.ResponseWriter, r *http.Request) {
	var httpError = entities.ErrorResponse{
		Code: http.StatusInternalServerError, Message: "NO BOOK ID IN THE DATABASE",
	}

	ibsn := mux.Vars(r)["ibsn"]
	qtd := mux.Vars(r)["quantidade"]

	if ibsn == "" && qtd == "" {
		httpError.Message = "IBSN ou Quantidade não pode ser nulo"
		ReturnErrorResponse(w, r, httpError)

	} else {
		getBook := services.GetParameters(ibsn, qtd)
		w.Header().Set("Content-Type", "application/json")
		json.NewEncoder(w).Encode(getBook)
	}
}

func ReturnErrorResponse(response http.ResponseWriter, request *http.Request, errorMessage entities.ErrorResponse) {
	httpResponse := &entities.ErrorResponse{Code: errorMessage.Code, Message: errorMessage.Message}
	jsonResponse, err := json.Marshal(httpResponse)
	if err != nil {
		panic(err)
	}
	response.Header().Set("Content-Type", "application/json")
	response.WriteHeader(errorMessage.Code)
	response.Write(jsonResponse)
}
