package med.vol.api.user.presentation.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.user.domain.User;
import med.vol.api.shared.security.JsonWebTokenService;
import med.vol.api.user.presentation.dto.AuthTokenUserDto;
import med.vol.api.user.presentation.dto.AuthUserDto;
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
