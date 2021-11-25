package RestAPI.Quest.controller;

import RestAPI.Quest.entity.Book;
import RestAPI.Quest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookRepository repository;

    //Get a list of all books
    @GetMapping({"/","/books"})
    public List<Book> index(){
        return repository.findAll();
    }

    //Get a specific book, requested by its ID
    @GetMapping("/books/{id}")
    public Book show(@PathVariable int id){
        return repository.findById(id).get();
    }

    //Search for a book by a String ("text") in author or description or title
    /*@PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
        return repository.findByTitleContainingOrDescriptionContainingOrAuthorContaining(searchTerm, searchTerm, searchTerm);
    }*/

    @GetMapping("/books/search")
    public List<Book> search(@RequestParam("searchTerm") String searchTerm){
        return repository.findByTitleContainingOrDescriptionContainingOrAuthorContaining(searchTerm, searchTerm, searchTerm);
    }

    //Create/add a new book
    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return repository.save(book);
    }

    //Update a book
    @PutMapping("/books/{id}")
    public Book update(@PathVariable int id, @RequestBody Book book){
        // getting book
        Book bookToUpdate = repository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return repository.save(bookToUpdate);
    }

    //Delete a book
    @DeleteMapping("books/{id}")
    public boolean delete(@PathVariable int id){
        repository.deleteById(id);
        return true;
    }
}
