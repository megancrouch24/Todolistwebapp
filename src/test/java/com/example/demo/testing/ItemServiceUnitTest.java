package com.example.demo.testing;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.itemrepo.ItemRepo;
import com.example.rest.Items;
import com.example.service.ItemService;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
public class ItemServiceUnitTest {

	@MockBean
	private ItemRepo repo;

	@Autowired
	private ItemService service;

//CREATE
	@Test
	void testCreateUnit() {

		Items item = new Items("buy beach towel", "medium");
		Items itemWithId = new Items(2L, "buy beach towel", "medium");

		Mockito.when(this.repo.saveAndFlush(item)).thenReturn(itemWithId);
		assertThat(this.service.create(item)).isEqualTo(itemWithId);
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(item);

	}
	
//DELETE
		@Test
		void testDeleteSucceeds() {
			Long id = 2L;

			Mockito.when(this.repo.existsById(id)).thenReturn(true);

			assertThat(this.service.delete(id)).isEqualTo("This id wasnt deleted!: " + id);

			Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
		}

		@Test
		void testDeleteFails() {
			Long id = 2L;

			Mockito.when(this.repo.existsById(id)).thenReturn(false);

			assertThat(this.service.delete(id)).isEqualTo("This id was deleted!: " + id);

			Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
		}

//READALL

		@Test
		void testReadAll() {
			List<Items> item = List.of(new Items(2L, "buy beach towel", "medium"));

			Mockito.when(this.repo.findAll()).thenReturn(item);

			assertThat(this.service.read()).isEqualTo(item);

			Mockito.verify(this.repo, Mockito.times(1)).findAll();
		}
	

}

