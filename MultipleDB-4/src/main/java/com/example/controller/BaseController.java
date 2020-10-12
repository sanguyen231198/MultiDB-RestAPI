package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.mysql.UserMySQL;
import com.example.entity.postgres.UserPostgres;
import com.example.service.mysql.UserServiceMySQL;
import com.example.service.postgres.UserServicePostgres;

@RestController
public class BaseController {
	@Autowired
	UserServiceMySQL userServiceMySQL;
	@Autowired
	UserServicePostgres userServicePostgres;

//	@Autowired
//	private UserMySQLRepository userMySQLRepository;
//	@Autowired
//	private UserPostgresRepository userPostgresRepository;

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String welcome(Model model) {
//		String message = "Hello Spring Boot + JSP";
//		model.addAttribute("message", message);
//		return "index";
//	}

	@GetMapping(value = "/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/list")
	public List<UserMySQL> all() {
		return userServiceMySQL.findAllUser();
	}
	@GetMapping("/listPostgres")
    public List<UserPostgres> allPostgres() {
    return userServicePostgres.findAllUser();
  }


	@PutMapping(value = "/mysql/updateUser/{id}")
	public ResponseEntity<UserMySQL> updateUser(@PathVariable("id") Long id, @RequestBody UserMySQL userMySQL) {
		Optional<UserMySQL> currentUser = userServiceMySQL.findUserByID(id);

		if (!currentUser.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

        currentUser.get().setUsername(userMySQL.getUsername());
        currentUser.get().setPassword(userMySQL.getPassword());
    

		userServiceMySQL.save(currentUser.get());
		return new ResponseEntity<>(currentUser.get(), HttpStatus.OK);
	}
	@PutMapping(value = "/postgres/updateUser/{id}")
	public ResponseEntity<UserPostgres> updateUserPostgres(@PathVariable("id") Long id, @RequestBody UserPostgres userPostgres) {
		Optional<UserPostgres> currentUser = userServicePostgres.findUserByID(id);

		if (!currentUser.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

        currentUser.get().setUsername(userPostgres.getUsername());
        currentUser.get().setPassword(userPostgres.getPassword());
    

		userServicePostgres.save(currentUser.get());
		return new ResponseEntity<>(currentUser.get(), HttpStatus.OK);
	}

}
