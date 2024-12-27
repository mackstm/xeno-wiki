package es.ies.puerto.model.db.dao;


import es.ies.puerto.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDaoRole extends JpaRepository<Role, Integer> {
}
