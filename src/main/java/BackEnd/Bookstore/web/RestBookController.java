package BackEnd.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import BackEnd.Bookstore.model.Book;
import BackEnd.Bookstore.model.BookRepository;

@RestController
public class RestBookController {

    @Autowired
	private BookRepository brepository; 

    @RequestMapping(value="/books", method = RequestMethod.GET)
    public List<Book> bookListRest() {	
        return (List<Book>) brepository.findAll();
    }    

    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public Optional<Book> findBookRest(@PathVariable("id") Long id) {	
    	return brepository.findById(id);
    }
	
    @RequestMapping(value="/book", method = RequestMethod.POST)
    public Book addbook(@RequestBody Book newBook) {
		return brepository.save(newBook);
	}
}
