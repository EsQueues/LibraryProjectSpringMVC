package structure.kz.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import structure.kz.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> list(){
        return jdbcTemplate.query("SELECT * FROM Person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> index(String name){
        return jdbcTemplate.query("SELECT *FROM Person WHERE fullName=?",
                new Object[] {name}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public Person index(int id){
        return jdbcTemplate.query(
                "SELECT *FROM person WHERE id=?",new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person(fullName,year) VALUES(?,?)",
                person.getFullName(),person.getYear());
    }

    public void update(int id,Person updatedPerson){
        jdbcTemplate.update("UPDATE person SET fullname=?,year=? WHERE id=?",
                updatedPerson.getFullName(),updatedPerson.getYear(),id);
    }///////////////////////////////////////

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM person WHERE id=?",id);
    }
}

