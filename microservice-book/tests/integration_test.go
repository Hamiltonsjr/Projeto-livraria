package tests

import (
	"bytes"
	"encoding/json"
	"fmt"
	"strconv"

	"hamilton/ms-stockRegister/config"
	"hamilton/ms-stockRegister/controllers"
	"hamilton/ms-stockRegister/entities"
	"log"
	"net/http"
	"net/http/httptest"
	"testing"
	"github.com/stretchr/testify/assert"
)

func TestGetAllBooks(t *testing.T){

	
	request, err := http.NewRequest("GET", "/stock", nil)
	if err != nil{
		t.Errorf("this is the error :%v", err)
	}

	rr := httptest.NewRecorder()
	handler := http.HandlerFunc(controllers.GetAll)
	handler.ServeHTTP(rr, request)

	var msg []entities.Stock

	err = json.Unmarshal(rr.Body.Bytes(), &msg)
	if err != nil {
		log.Fatalf("Cannot convert to json: %v\n", err)
	}
	assert.Equal(t, rr.Code, http.StatusOK)
	assert.Equal(t, len(msg), 2)	
}

func TestCreateBook(t *testing.T){
	db := config.Connection()
	samples := []struct{
		inputJson string
		statusCode int
		name string
		ibsn string
		author string
		quantity string 
		price string
		store string
		errMessage string 
	}{
		{
			inputJson: `{"nome":"Clean Code","ibsn": "97801323","autor": "Robert C. Martin","quantidade": "1000","preco": "39,90",": "Amazon"}`,
			statusCode: 201,
			name:"Clean Code",
			ibsn:"97801323",
			author:"Robert C. Martin",
			quantity:"1000",
			price:"39,90",
			store:"Amazon",
			errMessage: "",
		},
		{	
			inputJson: `{"nome": "Clean Code","ibsn": "97801323","autor": "Robert C. Martin","quantidade": "1000","preco": "39,90","loja": "Amazon"}`,
			statusCode: 500,
			errMessage: "Name already taken",

		},
		{
			inputJson: `{"nome": "","ibsn": "97801323","autor": "Robert C. Martin","quantidade": "1000","preco": "39,90","loja": "Amazon"}`,
			statusCode: 422,
			errMessage: "Please enter a valid name",
		},
		{
			inputJson: `{"nome": "Clean Code","ibsn": "","autor": "Robert C. Martin","quantidade": "1000","preco": "39,90","loja": "Amazon"}`,
			statusCode: 422,
			errMessage: "Please enter a valid ibsn",
		},
		{
			inputJson: `{"nome": "Clean Code","ibsn": "97801323","autor": "","quantidade": "1000","preco": "39,90","loja": "Amazon"}`,
			statusCode: 422,
			errMessage: "Please enter a valid Author",
		},
		{
			inputJson: `{"nome": "Clean Code","ibsn": "97801323","autor": "Robert C. Martin","quantidade": "","preco": "39,90","loja": "Amazon"}`,
			statusCode: 422,
			errMessage: "Please enter a valid Quantity",
		},
		{
			inputJson: `{"nome": "Clean Code","ibsn": "97801323","autor": "Robert C. Martin","quantidade": "1000","preco": "","loja": "Amazon"}`,
			statusCode: 422,
			errMessage: "Please enter a valid price",
		},
		{
			inputJson: `{"nome": "Clean Code","ibsn": "97801323","autor": "Robert C. Martin","quantidade": "1000","preco": "39,90","loja": ""}`,
			statusCode: 422,
			errMessage: "Please enter a valid store",
		},
	}
	for _, v := range samples{
		handler := http.HandlerFunc(controllers.GetAll)
		request, err := http.NewRequest("POST", "/insert", bytes.NewBufferString(v.inputJson))
		if err != nil{
			t.Errorf("this is the error: %v\n", err)
		}
		rr := httptest.NewRecorder()
		handler.ServeHTTP(rr, request)

		responseMap := make(map[string]interface{})
		err = json.Unmarshal(rr.Body.Bytes(), &responseMap)
		if err != nil {
			t.Errorf("cannot convert to json: %v", err)
		}
		fmt.Println("this is the response data: ", responseMap)
		assert.Equal(t, rr.Code, v.statusCode)
		if v.statusCode == 201 {
			assert.Equal(t, responseMap["nome"], v.name)
			assert.Equal(t, responseMap["ibsn"], v.ibsn)
			assert.Equal(t, responseMap["autor"], v.author)
			assert.Equal(t, responseMap["quantidade"], v.quantity)
			assert.Equal(t, responseMap["preco"], v.price)
			assert.Equal(t, responseMap["loja"], v.store)
		}
		if v.statusCode == 400 || v.statusCode == 422 || v.statusCode == 500 && v.errMessage != ""{
			assert.Equal(t, responseMap["message"], v.errMessage)
		}
		defer db.Close()
	}		
}

