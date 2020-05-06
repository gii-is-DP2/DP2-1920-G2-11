
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
import org.springframework.samples.petclinic.model.ProductType;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.service.ProductService;
import org.springframework.samples.petclinic.service.ProductTypeService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = ProductController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class ProductControllerTests {

	@Autowired
	private ProductController productController;

	@MockBean
	private ProductService productService;

	@MockBean
	private ProductTypeService productTypeService;

	@MockBean
	private ClinicService clinicService;

	@Autowired
	private MockMvc mockMvc;

	private static final int TEST_PRODUCT_ID = 1;

	private static final int TEST_CLINIC_ID = 1;

	private static final int TEST_PRODUCT_TYPE_ID = 1;

	@BeforeEach
	void setup() {
		List<Product> products = new ArrayList<Product>();
		Product product = new Product();
		product.setDescription("para pelo seco");
		product.setId(1);
		product.setName("chámpu hidratante");
		product.setPrice(4.00);
		ProductType productType = new ProductType();
		productType.setId(1);
		productType.setName("Higiene");
		product.setProductType(productType);
		product.setStock(3);
		Product product2 = new Product();
		product2.setDescription("contiene láminas masticables para mantener limpia la dentadura del perro");
		product2.setId(2);
		product2.setName("Limpiador bucal");
		product2.setPrice(8.00);
		ProductType productType2 = new ProductType();
		productType2.setId(1);
		productType2.setName("Higiene");
		product2.setProductType(productType);
		product2.setStock(100);

		products.add(product);
		products.add(product2);

		BDDMockito.given(this.productService.findProductByClinicId(ProductControllerTests.TEST_CLINIC_ID))
				.willReturn(products);
		BDDMockito.given(this.productService.findProductsByProductTypeId(ProductControllerTests.TEST_PRODUCT_TYPE_ID))
				.willReturn(products);
		BDDMockito.given(this.productService.findProductsById(ProductControllerTests.TEST_PRODUCT_ID))
				.willReturn(product);
		// BDDMockito.given(this.productService.findProducts().contains(products));

	}

	@WithMockUser(value = "spring")
	@Test
	void testShowProductsList() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/clinics/{clinicId}/products",
						ProductControllerTests.TEST_CLINIC_ID))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("products"))
				.andExpect(MockMvcResultMatchers.view().name("products/clinicProductList"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowProduct() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/products/{productId}", ProductControllerTests.TEST_PRODUCT_ID))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("products"))
				.andExpect(MockMvcResultMatchers.view().name("products/productShow"));

	}

	// devuelve productos filtrados por tipo

	@WithMockUser(value = "spring")
	@Test
	void testShowProducts() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/products/productType/{productTypeId}",
						ProductControllerTests.TEST_PRODUCT_TYPE_ID))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("products"))
				.andExpect(MockMvcResultMatchers.view().name("products/productList"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProducts() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/products")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("products"))
				.andExpect(MockMvcResultMatchers.view().name("products/productList"));
	}
	
	
	// TODO: casos negativos, meter redireccion a vista de error en controller

}
