package DAO;

import java.util.List;

public interface IDAO<T> {

    List<T> fetch();

    int insert(T t);

    void update(T t, T tN);

    void delete(T t);
}
