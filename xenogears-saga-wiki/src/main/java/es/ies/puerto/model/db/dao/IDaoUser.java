package es.ies.puerto.model.db.dao;


import es.ies.puerto.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDaoUser extends JpaRepository<User, Integer> {

}
