package lk.ijse.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    public boolean save(T object);
    public boolean update(T object);
    public boolean delete(T object);
    public T get(T object);
    List<T> getAll();
}
