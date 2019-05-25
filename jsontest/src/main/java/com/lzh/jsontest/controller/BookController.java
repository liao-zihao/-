package com.lzh.jsontest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lzh.jsontest.domain.Book;

@RestController
public class BookController {
	@RequestMapping("findBook")
	public Book findBook(@RequestBody Book book) {
		System.out.println(book);
		book.setAuthor("007");
		book.setImage("SpringMyBatis.jpg");
		book.setPrice(100.0);
		book.setRemark("这本书很垃圾");
		return book;
	}
	
	@RequestMapping("/findBooks")
	public List<Book> findBooks() {
		List<Book> books = new ArrayList<Book>();
		
		books.add(new Book(1,"疯狂Java讲义","李刚","java.jpg",109.0));
    	books.add(new Book(2,"轻量级JavaEE企业应用实战","李刚","ee.jpg",108.0));
    	books.add(new Book(3,"Spring MVC企业应用实战","肖文吉","SpringMyBatis.jpg",58.0));
    	books.add(new Book(4,"疯狂Android讲义","李刚","android.jpg",108.0));
    	books.add(new Book(5,"疯狂Ajax开发","李刚","ajax.jpg",79.0));
    	return books;
	}
}
