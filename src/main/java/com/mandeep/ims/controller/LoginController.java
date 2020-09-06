package com.mandeep.ims.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mandeep.ims.dto.ErrorResponseDto;
import com.mandeep.ims.dto.LoginRequestDto;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

	@Value("${application.username}")
	private String username;

	@Value("${application.username}")
	private String password;

	@PostMapping
	public ResponseEntity login(@RequestBody LoginRequestDto request) {
		if (request.getUserId().equalsIgnoreCase(username) && request.getUserId().equalsIgnoreCase(username)) {
			return ResponseEntity.ok().body("User Authenticated");
		} else {
			return new ResponseEntity(new ErrorResponseDto("Username or Password is incorrect"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
