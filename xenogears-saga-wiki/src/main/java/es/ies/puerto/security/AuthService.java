package es.ies.puerto.security;

import es.ies.puerto.model.db.dao.IRoleRepository;
import es.ies.puerto.model.db.dao.IUserRepository;
import es.ies.puerto.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.UUID;


@Service
public class AuthService {
	

    private IUserRepository userRepository;
	private IRoleRepository roleRepository;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;

	@Autowired
	public void setUserRepository(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@Autowired
	public void setRoleRepository(IRoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	@Autowired
	public void setJwtService(JwtService jwtService) {
		this.jwtService = jwtService;
	}
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Registers new user
	 * @param username of user
	 * @param password of user
	 * @param email of user
	 * @return registered user
	 */
	public User register(String username, String password, String email) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setEmail(email);
		user.setRole(roleRepository.findByName("ROLE_USER"));
		
		return userRepository.save(user);
	}

	/**
	 * Authenticates existing user
	 * @param username of user
	 * @param password of user
	 * @return generated token
	 */
	public String authenticate(String username, String password)  {
		String generateToken = null;
		User user = userRepository.findByUsername(username);

		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
				generateToken = jwtService.generateToken(user.getUsername(), user.getRole().getName());
			}


		return generateToken;
	}


}

