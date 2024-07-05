package com.astroganit.api.repository;

import com.astroganit.api.entities.CityDetails;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CityDetailsRepo extends JpaRepository<CityDetails, Integer> {
   List<CityDetails> findByPlaceStartsWith(String place);
}
