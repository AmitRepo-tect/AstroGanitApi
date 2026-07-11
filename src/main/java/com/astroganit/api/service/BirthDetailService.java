package com.astroganit.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.astroganit.api.entities.BirthDetailInfo;
import com.astroganit.api.entities.PlanFeature;
import com.astroganit.api.entities.User;
import com.astroganit.api.entities.UserSubscription;
import com.astroganit.api.exception.AppException;
import com.astroganit.api.repository.BirthDetailRepository;
import com.astroganit.api.repository.PlanFeatureRepository;
import com.astroganit.api.repository.SubscriptionRepository;
import com.astroganit.api.repository.UserFeatureUsageRepository;
import com.astroganit.api.repository.UserRepo;
import com.astroganit.api.util.FeatureKeys;
import com.astroganit.api.util.ResultCode;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

@Service
public class BirthDetailService {

	private static final Logger log = LoggerFactory.getLogger(BirthDetailService.class);

	private final BirthDetailRepository birthDetailRepository;
	private final SubscriptionRepository userSubscriptionRepository;
	private final UserFeatureUsageRepository userFeatureUsageRepository;
	private final PlanFeatureRepository planFeatureRepository;
	private final UserRepo userRepo;

	// ✅ Constructor Injection
	public BirthDetailService(BirthDetailRepository birthDetailRepository,
			SubscriptionRepository userSubscriptionRepository, UserFeatureUsageRepository userFeatureUsageRepository,
			PlanFeatureRepository planFeatureRepository, UserRepo userRepo) {

		this.birthDetailRepository = birthDetailRepository;
		this.userSubscriptionRepository = userSubscriptionRepository;
		this.userFeatureUsageRepository = userFeatureUsageRepository;
		this.planFeatureRepository = planFeatureRepository;
		this.userRepo = userRepo;
	}

