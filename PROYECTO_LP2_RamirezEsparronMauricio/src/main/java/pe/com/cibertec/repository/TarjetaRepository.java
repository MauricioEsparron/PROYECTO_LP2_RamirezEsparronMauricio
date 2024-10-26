package pe.com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.cibertec.model.entity.TarjetaEntity;

@Repository
public interface TarjetaRepository extends JpaRepository<TarjetaEntity, Integer> {
}
