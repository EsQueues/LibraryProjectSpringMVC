package structure.kz.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import structure.kz.dao.PersonDAO;
import structure.kz.models.Person;

@Component
public class PersonValidator implements Validator {

    private PersonDAO personDAO;
    @Autowired
    public PersonValidator(PersonDAO personDAO){
        this.personDAO=personDAO;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Person person = (Person) target;

        if(personDAO.index(person.getFullName()).isPresent())
            errors.rejectValue("fullName","","This full name has already taken");

    }

}
