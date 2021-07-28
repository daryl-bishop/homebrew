package com.example.Homebrew.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Homebrew.rest.Brew;

@Repository
public interface HomeBrewRepo extends JpaRepository<Brew, Integer> {

	List<Brew> findByTypeIgnoreCase(String type);

	List<Brew> findByNameIgnoreCase(String type);
}