package es.ies.puerto.model.db.dao;


import es.ies.puerto.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    @Query(
            value = "SELECT * FROM users WHERE username = :username",
            nativeQuery = true
    )
    User findByUsername(String username);
}
