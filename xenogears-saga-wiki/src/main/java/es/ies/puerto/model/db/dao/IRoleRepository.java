package es.ies.puerto.model.db.dao;


import es.ies.puerto.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {
    @Query(
            value = "SELECT * FROM roles WHERE name = :name",
            nativeQuery = true
    )
    Role findByName(String name);
}
