package org.springframework.samples.petclinic.web.e2e.mysql;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.web.ProductControllerTests;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-mysql.properties")
public class ProductControllerE2EMySQLTest {

	@Autowired
	private MockMvc mockMvc;

	private static final int TEST_PRODUCT_ID = 1;
	private static final int TEST_PRODUCT_ID_ERROR = 4;

	private static final int TEST_CLINIC_ID = 1;
	private static final int TEST_CLINIC_ID_ERROR = 3;

	@WithMockUser(username = "owner1", authorities = { "veterinarian", "admin" })
	@Test
	void testShowProductsList() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/clinics/{clinicId}/products",
						ProductControllerE2EMySQLTest.TEST_CLINIC_ID))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("products"))
				.andExpect(MockMvcResultMatchers.view().name("products/clinicProductList"));
	}

	@WithMockUser(username = "owner1", authorities = { "veterinarian", "admin" })
	@Test
	void testShowProductsListError() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/clinics/{clinicId}/products",
						ProductControllerE2EMySQLTest.TEST_CLINIC_ID_ERROR))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("products"))
				.andExpect(MockMvcResultMatchers.view().name("products/productDetailsError"));
	}

	@WithMockUser(username = "owner1", authorities = { "veterinarian", "admin" })
	@Test
	void testShowProduct() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/products/{productId}", ProductControllerE2EMySQLTest.TEST_PRODUCT_ID))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("products"))
				.andExpect(MockMvcResultMatchers.view().name("products/productShow"));

	}
	
	
	@WithMockUser(username = "owner1", authorities = { "veterinarian", "admin" })
	@Test
	void testShowProductError() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/products/{productId}", ProductControllerE2EMySQLTest.TEST_PRODUCT_ID_ERROR))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("products"))
				.andExpect(MockMvcResultMatchers.view().name("products/productDetailsError"));

	}

	@WithMockUser(username = "owner1", authorities = { "veterinarian", "admin" })

	@Test
	void testProducts() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/products")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("products"))
				.andExpect(MockMvcResultMatchers.view().name("products/productList"));
	}

	@WithMockUser(username = "owner1", authorities = { "veterinarian", "admin" })
	@Test
	void testInitCreationForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/products/new"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("products/editProduct"));
	}

	// Error, tiene que ser algo del type
	@WithMockUser(username = "owner1", authorities = { "veterinarian", "admin" })
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/products/save").with(SecurityMockMvcRequestPostProcessors.csrf())
						.param("description", "Comida").param("name", "Filete").param("price", "2.00")
						.param("stock", "1"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.view().name("products/productList"));
	}

	@WithMockUser(username = "owner1", authorities = { "veterinarian", "admin" })
	@Test
	void testProcessCreationFormHasErrors1() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/products/save").with(SecurityMockMvcRequestPostProcessors.csrf())
						.param("name", "Filete"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeHasErrors("product"))
				.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("product", "description"))
				.andExpect(MockMvcResultMatchers.view().name("products/editProduct"));
	}

	@WithMockUser(username = "owner1", authorities = { "veterinarian", "admin" })
	@Test
	void testProcessCreationFormHasErrors2() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/products/save").with(SecurityMockMvcRequestPostProcessors.csrf())
						.param("description", "Comida").param("name", "Filete").param("stock", "a").param("price", "A"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeHasErrors("product"))
				.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("product", "price"))
				.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("product", "stock"))
				.andExpect(MockMvcResultMatchers.view().name("products/editProduct"));
	}

}
