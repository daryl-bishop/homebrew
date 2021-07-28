package com.example.Homebrew.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Homebrew.service.HomeBrewService;

@CrossOrigin
@RestController

public class HomeBrewController {

	private HomeBrewService service;

	public HomeBrewController(HomeBrewService service) {
		super();
		this.service = service;
	}

	@GetMapping("/")
	public String hello() {
		return "Lets see your amazing brews!";

	}

	@PostMapping("/createBrew")
	public ResponseEntity<Brew> createBrews(@RequestBody Brew brew) {
		Brew created = this.service.createBrews(brew);
		return new ResponseEntity<>(created, HttpStatus.CREATED);

	}

	@GetMapping("/getAllBrews")
	public List<Brew> getAllBrews() {
		return this.service.getAllBrews();

	}

	@GetMapping("/getByType/{type}")
	public List<Brew> getByType(@PathVariable String type) {
		return this.service.getByType(type);

	}

	@GetMapping("/getBrews/{id}")
	public ResponseEntity<Brew> getBrews(@PathVariable int id) {
		Brew found = this.service.getBrew(id);
		return new ResponseEntity<>(found, HttpStatus.ACCEPTED);
	}

	@PutMapping("/replaceBrew/{id}")
	public ResponseEntity<Brew> replaceBrews(@PathVariable int id, @RequestBody Brew newBrew) {
		Brew body = this.service.replaceBrew(id, newBrew);
		return new ResponseEntity<Brew>(body, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteBrew/{id}")
	public ResponseEntity<String> deleteBrew(@PathVariable int id) {
		String body = this.service.deleteBrew(id);
		return new ResponseEntity<String>(body, HttpStatus.NO_CONTENT);

	}

}
