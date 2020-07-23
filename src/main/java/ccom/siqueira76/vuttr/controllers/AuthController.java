package ccom.siqueira76.vuttr.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ccom.siqueira76.vuttr.security.JWTUtil;
import ccom.siqueira76.vuttr.security.UserSSService;
import ccom.siqueira76.vuttr.security.UserSpringSecurity;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private JWTUtil jwtUtil;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSpringSecurity user = UserSSService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}

}
