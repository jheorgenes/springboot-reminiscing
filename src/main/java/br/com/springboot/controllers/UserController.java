package br.com.springboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.model.User;
import br.com.springboot.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/{id}")
	public User user(@PathVariable Long id) {
		 Optional<User> userFind = userRepository.findById(id);
		 if(userFind.isPresent()) {
			 return userFind.get();
		 }
		 return null;
	}
	
	@PostMapping("/")
	public User user(@RequestBody User user){
		return userRepository.save(user);
	}
	
	@GetMapping("/list")
	public List<User> list(){
		List<User> users = userRepository.findAll();
		return users;
	}
	
	@GetMapping("/list/{id}")
	public List<User> listMoreThan(@PathVariable Long id){
		return userRepository.findByIdGreaterThan(id);
		//return userRepository.findAllMoreThan(id);
	}
	
	@GetMapping("/findByName/{name}")
	public List<User> findByName(@PathVariable String name){
		return userRepository.findByNameIgnoreCase(name);
	}
}
