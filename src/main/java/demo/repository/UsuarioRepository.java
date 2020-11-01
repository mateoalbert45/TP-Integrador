package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.model.Plan;
import demo.model.Usuario;
import demo.model.Viaje;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	
	   @Query("select u.id FROM Usuario u where mail=:mail and contraseña=:contraseña")
	    public long getIdUsuario(String mail, String contraseña);
	   
	   @Query("select v from Viaje v join v.usuario u where u.id=:usuario")
	    public List<Viaje> getViajes(Long usuario);
	   
}
