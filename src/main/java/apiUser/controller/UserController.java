package apiUser.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import apiUser.entity.User;
import apiUser.service.UserService;


@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/insertUser", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Map<String, Object>> insertUser(@RequestBody User user, @RequestHeader Map<String, String> headers) {
		 
		 Optional<User> userInserted = userService.insertUser(user, headers);
		 
		 Map<String, Object> responseService = new LinkedHashMap<>();
		 responseService.put("mensaje",userInserted);
		 
		 return new ResponseEntity<>(responseService, HttpStatus.OK);
	}
}
