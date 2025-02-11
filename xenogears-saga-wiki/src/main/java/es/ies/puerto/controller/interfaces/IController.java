package es.ies.puerto.controller.interfaces;

import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author mackstm
 */
public interface IController <T> {
    public ResponseEntity<T> add(T t);

    public ResponseEntity<String> update(int id, T t);

    public ResponseEntity<List<T>> getAll();

    public ResponseEntity<T> getById(int id);

    public ResponseEntity<String> delete(int id);
}