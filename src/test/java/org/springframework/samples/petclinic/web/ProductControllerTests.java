
package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.service.ProductService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = ProductController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class ProductControllerTests {

	@Autowired
	private ProductController	productController;

	@MockBean
	private ProductService		productService;

	@Autowired
	private MockMvc				mockMvc;

	private static final int	TEST_PRODUCT_ID			= 1;
	
	private static final int TEST_CLINIC_ID = 1;



	@BeforeEach
	void setup() {
		List<Product> products= new ArrayList<>();
		Product product = new Product();
		products.add(product);
		//BDDMockito.given(this.productService.findSicknessesByPetId(SicknessControllerTest.TEST_PET_ID)).willReturn(sicknesses);
		BDDMockito.given(this.productService.findProductByClinicId(ProductControllerTests.TEST_CLINIC_ID)).willReturn(products);
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowProductsListHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/products")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("products"))
			.andExpect(MockMvcResultMatchers.view().name("products/productList"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowProductByClinicHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("products/clinic/{clinicId}", ProductControllerTests.TEST_CLINIC_ID)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("products"))
			.andExpect(MockMvcResultMatchers.view().name("products/clinicProductList"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testShowProductsHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/products/{productId}/", ProductControllerTests.TEST_PRODUCT_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeExists("product")).andExpect(MockMvcResultMatchers.view().name("products/productShow"));
	}

}

