package com.example.Homebrew.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.Homebrew.data.HomeBrewRepo;
import com.example.Homebrew.rest.Brew;

@Service
@Primary

public class HomeBrewServiceDB implements HomeBrewService {

	private HomeBrewRepo repo;

	public HomeBrewServiceDB(HomeBrewRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Brew createBrews(Brew brew) {
		return this.repo.save(brew);

	}

	@Override
	public List<Brew> getAllBrews() {
		return this.repo.findAll();
	}

	@Override
	public List<Brew> getByName(String name) {
		return this.repo.findByNameIgnoreCase(name);
	}

	@Override
	public List<Brew> getByType(String type) {
		return this.repo.findByTypeIgnoreCase(type);
	}

	@Override
	public Brew getBrew(int id) {
		return this.repo.findById(id).get();

	}

	@Override
	public Brew replaceBrew(int id, Brew newBrew) {
		Brew found = this.repo.getById(id);

		found.setType(newBrew.getType());
		found.setName(newBrew.getName());
		found.setPercentage(newBrew.getPercentage());
		found.setBrewTime(newBrew.getBrewTime());
		System.out.println("FOUND AFTER UPDATE: " + found);

		Brew updated = this.repo.save(found);
		System.out.println("UPDATED" + updated);

		return updated;
	}

	@Override
	public String deleteBrew(int id) {
		this.repo.deleteById(id);

		if (this.repo.existsById(id)) {
			return "Not deleted: " + id;

		} else {

			return "Deleted: " + id;

		}

	}

}
