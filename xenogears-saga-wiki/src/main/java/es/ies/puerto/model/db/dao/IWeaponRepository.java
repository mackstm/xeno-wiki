package es.ies.puerto.model.db.dao;
import es.ies.puerto.model.entities.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mackstm
 */
@Repository
public interface IWeaponRepository extends JpaRepository<Weapon, Integer> {
}
