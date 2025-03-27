package com.example.dlbms.repositories;

import java.util.Optional;

import com.example.dlbms.entities.Book;

public interface IBookRepository extends CRUDRepository<Book, String> {

	Optional<Book> findByTitle(String title);

}
