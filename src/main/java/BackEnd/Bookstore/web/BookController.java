package BackEnd.Bookstore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import BackEnd.Bookstore.model.Book;
import BackEnd.Bookstore.model.BookRepository;

@Controller
public class BookController {
	
	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	BookRepository repository;
	
	@GetMapping(value = {"/", "/index"})
	public String showMainPage() {
		return "index";
	}
	
	@RequestMapping(value= "/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
	
	 @RequestMapping(value = "/add")
	 public String addBook(Model model){
	    model.addAttribute("book", new Book());
	    return "addbook";
	 }     
	    
	 @RequestMapping(value = "/save", method = RequestMethod.POST)
	 public String save(Book book){
		 repository.save(book);
	     return "redirect:booklist";
	 }    

	 @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	 	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
	    repository.deleteById(bookId);
	    return "redirect:../booklist";
	 } 
}