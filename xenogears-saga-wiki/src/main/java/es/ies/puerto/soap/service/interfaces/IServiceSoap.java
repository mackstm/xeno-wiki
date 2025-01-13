package es.ies.puerto.soap.service.interfaces;

import java.util.List;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

@WebService(targetNamespace = "soap.service.interfaces")
public interface IServiceSoap<T> {

    @WebMethod
    @WebResult(
            name="item")
    List<T> getAll();

    @WebMethod
    T getById(@WebParam(name = "id") int id);

    @WebMethod
    public boolean add(T t);
    public boolean update(T t);

    @WebMethod
    public boolean delete(int id);
}
