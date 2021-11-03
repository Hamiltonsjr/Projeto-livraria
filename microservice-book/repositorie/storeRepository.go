package repositorie

import (
	"encoding/json"
	"strconv"

	"hamilton/ms-stockRegister/bookstore"
	"io/ioutil"
	"log"
	"net/http"
)

func GetStore(storeId int64) bool {

	id := strconv.Itoa(int(storeId))
	url := "http://livraria:8082/livraria/getId/" + id

	resp, err := http.Get(url)
	if err != nil {
		log.Fatal(err.Error())
	}

	if resp.StatusCode != 200 {
		return false
	}

	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		log.Fatal(err.Error())
	}

	store := bookstore.Store{}

	err = json.Unmarshal(body, &store)
	if err != nil {
		log.Fatal(err.Error())
	}

	return true
}
