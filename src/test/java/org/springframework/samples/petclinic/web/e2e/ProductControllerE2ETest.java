package org.springframework.samples.petclinic.web.e2e;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.web.ProductControllerTests;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ProductControllerE2ETest {
	
	@Autowired
	private MockMvc				mockMvc;

	private static final int	TEST_PRODUCT_ID			= 1;

	private static final int	TEST_CLINIC_ID			= 1;

	private static final int	TEST_PRODUCT_TYPE_ID	= 1;

	
	@WithMockUser(username = "owner1", authorities = {
			"veterinarian", "admin"
		})
	@Test
	void testShowProductsList() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/clinics/{clinicId}/products", ProductControllerE2ETest.TEST_CLINIC_ID)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("products"))
			.andExpect(MockMvcResultMatchers.view().name("products/clinicProductList"));
	}

	@WithMockUser(username = "owner1", authorities = {
			"veterinarian", "admin"
		})
	@Test
	void testShowProduct() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/products/{productId}", ProductControllerE2ETest.TEST_PRODUCT_ID)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("products"))
			.andExpect(MockMvcResultMatchers.view().name("products/productShow"));

	}


	@WithMockUser(username = "owner1", authorities = {
			"veterinarian", "admin"
		})

	@Test
	void testProducts() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/products")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("products"))
			.andExpect(MockMvcResultMatchers.view().name("products/productList"));
	}

}
