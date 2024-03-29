package com.sgic.internal.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sgic.internal.product.entities.DefectType;

public interface TypeRepo extends JpaRepository<DefectType, Long> {
	DefectType findDefectTypeById(Long id);
}