	// 🔐 Common method to get logged-in user
	private User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getName())) {
			throw new AppException(ResultCode.UNAUTHORIZED);
		}

		String loginId = auth.getName();
		log.info("Logged in user: {}", loginId);

		return userRepo.findByLoginId(loginId).orElseThrow(() -> new AppException(ResultCode.USER_NOT_FOUND));
	}

	// ✅ CREATE
	@Transactional
	public BirthDetailInfo saveBirthDetail(BirthDetailInfo data) {
		System.out.println(data.getName()+"--"+data.getClientUuid());

		User user = getLoggedInUser();
		Long userId = user.getId();
		data.setUserId(userId);
		Optional<BirthDetailInfo> existing = birthDetailRepository.findByUserIdAndClientUuid(userId,
				data.getClientUuid());

		if (existing.isPresent()) {
			return existing.get();
		}
		// 🔹 1. Active subscription
		UserSubscription sub = userSubscriptionRepository.findActiveSubscription(userId)
				.orElseThrow(() -> new AppException(ResultCode.SUBSCRIPTION_NOT_FOUND));

		String featureKey = FeatureKeys.KUNDLI_CREATE;

		// 🔹 2. Feature config
		PlanFeature feature = planFeatureRepository.findByPlan_IdAndFeatureKey(sub.getPlanId(), featureKey)
				.orElseThrow(() -> new AppException(ResultCode.FEATURE_NOT_ALLOWED));

		int limit = feature.getFeatureLimit();

		if (limit == 0) {
			throw new AppException(ResultCode.FEATURE_NOT_ALLOWED);
		}

		// 🔥 3. Atomic usage update
		int updated = userFeatureUsageRepository.upsertUsage(userId, sub.getId(), featureKey, limit);

		if (updated <= 0) {
			throw new AppException(ResultCode.LIMIT_REACHED);
		}

		// 🔹 4. Save kundli
		// return birthDetailRepository.save(data);
		try {
			return birthDetailRepository.save(data);
		} catch (DataIntegrityViolationException e) {

			return birthDetailRepository.findByUserIdAndClientUuid(userId, data.getClientUuid())
					.orElseThrow(() -> new AppException(ResultCode.INTERNAL_SERVER_ERROR));
		}
	}

	// ✅ GET ALL (User-specific)
	public List<BirthDetailInfo> getAll() {
		User user = getLoggedInUser();
		return birthDetailRepository.findByUserId(user.getId());
	}

	public Page<BirthDetailInfo> getAllPaginated(String search, Pageable pageable) {

		User user = getLoggedInUser();

		if (search != null && !search.trim().isEmpty()) {
			return birthDetailRepository.findByUserIdAndNameContainingIgnoreCase(user.getId(), search, pageable);
		}

		return birthDetailRepository.findByUserId(user.getId(), pageable);
	}

	public Page<BirthDetailInfo> getBirthDetails(int page, int size, String sortBy, String sortDir) {
		User user = getLoggedInUser();

		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(page, size, sort);

		return birthDetailRepository.findByUserId(user.getId(), pageable);
	}

	// ✅ GET BY ID (with ownership check)
	public Optional<BirthDetailInfo> getById(Long id) {
		User user = getLoggedInUser();

		BirthDetailInfo data = birthDetailRepository.findById(id)
				.orElseThrow(() -> new AppException(ResultCode.DATA_NOT_FOUND));

		if (!data.getUserId().equals(user.getId())) {
			throw new AppException(ResultCode.UNAUTHORIZED);
		}

		return Optional.of(data);
	}

	// ✅ UPDATE
	@Transactional
	public BirthDetailInfo update(Long id, BirthDetailInfo data) {

		User user = getLoggedInUser();

		BirthDetailInfo existing = birthDetailRepository.findById(id)
				.orElseThrow(() -> new AppException(ResultCode.DATA_NOT_FOUND));

		// 🔒 Ownership check
		if (!existing.getUserId().equals(user.getId())) {
			throw new AppException(ResultCode.UNAUTHORIZED);
		}

		// ✅ Partial update
		updateIfNotNull(data.getName(), existing::setName);
		updateIfNotNull(data.getSex(), existing::setSex);
		updateIfNotNull(data.getDay(), existing::setDay);
		updateIfNotNull(data.getMonth(), existing::setMonth);
		updateIfNotNull(data.getYear(), existing::setYear);
		updateIfNotNull(data.getHrs(), existing::setHrs);
		updateIfNotNull(data.getMin(), existing::setMin);
		updateIfNotNull(data.getSec(), existing::setSec);
		updateIfNotNull(data.getPlace(), existing::setPlace);
		updateIfNotNull(data.getLongDeg(), existing::setLongDeg);
		updateIfNotNull(data.getLongMin(), existing::setLongMin);
		updateIfNotNull(data.getLongEW(), existing::setLongEW);
		updateIfNotNull(data.getLatDeg(), existing::setLatDeg);
		updateIfNotNull(data.getLatMin(), existing::setLatMin);
		updateIfNotNull(data.getLatNS(), existing::setLatNS);
		updateIfNotNull(data.getTimeZone(), existing::setTimeZone);
		updateIfNotNull(data.getTimezoneStr(), existing::setTimezoneStr);
		updateIfNotNull(data.getCountry(), existing::setCountry);
		updateIfNotNull(data.getPlaceId(), existing::setPlaceId);
		updateIfNotNull(data.getState(), existing::setState);
		updateIfNotNull(data.getDst(), existing::setDst);
		updateIfNotNull(data.getAyanamsa(), existing::setAyanamsa);
		updateIfNotNull(data.getKphn(), existing::setKphn);
		updateIfNotNull(System.currentTimeMillis(), existing::setUpdateTime);
		updateIfNotNull(System.currentTimeMillis(), existing::setViewTime);

		return birthDetailRepository.save(existing);
	}

	// ✅ DELETE (with ownership check)
	public void delete(Long id) {

		User user = getLoggedInUser();

		BirthDetailInfo data = birthDetailRepository.findById(id)
				.orElseThrow(() -> new AppException(ResultCode.DATA_NOT_FOUND));

		if (!data.getUserId().equals(user.getId())) {
			throw new AppException(ResultCode.UNAUTHORIZED);
		}

		birthDetailRepository.delete(data);
	}

	// 🔧 Utility for partial update
	private <T> void updateIfNotNull(T value, Consumer<T> setter) {
		if (value != null) {
			setter.accept(value);
		}
	}

	@Transactional
	public void updateViewTime(Long id) {
		int updated = birthDetailRepository.updateViewTime(id, System.currentTimeMillis());
		if (updated == 0) {
			throw new AppException(ResultCode.DATA_NOT_FOUND);
		}
	}

	public List<BirthDetailInfo> searchKundli(String search) {

		User user = getLoggedInUser();

		if (search == null || search.trim().isEmpty()) {
			return Collections.emptyList();
		}

		return birthDetailRepository.searchByName(user.getId(),search.trim());
	}
}