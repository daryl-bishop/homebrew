package com.example.Homebrew.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.Homebrew.data.HomeBrewRepo;
import com.example.Homebrew.service.HomeBrewServiceDB;

@SpringBootTest
@ActiveProfiles("test")

public class HomeBrewServiceDBUnitTest {

	@Autowired // injects the actual service from the context
	private HomeBrewServiceDB service;

	@MockBean // tells spring to make a 'fake' repository that we can program
	private HomeBrewRepo repo;

	@Test
	void testUpdate() {

		int id = 1;

		Brew testBrew = new Brew(id, "Cider", "Mixed Berry", 7, 10);
		Brew testNewBrew = new Brew(id, "Cider", "Pear", 5, 12);

		Mockito.when(this.repo.getById(id)).thenReturn(testBrew);
		Mockito.when(this.repo.save(new Brew(id, "Cider", "Pear", 5, 12))).thenReturn(testNewBrew);

		Brew actual = this.service.replaceBrew(id, testNewBrew);

		assertThat(actual).isEqualTo(testNewBrew);

		Mockito.verify(this.repo, Mockito.times(1)).getById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(new Brew(id, "Cider", "Pear", 5, 12));

	}

	@Test
	void testDelete() {
		int id = 1;

		assertThat(this.service.deleteBrew(id)).isEqualTo("Deleted: " + id);
	}

	@Test
	void testGetAllByName() {

		List<Brew> testBrew = List.of(new Brew(1, "Cider", "Elderflower", 5, 10));

		String search = "Elderflower";
		Mockito.when(this.repo.findByNameIgnoreCase(search)).thenReturn(testBrew);

		assertThat(this.service.getByName(search)).isEqualTo(testBrew);

		Mockito.verify(this.repo, Mockito.times(1)).findByNameIgnoreCase(search);
	}

	@Test
	void testGetAll() {
		List<Brew> testBrew = List.of(new Brew(1, "Beer", "Bitter", 7, 14));

		Mockito.when(this.repo.findAll()).thenReturn(testBrew);

		assertThat(this.service.getAllBrews()).isEqualTo(testBrew);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	void testCreate() {

		Brew newBrew = new Brew("Beer", "Guiness", 6, 14);

		Brew savedBrew = new Brew(1, "Beer", "Guiness", 6, 14);

		Mockito.when(this.repo.save(newBrew)).thenReturn(savedBrew);

		assertThat(this.service.createBrews(newBrew)).isEqualTo(savedBrew);

		Mockito.verify(this.repo, Mockito.times(1)).save(newBrew);
	}
}
