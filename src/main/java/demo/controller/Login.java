package demo.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.model.Usuario;
import demo.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class Login {
	@Qualifier("UsuarioRepository")
	@Autowired
	private final UsuarioRepository repository;
	// Servicio de login

	public Login(@Qualifier("usuarioRepository") UsuarioRepository repository) {
		this.repository = repository;
	}

	@PostMapping("/user")
	public String login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
		// En el caso normal debería chequear que el usuario exista.
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
		Usuario usuario = repository.getUsuario(username, pwd);
		System.out.println(usuario);
		if (usuario != null) {
			System.out.println("entro al if");
			String token = getJWTToken(username, pwd.equals("link"));
			return token;
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

		String token = Jwts.builder().setId("knife").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}