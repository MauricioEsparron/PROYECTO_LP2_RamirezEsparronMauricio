package pe.com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.cibertec.model.entity.EstadoCuentaEntity;

@Repository
public interface EstadoCuentaRepository extends JpaRepository<EstadoCuentaEntity, Integer> {

}
