package com.dilaraalk.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
	
	private Long id;
	
	private String name;
	
	private double price;
	
	private int stock;

}
