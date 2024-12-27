package es.ies.puerto.service.interfaces;
import java.util.List;
/**
 * @author mackstm
 */

// TODO: update add and update to separate methods and use id for update
public interface IServiceJPA<T> {
    public boolean add(T t);
    public boolean update(int id, T t) throws Exception;
    public List<T> getAll();
    public T getById(int id);
    public boolean delete(int id);
}