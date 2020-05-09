
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
public class ProductTypeController {

	private final ProductService productService;
    private final ProductTypeService productTypeService;

	
	@Autowired
	public ProductTypeController(final ProductService productService, final ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
		this.productService = productService;
	}

	
	@GetMapping(value = "/productTypes")
	public String listProductTypes(final Map<String, Object> model) {
		model.put("productTypes", this.productTypeService.findProductTypes());
		return "productTypes/productTypeList";
	}
	
	
}
