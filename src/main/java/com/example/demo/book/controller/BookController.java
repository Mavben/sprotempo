package com.example.demo.book.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.book.dto.BookCreateDTO;
import com.example.demo.book.dto.BookReadResponseDTO;
import com.example.demo.book.service.BookService;

// @Controller 어노테이션이 있는 클래스는 
// 스프링 부트가 브라우저의 요청(request)을 받아들이는 컨트롤러라고 인지해서 자바 빈(java bean)으로 등록해서 관리하게 됩니다. 
// 즉 프레임워크에서 관리하는 클래스가 됩니다.

@Controller
public class BookController {
	
	// controller -> service 객체 추가
	 @Autowired  
	 private BookService bookService;
	
	 @GetMapping("/book/create")
	    public String create() {
	        return "book/create";
	 }
	 
	 // Post - 데이터 변경에 필요한 액션들(생성, 수정, 삭제)을 할 때 사용
	 @PostMapping("/book/create")  
	 public String insert(BookCreateDTO bookCreateDTO) {  
	   Integer bookId = this.bookService.insert(bookCreateDTO);  
	   return String.format("redirect:/book/read/%s", bookId);   
	 }

	 @GetMapping("/book/read/{bookId}")  
	 public ModelAndView read(@PathVariable Integer bookId) {  
	     ModelAndView mav = new ModelAndView();  

	     try {  
	         BookReadResponseDTO bookReadResponseDTO = this.bookService.read(bookId);  
	         mav.addObject("bookReadResponseDTO", bookReadResponseDTO);  
	         mav.setViewName("book/read");  

	     }catch(NoSuchElementException ex) {  
	         mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);  
	         mav.addObject("message", "책 정보가 없습니다.");  
	         mav.addObject("location", "/book");   
	         mav.setViewName("common/error/422");  
	     }  

	     return mav;  
	 }  

}
















