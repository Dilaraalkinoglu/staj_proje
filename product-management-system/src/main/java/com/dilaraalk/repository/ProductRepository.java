package com.dilaraalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.dilaraalk.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{

}
