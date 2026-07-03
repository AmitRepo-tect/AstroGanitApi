package com.astroganit.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.astroganit.api.entities.BirthDetailInfo;

public interface BirthDetailRepository extends JpaRepository<BirthDetailInfo, Long> {

	// ✅ Pagination
	List<BirthDetailInfo> findByUserId(Long userId);

	// ✅ Pagination
	Page<BirthDetailInfo> findByUserId(Long userId, Pageable pageable);

	// ✅ Search + Pagination
	Page<BirthDetailInfo> findByUserIdAndNameContainingIgnoreCase(Long userId, String name, Pageable pageable);

	Optional<BirthDetailInfo> findByUserIdAndClientUuid(Long userId, String clientUuid);

	@Transactional
	@Modifying
	@Query("UPDATE BirthDetailInfo SET viewTime = :viewTime WHERE id = :id")
	int updateViewTime(Long id, Long viewTime);

	@Transactional
	@Modifying
	@Query("UPDATE BirthDetailInfo SET updateTime = :updateTime WHERE id = :id")
	int updateUpdateTime(Long id, Long updateTime);

	@Query("SELECT b FROM BirthDetailInfo b WHERE b.userId = :userId AND LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY b.viewTime DESC ")
	List<BirthDetailInfo> searchByName(Long userId, String name);
}