package com.example.Homebrew.service;

import java.util.List;

import com.example.Homebrew.rest.Brew;

public interface HomeBrewService {

	List<Brew> getAllBrews = null;

	public Brew createBrews(Brew brew);

	public List<Brew> getAllBrews();

	public Brew getBrew(int id);

	public Brew replaceBrew(int id, Brew newBrew);

	public String deleteBrew(int id);

	public List<Brew> getByType(String type);

	public List<Brew> getByName(String type);
}