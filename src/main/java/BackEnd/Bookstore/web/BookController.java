package BackEnd.Bookstore.web;

import org.apache.logging.log4j.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import BackEnd.Bookstore.model.Book;
import BackEnd.Bookstore.model.BookRepository;
import BackEnd.Bookstore.model.CategoryRepository;
import jakarta.validation.Valid;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository brepository; 

	@Autowired
	private CategoryRepository crepository;
	
	@GetMapping(value = {"/", "/index"})
	public String showMainPage() {
		return "index";
	}
	
	@RequestMapping(value= "/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", brepository.findAll());
        return "booklist";
    }
	
	 @RequestMapping(value = "/add")
	 public String addBook(Model model){
		 model.addAttribute("book", new Book());
		 model.addAttribute("categories", crepository.findAll());
		 return "addbook";
	 }   
	 
	 @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	 public String editBook(@PathVariable("id") Long id, Model model) {
		 model.addAttribute("editBook", brepository.findById(id));
		 model.addAttribute("categories", crepository.findAll());
		 return "editBook";
	 }
	    
	 @RequestMapping(value = "/save", method = RequestMethod.POST)
	 public String save(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model){
		 if (bindingResult.hasErrors()) {
			 model.addAttribute("editBook", book);
			 model.addAttribute("categories", crepository.findAll());
			 return "addbook";
		 }   
		 brepository.save(book);
	     return "redirect:booklist";
	 }   

	 @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	 	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
	    brepository.deleteById(bookId);
	    return "redirect:../booklist";
	 } 
}