func TestUpdate(t *testing.T){
	db := config.Connection()

	var book []entities.Stock

	bookId := book[0].Id

	samples := []struct{
		id string
		inputJson string
		statusCode int
		name string
		ibsn string
		author string
		quantity string 
		price string
		store string
		errMessage string 
	}{
		{
			id: strconv.Itoa(int(bookId)),
			inputJson: `{"nome": "update","ibsn": "97801323","autor": "Robert C. Martin","quantidade": "1000","preco": "39,90","loja": "Amazon"}`,
			statusCode: 200,
			name: "update",
			ibsn:"97801323",
			author:"Robert C. Martin",
			quantity:"1000",
			price:"39,90",
			store:"Amazon",
			errMessage: "",
			
		},
		{
			id: strconv.Itoa(int(bookId)),
			inputJson: `{"nome": "update","ibsn": "97801323","autor": "Robert C. Martin","quantidade": "1000","preco": "39,90","loja": "Amazon"}`,
			statusCode: 500,
			errMessage: "title already taken",
		},
		{
			id: strconv.Itoa(int(bookId)),
			inputJson: `{"nome": "","ibsn": "97801323","autor": "Robert C. Martin","quantidade": "1000","preco": "39,90","loja": "Amazon"}`,
			statusCode: 422,
			errMessage: "Please enter a valid name",
		},
		{
			id: strconv.Itoa(int(bookId)),
			inputJson: `{"nome": "Clean Code","ibsn": "","autor": "Robert C. Martin","quantidade": "1000","preco": "39,90","loja": "Amazon"}`,
			statusCode: 422,
			errMessage: "Please enter a valid ibsn",
		},
		{
			id: strconv.Itoa(int(bookId)),
			inputJson: `{"nome": "Clean Code","ibsn": "97801323","autor": "","quantidade": "1000","preco": "39,90","loja": "Amazon"}`,
			statusCode: 422,
			errMessage: "Please enter a valid Author",
		},
		{
			id: strconv.Itoa(int(bookId)),
			inputJson: `{"nome": "Clean Code","ibsn": "97801323","autor": "Robert C. Martin","quantidade": "","preco": "39,90","loja": "Amazon"}`,
			statusCode: 422,
			errMessage: "Please enter a valid Quantity",
		},
		{
			id: strconv.Itoa(int(bookId)),
			inputJson: `{"nome": "Clean Code","ibsn": "97801323","autor": "Robert C. Martin","quantidade": "1000","preco": "","loja": "Amazon"}`,
			statusCode: 422,
			errMessage: "Please enter a valid price",
		},
		{
			id: strconv.Itoa(int(bookId)),
			inputJson: `{"nome": "Clean Code","ibsn": "97801323","autor": "Robert C. Martin","quantidade": "1000","preco": "39,90","loja": ""}`,
			statusCode: 422,
			errMessage: "Please enter a valid store",
		},
		{
			id:         "unknwon",
			statusCode: 400,
			errMessage: "Book id should be a number",
		},
		{
			id: strconv.Itoa(12322), 
			inputJson:`{"nome": "Clean Code","ibsn": "97801323","autor": "Robert C. Martin","quantidade": "1000","preco": "39,90","loja": ""}`,
			statusCode: 404,
			errMessage: "no record matching given id",
		},
	}
	for _, v := range samples {
		handler := http.HandlerFunc(controllers.GetAll)
		request, err := http.NewRequest("PUT", "/update/"+v.id, bytes.NewBufferString(v.inputJson))
		if err != nil{
			t.Errorf("this is the error: %v\n", err)
		}
		rr := httptest.NewRecorder()
		handler.ServeHTTP(rr, request)

		responseMap := make(map[string]interface{})
		err = json.Unmarshal(rr.Body.Bytes(), &responseMap)
		if err != nil {
			t.Errorf("Cannot convert to json: %v", err)
		}
		assert.Equal(t, rr.Code, v.statusCode)
		if v.statusCode == 200 {
			assert.Equal(t, responseMap["nome"], v.name)
			assert.Equal(t, responseMap["ibsn"], v.ibsn)
			assert.Equal(t, responseMap["autor"], v.author)
			assert.Equal(t, responseMap["quantidade"], v.quantity)
			assert.Equal(t, responseMap["preco"], v.price)
			assert.Equal(t, responseMap["loja"], v.store)
		}
		if v.statusCode == 400 || v.statusCode == 422 || v.statusCode == 500 && v.errMessage != "" {
			assert.Equal(t, responseMap["message"], v.errMessage)
		}
		defer db.Close()
	}
}
func TestDeleteMessage(t *testing.T) {

	var book []entities.Stock

	bookId := book[0].Id

	samples := []struct {
		id         string
		statusCode int
		status string
		errMessage string
	}{
		{
			id:         strconv.Itoa(int(bookId)),
			statusCode: 200,
			status: "deleted",
			errMessage: "",
		},
		{
			id:         "unknwon",
			statusCode: 400,
			errMessage: "message id should be a number",
		},
		{
			id:         strconv.Itoa(12322), 
			statusCode: 404,
			errMessage: "no record matching given id",
		},
	}
	for _, v := range samples {
		handler := http.HandlerFunc(controllers.GetAll)
		request, err := http.NewRequest("DELETE", "/delete/"+v.id, nil)
		if err != nil{
			t.Errorf("this is the error: %v\n", err)
		}
		rr := httptest.NewRecorder()
		handler.ServeHTTP(rr, request)

		responseMap := make(map[string]interface{})
		err = json.Unmarshal(rr.Body.Bytes(), &responseMap)
		if err != nil {
			t.Errorf("Cannot convert to json: %v", err)
		}
		assert.Equal(t, rr.Code, v.statusCode)

		if v.statusCode == 200 {
			assert.Equal(t, responseMap["status"], v.status)
		}
		if v.statusCode == 400 || v.statusCode == 422 && v.errMessage != "" {
			assert.Equal(t, responseMap["message"], v.errMessage)
		}
	}
}