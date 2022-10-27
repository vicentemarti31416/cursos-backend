package vicente.marti.microserviciocommons.service;

import java.util.List;
import java.util.Optional;

public interface CommonService<T> {

    List<T> findAll();
    Optional<T> findById(Long id);
    T save(T type);
    void deleteById(Long id);
}
