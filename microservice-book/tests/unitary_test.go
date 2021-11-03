package tests

import (
	"bytes"
	"encoding/json"
	"hamilton/ms-stockRegister/controllers"
	"hamilton/ms-stockRegister/entities"
	"net/http"
	"net/http/httptest"
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestGetAllBookSuccess(t *testing.T) {

	expected := []entities.Stock([]entities.Stock{
		{
			Id: 1, Name: "Clean Code",
			IBSN:     "97801323",
			Author:   "Robert C. Martin",
			Quantity: "1000",
			Price:    "39,90",
			Store:    1},

		{
			Id: 2, Name: "Codificando limpo",
			IBSN:     "97801323",
			Author:   "Robert C. Martin",
			Quantity: "1100",
			Price:    "39,90",
			Store:    1}})

	request, err := http.NewRequest("GET", "/stock", nil)
	if err != nil {
		t.Errorf("this is the error: %v\n", err)
	}

	rr := httptest.NewRecorder()
	handler := http.HandlerFunc(controllers.GetAll)
	handler.ServeHTTP(rr, request)

	var book []entities.Stock
	err = json.Unmarshal(rr.Body.Bytes(), &book)
	if err != nil {
		t.Errorf("this isd the error: %v", err)
	}

	assert.Nil(t, err)
	assert.NotNil(t, book)
	assert.Equal(t, len(book), 2)
	assert.EqualValues(t, expected[0].Id, book[0].Id, 1)
	assert.EqualValues(t, expected[0].Name, book[0].Name, "Clean Code")
	assert.EqualValues(t, expected[0].IBSN, book[0].IBSN, "97801323")
	assert.EqualValues(t, expected[0].Author, book[0].Author, "Robert C. Martin")
	assert.EqualValues(t, expected[0].Quantity, book[0].Quantity, "1000")
	assert.EqualValues(t, expected[0].Price, book[0].Price, "39,90")
	assert.EqualValues(t, expected[0].Store, book[0].Store, "Amazon")
	assert.EqualValues(t, expected[0].Id, book[0].Id, 2)
	assert.EqualValues(t, expected[0].Name, book[0].Name, "Codificando limpo")
	assert.EqualValues(t, expected[0].IBSN, book[0].IBSN, "97801323")
	assert.EqualValues(t, expected[0].Author, book[0].Author, "Robert C. Martin")
	assert.EqualValues(t, expected[0].Quantity, book[0].Quantity, "1100")
	assert.EqualValues(t, expected[0].Price, book[0].Price, "39,90")
	assert.EqualValues(t, expected[0].Store, book[0].Store, "Americanas")
}

func TestUpdateBook(t *testing.T) {

	updateBook := &entities.Stock{Id: 5, Name: "Guaia da Plantas do Cerrado",
		IBSN:     "97801323",
		Author:   "Dr. Rubens Coelho",
		Quantity: "300",
		Price:    "45,90",
		Store:    1}

	id := "1"
	jsonBook, _ := json.Marshal(updateBook)
	request, err := http.NewRequest("POST", "/update"+id, bytes.NewBuffer(jsonBook))
	if err != nil {
		t.Errorf("this is the error: %v\n", err)
	}
	response := httptest.NewRecorder()
	handler := http.HandlerFunc(controllers.GetAll)
	handler.ServeHTTP(response, request)

	assert.Equal(t, http.StatusOK, response.Code)
	assert.NotNil(t, updateBook)
	assert.EqualValues(t, "Guaia da Plantas do Cerrado", updateBook.Name)
	assert.EqualValues(t, "97801323", updateBook.IBSN)
	assert.EqualValues(t, "Dr. Rubens Coelho", updateBook.Author)
	assert.EqualValues(t, "300", updateBook.Quantity)
	assert.EqualValues(t, "45,90", updateBook.Price)
	assert.EqualValues(t, "Submarino", updateBook.Store)
}

func TestInsertBook(t *testing.T) {

	newBook := &entities.Stock{Id: 3, Name: "Guaia da Plantas do Cerrado",
		IBSN:     "97801323",
		Author:   "Dr. Rubens Coelho",
		Quantity: "150",
		Price:    "45,90",
		Store:    1}

	jsonBook, _ := json.Marshal(newBook)
	request, err := http.NewRequest("POST", "/insert", bytes.NewBuffer(jsonBook))
	if err != nil {
		t.Errorf("this is the error: %v\n", err)
	}

	response := httptest.NewRecorder()
	handler := http.HandlerFunc(controllers.GetAll)
	handler.ServeHTTP(response, request)

	assert.Equal(t, http.StatusOK, response.Code)
	assert.NotNil(t, newBook)
}

func TestDeleteBookId(t *testing.T) {

	id := "1"
	request, err := http.NewRequest(http.MethodDelete, "/delete"+id, nil)
	if err != nil {
		t.Errorf("this is the error: %v\n", err)
	}

	response := httptest.NewRecorder()
	handler := http.HandlerFunc(controllers.Delete)
	handler.ServeHTTP(response, request)

	var resp = make(map[string]string)
	theErr := json.Unmarshal(response.Body.Bytes(), &response)
	if theErr != nil {
		t.Errorf("this is the error: %v\n", err)
	}
	assert.EqualValues(t, http.StatusOK, response.Code)
	assert.EqualValues(t, resp["status"], "deleted")

}
