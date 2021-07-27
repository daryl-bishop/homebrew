package com.example.Homebrew.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Homebrew.rest.Brew;

@Service

public class HomeBrewServiceList implements HomeBrewService {

	private List<Brew> brews = new ArrayList<>();

	@Override
	public Brew createBrews(Brew brew) {
		System.out.println(brews);
		this.brews.add(brew);
		return this.brews.get(this.brews.size());
	}

	@Override
	public List<Brew> getAllBrews() {
		return this.brews;
	}

	@Override
	public Brew getBrew(int id) {
		Brew found = this.brews.get(id);
		return found;
	}

	@Override
	public Brew replaceBrew(int id, Brew newBrew) {
		return this.brews.set(id, newBrew);
	}

	@Override
	public String deleteBrew(int id) {
		this.brews.remove(id);

		return "Deleted Home Brew at index: " + id;
	}

	@Override
	public List<Brew> getByType(String type) {

		return null;
	}

}
