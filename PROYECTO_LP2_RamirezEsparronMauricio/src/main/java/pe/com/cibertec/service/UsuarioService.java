package pe.com.cibertec.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import pe.com.cibertec.model.entity.UsuarioEntity;

public interface UsuarioService {
	List<UsuarioEntity> buscarUsuarios();

	void crearUsuario(UsuarioEntity usuarioEntity, MultipartFile foto);

	boolean validarUsuario(UsuarioEntity usuarioEntity);

	UsuarioEntity buscarUsuarioPorCorreo(String correo);
}
