
package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Clinic;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.model.ProductType;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.ProductService;
import org.springframework.samples.petclinic.service.ProductTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

	private final ProductService productService;
   // private final ProductTypeService productTypeService;

	
	@Autowired
	public ProductController(final ProductService productService, final ClinicService clinicService, final ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
		this.productService = productService;
		this.clinicService=clinicService;
	}

	// devuelve productos filtrados por tipo
	@GetMapping(value = "/products/productType/{productTypeId}")
	public String showProducts(@PathVariable final int productTypeId, final Map<String, Object> model) {
		model.put("products", this.productService.findProductsByProductTypeId(productTypeId));
		return "products/productList";
	}

	
	@GetMapping(value = "/products")
	public String listProducts(final Map<String, Object> model) {
		model.put("products", this.productService.findProducts());
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
	
	@GetMapping(value="products/new")
	public String createProduct(ModelMap modelMap) {
		Collection<Clinic> clinics=clinicService.findClinics();
		Collection<ProductType> productTypes=productTypeService.findProductTypes();
		String view= "products/editProduct";
		
		modelMap.addAttribute("product", new Product());
		modelMap.addAttribute("Clinics", clinics);
		modelMap.addAttribute("ProductTypes", productTypes);
		return view;
		
	}
	
	@PostMapping(value="products/save")
	public String saveProduct(@Valid Product product, BindingResult result,ModelMap modelMap) {
		String view="products/listProducts";
		if(result.hasErrors())
		{
			modelMap.addAttribute("product", product);
			return "products/editProduct";
		}else {
			productService.save(product);
			modelMap.addAttribute("message", "Product saved!");
			view=listProducts(modelMap);
		}
		return view;
	}
	
	@GetMapping(value="products/delete/{productId}")
	public String deleteProduct(@PathVariable("productId") int productId,ModelMap modelMap) {
		String view="products/productList";
		Product product=productService.findProductsById(productId);
		if(product!=null) {
			productService.delete(product);
			modelMap.addAttribute("message", "Product deleted!");
			view=listProducts(modelMap);
		}else {
		modelMap.addAttribute("message", "ERROR!");
		}
		return view;
		
	}
	

	// controlador para el filtro y cuando ya se ha escogido una clinica: TODO
	//	@GetMapping(value = "/clinics/{clinicId}/products")
	//	public String showClinicProductFiltered(final Map<String, Object> model, @PathVariable final int clinicId) {
	//		model.put("products", this.productService.findProductByClinicId(clinicId));
	//		return "products/clinicProductList";
	//	}

}
