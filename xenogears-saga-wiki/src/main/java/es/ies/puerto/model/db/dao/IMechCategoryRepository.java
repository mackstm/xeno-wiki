package es.ies.puerto.model.db.dao;
import es.ies.puerto.model.entities.MechCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mackstm
 */
@Repository
public interface IMechCategoryRepository extends JpaRepository<MechCategory, Integer> {
}
