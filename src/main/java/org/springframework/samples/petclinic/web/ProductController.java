package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

	private final ProductService productService;
	
	@Autowired
	public ProductController(final ProductService productService) {
		this.productService = productService;
	}
	
	
	
	@GetMapping(value= "/productType/{productTypeId}/products")
	public String showProducts(@PathVariable final int productTypeId,
			final Map<String,Object> model) {
		model.put("products", this.productService.findProductsByProductTypeId(productTypeId));
		return "products/productList";  
	}
	
	
	@GetMapping(value="/clinics/{clinicId}/products/{productId}")
	public String showProduct( @PathVariable final int productId, final Map<String, Object> model) {
			model.put("products", this.productService.findProductsById(productId));
				return "products/productShow";
		
	}
	
	
	@GetMapping(value = "/clinics/{clinicId}/products")
	public String showClinicProducts(final Map<String, Object> model, @PathVariable final int clinicId) {
		model.put("products", this.productService.findProductByClinicId(clinicId));
		return "products/clinicProductList";
	}
	
}
