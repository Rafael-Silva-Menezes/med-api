package med.vol.api.presentation.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.domain.entities.User;
import med.vol.api.infra.security.JsonWebTokenService;
import med.vol.api.presentation.dto.user.AuthTokenUserDto;
import med.vol.api.presentation.dto.user.AuthUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
public class UserAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JsonWebTokenService jsonWebTokenService;

    @PostMapping("/auth")
    @Transactional
    public ResponseEntity authenticate(@RequestBody @Valid AuthUserDto user) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(user.username(), user.password());

        var authentication = authenticationManager.authenticate(authenticationToken);
        String jsonWebToken = jsonWebTokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new AuthTokenUserDto(jsonWebToken));
    }
}
