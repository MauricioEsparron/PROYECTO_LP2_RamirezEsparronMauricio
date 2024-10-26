package pe.com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.cibertec.model.entity.CatetgoriaProductoEntity;

@Repository
public interface CategoriaProductoRepository extends JpaRepository<CatetgoriaProductoEntity, Integer> {

}
