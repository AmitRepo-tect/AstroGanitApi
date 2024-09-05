package specification;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import com.astroganit.api.entities.StatusImg;
import jakarta.persistence.criteria.CriteriaBuilder;

public class StatusImageSpecifications {

	public static Specification<StatusImg> hasName(String name) {
		return (root, query, criteriaBuilder) -> name == null ? null
				: criteriaBuilder.like(root.get("name"), "%" + name + "%");
	}

	public static Specification<StatusImg> hasCategory(String category) {
		return (root, query, criteriaBuilder) -> category == null ? null
				: criteriaBuilder.equal(root.get("category"), category);
	}

	public static Specification<StatusImg> hasPriceBetween(Double minPrice, Double maxPrice) {
		return (root, query, criteriaBuilder) -> {
			if (minPrice == null && maxPrice == null) {
				return null;
			} else if (minPrice == null) {
				return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
			} else if (maxPrice == null) {
				return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
			} else {
				return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
			}
		};
	}

	public static Specification<StatusImg> hasCategoryIn(List<String> categories) {
		return (root, query, criteriaBuilder) -> {
			if (categories == null || categories.isEmpty()) {
				return null;
			}
			CriteriaBuilder.In<String> inClause = criteriaBuilder.in(root.get("specialDay"));
			for (String category : categories) {
				System.out.println(category);
				inClause.value(category);
			}
			return inClause;
		};
	}
}
