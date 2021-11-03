package config

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql"
)

var USER = "estoque"
var PASS = "estoque"
var HOST = "db-estoque"
var PORT = "3306"
var DBNAME = "db-stock"

func CreateDatabase() {

	URL := fmt.Sprintf("%s:%s@tcp(%s:%s)/", USER, PASS, HOST, PORT)
	db, err := sql.Open("mysql", URL)

	if err != nil {
		panic(err.Error())
	}
	_, err = db.Exec("create database if not exists `db-stock`")
	if err != nil {
		panic(err.Error())
	} else {
		log.Println("Criou Banco")
	}

	_, err = db.Exec("use `db-stock`")
	if err != nil {
		panic(err.Error())
	} else {
		log.Println("Usando Banco")
	}

	stmt, err := db.Prepare(
		`create table if not exists book(
		id integer AUTO_INCREMENT NOT NULL,
		nome VARCHAR(250),
		IBSN VARCHAR(250),
		autor VARCHAR(250),
		quantidade VARCHAR(250),
		preco VARCHAR(250),
		loja int,
		PRIMARY KEY (id))`)

	if err != nil {
		panic(err.Error())
	} else {
		log.Println("Preparou tabela")
	}
	_, err = stmt.Exec()

	if err != nil {
		panic(err.Error())
	} else {
		log.Println("Criou tabela")
	}
	defer db.Close()
}

func Connection() *sql.DB {

	URL := fmt.Sprintf("%s:%s@tcp(%s:%s)/%s?charset=utf8&parseTime=True&loc=Local", USER, PASS, HOST, PORT, DBNAME)
	db, err := sql.Open("mysql", URL)
	if err != nil {
		panic(err.Error())
	}
	return db
}
