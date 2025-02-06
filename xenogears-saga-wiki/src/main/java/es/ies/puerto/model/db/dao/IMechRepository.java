package es.ies.puerto.model.db.dao;
import es.ies.puerto.model.entities.Mech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mackstm
 */
@Repository
public interface IMechRepository extends JpaRepository<Mech, Integer> {
}
