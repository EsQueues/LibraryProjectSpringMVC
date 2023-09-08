package structure.kz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import structure.kz.dao.BookDAO;
import structure.kz.dao.PersonDAO;
import structure.kz.models.Person;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @Autowired
    public PersonController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }
    @GetMapping()
    public String list(Model model){
        model.addAttribute("people",personDAO.list());
        return "people/list";
    }

    @GetMapping("/{id}")
    public String index(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personDAO.index(id));
//        model.addAttribute("book",bookDAO.for_people(id));
        return "people/index";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") Person person){
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("person",personDAO.index(id));
        return "people/edit";
    }
    //need to understand
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person")Person person,@PathVariable("id")int id){
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
