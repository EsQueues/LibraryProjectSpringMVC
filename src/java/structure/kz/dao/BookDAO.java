package structure.kz.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import structure.kz.models.Book;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> list(){
        return jdbcTemplate.query("SELECT *FROM book",
                new BeanPropertyRowMapper<>(Book.class));
    }
    public Book index(int id){
        return jdbcTemplate.query("SELECT *FROM book WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    public List<Book> for_people(int person_id){
        return jdbcTemplate.query("SELECT *FROM book WHERE owner_id=?",new Object[]{person_id},
                new BeanPropertyRowMapper<>(Book.class));
    }
    public void save(Book book){
        jdbcTemplate.update("INSERT INTO book(name,author,year) VALUES(?,?,?)",
                book.getName(),book.getAuthor(),book.getYear());
    }
    public void update(Book updatedBook,int id){
        jdbcTemplate.update("UPDATE book SET name=?,author=?, year=? WHERE id=?" ,
                updatedBook.getName(),updatedBook.getAuthor(),updatedBook.getYear(),id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE id=?",id);
    }
}
