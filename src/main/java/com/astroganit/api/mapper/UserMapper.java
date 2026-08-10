package com.astroganit.api.mapper;

import java.util.Date;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.astroganit.api.entities.User;
import com.astroganit.api.payload.UserDto;
import com.astroganit.api.payload.UserResponse;
import com.astroganit.api.util.HUtil;

@Component
public class UserMapper {

	public void updateUserFromDto(UserDto dto, User user) {
	
		updateIfNotNull(dto.getName(), user::setName);
		updateIfNotNull(dto.getAbout(), user::setAbout);
		updateIfNotNull(dto.getEmail(), user::setEmail);

		updateIfNotNull(dto.getGender(), user::setGender);
		updateIfNotNull(dto.getMaritalStatus(), user::setMaritalStatus);

		updateIfNotNull(dto.getPlace(), user::setPlace);
		updateIfNotNull(dto.getState(), user::setState);
		updateIfNotNull(dto.getCountry(), user::setCountry);

		updateIfNotNull(dto.getDayBirth(), user::setDayBirth);
		updateIfNotNull(dto.getMonthBirth(), user::setMonthBirth);
		updateIfNotNull(dto.getYearBirth(), user::setYearBirth);

		updateIfNotNull(dto.getHourBirth(), user::setHourBirth);
		updateIfNotNull(dto.getMinuteBirth(), user::setMinuteBirth);
		updateIfNotNull(dto.getSecondBirth(), user::setSecondBirth);

		updateIfNotNull(dto.getLatitude(), user::setLatitude);
		updateIfNotNull(dto.getLatDeg(), user::setLatDeg);
		updateIfNotNull(dto.getLatMin(), user::setLatMin);
		updateIfNotNull(dto.getLatNS(), user::setLatNS);

		updateIfNotNull(dto.getLongitude(), user::setLongitude);
		updateIfNotNull(dto.getLongDeg(), user::setLongDeg);
		updateIfNotNull(dto.getLongMin(), user::setLongMin);
		updateIfNotNull(dto.getLongEW(), user::setLongEW);

		updateIfNotNull(dto.getTimeZone(), user::setTimeZone);

		updateIfNotNull(dto.getDeviceId(), user::setDeviceId);
		updateIfNotNull(dto.getAppVersion(), user::setAppVersion);
		updateIfNotNull(dto.getAndroidVersion(), user::setAndroidVersion);
	}

	private void updateIfNotNull(String value, Consumer<String> setter) {
		if (!HUtil.isNullEmpty(value)) {
			setter.accept(value);
		}
	}

	public UserResponse toResponse(User user) {

		UserResponse response = new UserResponse();

		// Basic information
		response.setId(user.getId());
		response.setLoginId(user.getLoginId());
		response.setName(user.getName());
		response.setEmail(user.getEmail());
		response.setAbout(user.getAbout());

		// Personal information
		response.setGender(user.getGender());
		response.setMaritalStatus(user.getMaritalStatus());

		// Address information
		response.setPlace(user.getPlace());
		response.setState(user.getState());
		response.setCountry(user.getCountry());

		// Mobile information
		response.setMobile(user.getMobile());
		response.setMobilecc(user.getMobilecc());

		// Birth details
		response.setDayBirth(user.getDayBirth());
		response.setMonthBirth(user.getMonthBirth());
		response.setYearBirth(user.getYearBirth());
		response.setHourBirth(user.getHourBirth());
		response.setMinuteBirth(user.getMinuteBirth());
		response.setSecondBirth(user.getSecondBirth());

		// Latitude details
		response.setLatitude(user.getLatitude());
		response.setLatDeg(user.getLatDeg());
		response.setLatMin(user.getLatMin());
		response.setLatNS(user.getLatNS());

		// Longitude details
		response.setLongitude(user.getLongitude());
		response.setLongDeg(user.getLongDeg());
		response.setLongMin(user.getLongMin());
		response.setLongEW(user.getLongEW());

		// Timezone
		response.setTimeZone(user.getTimeZone());

		// Verification
		response.setUserVerified(user.isUserVerified());

		return response;
	}
}