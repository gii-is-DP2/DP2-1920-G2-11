
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
import org.springframework.samples.petclinic.model.Clinic;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.model.ProductType;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.service.ProductService;
import org.springframework.samples.petclinic.service.ProductTypeService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = ProductController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class ProductControllerTests {

	@Autowired
	private ProductController	productController;

	@MockBean
	private ProductService		productService;

	@MockBean
	private ProductTypeService	productTypeService;

	@MockBean
	private ClinicService		clinicService;

	@Autowired
	private MockMvc				mockMvc;

	private static final int	TEST_PRODUCT_ID			= 1;

	private static final int	TEST_CLINIC_ID			= 1;

	private static final int	TEST_PRODUCT_TYPE_ID	= 1;

	private static final int	TEST_CLINIC_ERROR_ID	= 3;

	private static final int	TEST_PRODUCT_ERROR_ID	= 4;


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
		Clinic clinic = new Clinic();
		clinic.setId(1);
		clinic.setName("Winston Pet Cares");
		clinic.setAddress("Evergreen Av. 4");
		clinic.setCity("Pitsburg");
		clinic.setEmail("charles@mail.com");
		clinic.setTelephone("600033472");
		product.setClinic(clinic);

		Product product2 = new Product();
		product2.setDescription("contiene láminas masticables para mantener limpia la dentadura del perro");
		product2.setId(2);
		product2.setName("Limpiador bucal");
		product2.setPrice(8.00);
		ProductType productType2 = new ProductType();
		productType2.setId(1);
		productType2.setName("Higiene");
		product2.setProductType(productType2);
		product2.setStock(100);
		Clinic clinic2 = new Clinic();
		clinic2.setId(1);
		clinic2.setName("Winston Pet Cares");
		clinic2.setAddress("Evergreen Av. 4");
		clinic2.setCity("Pitsburg");
		clinic2.setEmail("charles@mail.com");
		clinic2.setTelephone("600033472");
		product2.setClinic(clinic2);

		products.add(product);
		products.add(product2);

		//SHOW PRODUCT

		//1,'chámpu hidratante','para pelo seco',4.00,3,1,1
		Product p = new Product();
		p.setId(1);
		p.setName("chámpu hidratante");
		p.setDescription("para pelo seco");
		p.setPrice(4.00);
		p.setStock(3);
		ProductType pt = new ProductType();
		pt.setId(1);
		pt.setName("Higiene");
		p.setProductType(pt);
		Clinic c = new Clinic();
		c.setId(1);
		c.setName("Winston Pet Cares");
		c.setAddress("Evergreen Av. 4");
		c.setCity("Pitsburg");
		c.setEmail("charles@mail.com");
		c.setTelephone("600033472");
		p.setClinic(c);

		//LIST ERROR
		List<Product> productsError = new ArrayList<Product>();

		//SHOW PRODUCT ERROR
		Product productError = new Product();
		productError.setId(4);
		productError.setName("champú para gato");
		productError.setDescription("");
		ProductType pt2 = new ProductType();
		pt2.setId(1);
		pt2.setName("Higiene");
		productError.setProductType(pt2);
		Clinic c2 = new Clinic();
		c2.setId(1);
		c2.setName("Winston Pet Cares");
		c2.setAddress("Evergreen Av. 4");
		c2.setCity("Pitsburg");
		c2.setEmail("charles@mail.com");
		c2.setTelephone("600033472");
		productError.setClinic(c);

		BDDMockito.given(this.productService.findProductsByClinicId(ProductControllerTests.TEST_CLINIC_ID)).willReturn(products);

		BDDMockito.given(this.productService.findProductById(ProductControllerTests.TEST_PRODUCT_ID)).willReturn(p);
		BDDMockito.given(this.productService.findProductsByClinicId(ProductControllerTests.TEST_CLINIC_ERROR_ID)).willReturn(productsError);
		BDDMockito.given(this.productService.findProductById(ProductControllerTests.TEST_PRODUCT_ERROR_ID)).willReturn(productError);

	}

	@WithMockUser(value = "spring")
	@Test
	void testShowProductsClinicIdList() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/clinics/{clinicId}/products", ProductControllerTests.TEST_CLINIC_ID)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("products"))
			.andExpect(MockMvcResultMatchers.view().name("products/clinicProductList"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowProduct() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/products/{productId}", ProductControllerTests.TEST_PRODUCT_ID)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("products"))
			.andExpect(MockMvcResultMatchers.view().name("products/productShow"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testProducts() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/products")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("products"))
			.andExpect(MockMvcResultMatchers.view().name("products/productList"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowProductsClinicIdListError() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/clinics/{clinicId}/products", ProductControllerTests.TEST_CLINIC_ERROR_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("productsError")).andExpect(MockMvcResultMatchers.view().name("products/productDetailsError"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowProductClinicIdListError() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/products/{productId}", ProductControllerTests.TEST_PRODUCT_ERROR_ID)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("productError"))
			.andExpect(MockMvcResultMatchers.view().name("products/productDetailsError"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testDeleteProduct() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/products/delete/{productId}", ProductControllerTests.TEST_PRODUCT_ID)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.view().name("products/productList"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/products/new")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("products/editProduct"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/products/save").with(SecurityMockMvcRequestPostProcessors.csrf()).param("description", "Comida").param("name", "Filete").param("price", "2.00").param("stock", "1"))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.view().name("products/productList"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessSaveFormHasErrors1() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/products/save").with(SecurityMockMvcRequestPostProcessors.csrf()).param("name", "Filete")).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeHasErrors("product")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("product", "description")).andExpect(MockMvcResultMatchers.view().name("products/editProduct"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessSaveFormHasErrors2() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/products/save").with(SecurityMockMvcRequestPostProcessors.csrf()).param("description", "Comida").param("name", "Filete").param("stock", "a").param("price", "A"))
			.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeHasErrors("product")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("product", "price"))
			.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("product", "stock")).andExpect(MockMvcResultMatchers.view().name("products/editProduct"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/products/edit/{productId}", ProductControllerTests.TEST_PRODUCT_ID)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("products/editProduct"));
	}

}
