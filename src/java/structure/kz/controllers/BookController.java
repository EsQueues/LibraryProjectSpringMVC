package structure.kz.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import structure.kz.dao.BookDAO;
import structure.kz.dao.PersonDAO;
import structure.kz.models.Book;
import structure.kz.models.Person;
import structure.kz.util.PersonValidator;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    private PersonValidator personValidator;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String list(Model model){
        model.addAttribute("books",bookDAO.list());
        return "books/list";
    }
    @GetMapping("{id}")
    public String index(@PathVariable("id") int id,@ModelAttribute("person")Person person, Model model){
        model.addAttribute("book",bookDAO.index(id));
        Optional<Person>bookOwner= bookDAO.getOwnerId(id);
        if(bookOwner.isPresent())
            model.addAttribute("person",bookOwner);
        else
            model.addAttribute("people",personDAO.list());

        return "books/index";
    }

    public String release(){
        return "d";
    }





    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("book",new Book());
        return "books/new";
    }
    @PostMapping
    public String create(@ModelAttribute("book")@Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "books/new";
        }
        bookDAO.save(book);
        return "redirect:/books";
    }



    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id")  int id){

        model.addAttribute("book",bookDAO.index(id));
        return "books/edit";
    }

   @PatchMapping("/{id}")
    public String update(@ModelAttribute("book")@Valid Book book,BindingResult bindingResult ,@PathVariable("id")int id){
       if(bindingResult.hasErrors()){
           return "books/edit";
       }
        bookDAO.update(book,id);
        return "redirect:/books";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }


}
