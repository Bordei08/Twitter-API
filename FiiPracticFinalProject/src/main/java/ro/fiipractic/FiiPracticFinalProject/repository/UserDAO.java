package ro.fiipractic.FiiPracticFinalProject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.fiipractic.FiiPracticFinalProject.exception.UserNotFoundException;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.repository.mapper.UserRowMapper;

import javax.sql.DataSource;
import java.util.List;


@Repository
public class UserDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createUser(String username, String firstName, String lastName, String email, String password) {
        return jdbcTemplate.update("INSERT INTO \"USERS\"(\"USERNAME\",\"FIRST_NAME\", \"LAST_NAME\", \"EMAIL\", \"PASSWORD\") VALUES (?,?, ?, ?, ?)", username, firstName, lastName, email, password);
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM \"USERS\" ", new UserRowMapper());
    }

    public User getUserByUsername(String username) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM \"USERS\" WHERE \"USERNAME\" = ? ", new UserRowMapper(), username);
        } catch (EmptyResultDataAccessException ex) {
            throw new UserNotFoundException(String.format("User with username %s was not found", username));
        }
    }
}
