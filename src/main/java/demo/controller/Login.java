package demo.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.model.UserLogin;
import demo.model.Usuario;
import demo.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class Login {
	@Qualifier("UsuarioRepository")
	private final UsuarioRepository repository;
	// Servicio de login
	private AtomicLong id = new AtomicLong();

	public Login(@Qualifier("usuarioRepository") UsuarioRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/user")
	public UserLogin login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
		// En el caso normal debería chequear que el usuario exista.
		UserLogin userLogin = new UserLogin();
		Usuario usuario = repository.getUsuario(username, pwd);
		System.out.println(usuario);
		if (usuario != null) {
			String token = getJWTToken(username, pwd.equals("link"));
			userLogin.setId(usuario.getId());
			userLogin.setUsername(username);;
			userLogin.setToken(token);
			return userLogin;
		}
		return null;
	}

	// Genero el token.
	private String getJWTToken(String username, boolean link) {
		String secretKey = "mySecretKey";
		String roles = "ROLE_USER";
		if (link) {
			roles += ",LINK";
		}
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);

		String token = Jwts.builder().setId(String.valueOf(this.id)).setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
				this.id.getAndIncrement();
		return "Bearer " + token;
	}
}