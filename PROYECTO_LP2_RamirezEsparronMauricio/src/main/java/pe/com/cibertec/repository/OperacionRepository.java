package pe.com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.cibertec.model.entity.OperacionEntity;

@Repository
public interface OperacionRepository extends JpaRepository<OperacionEntity, Integer> {
}
