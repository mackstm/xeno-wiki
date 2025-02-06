package es.ies.puerto.controller.impl;


import es.ies.puerto.dto.UserLoginDTO;
import es.ies.puerto.dto.UserRegisterDTO;
import es.ies.puerto.model.entities.User;
import es.ies.puerto.security.AuthService;
import es.ies.puerto.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthController {

    private AuthService authService;

    private UserService userService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
	 * Login de user
	 * @param u con datos de user
	 * @return token JWT
	 */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO u ) {
        String token = authService.authenticate(u.username(), u.password());

        if ( token == null ) {
            return ResponseEntity.badRequest().body("Credenciales inválidas");
        }
        return ResponseEntity.ok(token);
    }

    /**
     * Register de user
     * @param u
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDTO u ) {
        User newUser = authService.register(u.username(), u.password(), u.email());

        if (newUser == null) {
            return ResponseEntity.badRequest().body("El usuario ya existe");
        } else {
            return ResponseEntity.ok("Usuario registrado");
        }
    }
}