package structure.kz.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import structure.kz.dao.BookDAO;
import structure.kz.dao.PersonDAO;
import structure.kz.models.Book;
import structure.kz.models.Person;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

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
    public String index(@PathVariable("id") int id,Model model){
        model.addAttribute("person",personDAO.index(id));
        model.addAttribute("book",bookDAO.index(id));
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("book",new Book());
        return "books/new";
    }
    @PostMapping
    public String create(@ModelAttribute("book") Book book){
        bookDAO.save(book);
        return "redirect:/books";
    }



    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book",bookDAO.index(id));
        return "books/edit";
    }

   @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book book, @PathVariable("id")int id){
        bookDAO.update(book,id);
        return "redirect:/books";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }


}
