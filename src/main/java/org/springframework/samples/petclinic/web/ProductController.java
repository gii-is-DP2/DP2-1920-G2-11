
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

	// devuelve productos filtrados por tipo
	@GetMapping(value = "/products/productType/{productTypeId}")
	public String showProducts(@PathVariable final int productTypeId, final Map<String, Object> model) {
		model.put("products", this.productService.findProductsByProductTypeId(productTypeId));
		return "products/productList";
	}

	// devuelve producto filtrados ya clinicas y tipo producto
	@GetMapping(value = "/products/{productId}")
	public String showProduct(@PathVariable final int productId, final Map<String, Object> model) {
		model.put("products", this.productService.findProductsById(productId));
		return "products/productShow";

	}

	// devuelve los productos filtrado por clinica
	@GetMapping(value = "/clinics/{clinicId}/products")
	public String showClinicProducts(final Map<String, Object> model, @PathVariable final int clinicId) {
		model.put("products", this.productService.findProductByClinicId(clinicId));
		return "products/clinicProductList";
	}

	// controlador para el filtro y cuando ya se ha escogido una clinica: TODO
	//	@GetMapping(value = "/clinics/{clinicId}/products")
	//	public String showClinicProductFiltered(final Map<String, Object> model, @PathVariable final int clinicId) {
	//		model.put("products", this.productService.findProductByClinicId(clinicId));
	//		return "products/clinicProductList";
	//	}

}
