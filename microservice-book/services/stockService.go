package services

import (
	"database/sql"
	"encoding/json"
	"errors"
	"hamilton/ms-stockRegister/config"
	"hamilton/ms-stockRegister/entities"
	"hamilton/ms-stockRegister/repositorie"
	"log"
)

func GetAllBook() []entities.Stock {
	db := config.Connection()
	var books []entities.Stock

	rows, err := db.Query("SELECT * FROM `db-stock`.book")
	if err != nil {
		panic(err.Error())
	}

	for rows.Next() {
		var book entities.Stock
		err := rows.Scan(&book.Id, &book.Name, &book.IBSN, &book.Author, &book.Quantity, &book.Price, &book.Store)
		if err != nil {
			log.Fatal(err)
		}
		books = append(books, book)
	}

	defer rows.Close()
	_, jsonError := json.Marshal(books)
	if jsonError != nil {
		panic(jsonError.Error())
	}
	return books
}

func GetBookById(bookId string) entities.Stock {
	db := config.Connection()

	var book entities.Stock

	err := db.QueryRow(`SELECT * FROM book WHERE ID=?`, bookId).
		Scan(&book.Id, &book.Name, &book.IBSN, &book.Author, &book.Quantity, &book.Price, &book.Store)
	if err != nil {
		if err == sql.ErrNoRows {
			log.Println("ID não encontrado")
		} else {
			log.Fatal(err.Error())
		}
	}
	defer db.Close()
	return book
}

func InsertBook(newBook entities.Stock) (bool, error) {

	out, err := json.Marshal(newBook)
	if err != nil {
		log.Fatal(err.Error())
	}
	log.Println(string(out))

	isValidStore := repositorie.GetStore(newBook.Store)
	if isValidStore {

		db := config.Connection()
		stmt, err := db.Prepare("INSERT into book SET nome=?, ibsn=?, autor=?, quantidade=?, preco=?, loja=?")
		if err != nil {
			panic(err.Error())

		}
		_, queryError := stmt.Exec(newBook.Name, newBook.IBSN, newBook.Author, newBook.Quantity, newBook.Price, newBook.Store)
		if queryError != nil {
			panic(queryError.Error())

		}
		defer db.Close()
		return true, err

	} else {
		return false, errors.New("Livrária não encontrada")
	}
}

func UpdateBook(bookDetails entities.Stock) (bool, error) {

	out, err := json.Marshal(bookDetails)
	if err != nil {
		log.Fatal(err.Error())
	}

	log.Println(string(out))

	isValidStore := repositorie.GetStore(bookDetails.Store)
	if isValidStore {
		db := config.Connection()

		stmt, err := db.Prepare("UPDATE book set nome=?, ibsn=?, autor=?, quantidade=?, preco=?, loja=? WHERE id=?")
		if err != nil {
			panic(err.Error())
		}

		_, queryError := stmt.Exec(bookDetails.Name, bookDetails.IBSN, bookDetails.Author, bookDetails.Quantity, bookDetails.Price, bookDetails.Store, bookDetails.Id)
		if queryError != nil {
			panic(err.Error())
		}
		defer db.Close()

		return true, err
	} else {
		return false, errors.New("Livrária não encontrada")
	}
}

func DeleteBook(bookId string) bool {
	db := config.Connection()
	stmt, err := db.Prepare("DELETE FROM book WHERE id=?")
	if err != nil {
		panic(err.Error())
	}
	_, queryError := stmt.Exec(bookId)
	if queryError != nil {
		panic(queryError.Error())
	}
	defer db.Close()
	return true
}

func GetParameters(ibsn string, qtd string) entities.Stock {

	db := config.Connection()

	var book entities.Stock

	err := db.QueryRow(`SELECT * FROM book WHERE ibsn=? AND quantidade=?`, ibsn, qtd).
		Scan(&book.Id, &book.Name, &book.IBSN, &book.Author, &book.Quantity, &book.Price, &book.Store)
	if err != nil {
		if err == sql.ErrNoRows {
			log.Println("IBSN ou QUANTIDADE")
		} else {
			log.Fatal(err.Error())
		}
	}
	defer db.Close()
	return book

}
