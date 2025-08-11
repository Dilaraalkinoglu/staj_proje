package com.dilaraalk.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dilaraalk.dto.ProductRequestDto;
import com.dilaraalk.dto.ProductResponseDto;

public interface IProductService {
	
	ProductResponseDto createProduct(ProductRequestDto productRequestDto);
	
	ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);
	
	void deleteProduct(Long id);
	
	ProductResponseDto getProductById(Long id);
	
	List<ProductResponseDto> getAllProducts();

	Page<ProductResponseDto> getAllProductsPaginated(Pageable pageable);

	
	
	

}
