package vicente.marti.microserviciocommons.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class CommonServiceImpl<T, R extends JpaRepository<T, Long>> implements CommonService<T> {

    @Autowired
    protected R repository;

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public T save(T type) {
        return repository.save(type);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
