package com.example.demo.book.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.book.dto.BookCreateDTO;
import com.example.demo.book.dto.BookReadResponseDTO;
import com.example.demo.book.entity.Book;
import com.example.demo.book.entity.BookRepository;

import lombok.AllArgsConstructor;  

// 생성자 annotation    

@AllArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    public Integer insert(BookCreateDTO bookCreateDTO) {
        Book book = ((Object) Book.builder())
            .title(bookCreateDTO.getTitle())
            .price(bookCreateDTO.getPrice())
            .build();

        this.bookRepository.save(book);
        return book.getBookId();
    }
    
    public BookReadResponseDTO read(Integer bookId) throws NoSuchElementException {  
        Book book = this.bookRepository.findById(bookId).orElseThrow();  
        BookReadResponseDTO bookReadResponseDTO = new BookReadResponseDTO();  
        bookReadResponseDTO.fromBook(book);  
        return bookReadResponseDTO;  
    }  

}

