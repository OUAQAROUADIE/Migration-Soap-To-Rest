package com.studentmanagment.repo;

import com.studentmanagment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository<Product, Long> {
}
