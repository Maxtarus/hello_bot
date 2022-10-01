package com.kpd.kpd_bot.service;

import com.kpd.kpd_bot.entity.ExchangeRatesSetting;
import com.kpd.kpd_bot.entity.Subscription;
import com.kpd.kpd_bot.entity.UserSetting;
import com.kpd.kpd_bot.entity.UserInfo;
import com.kpd.kpd_bot.jpa.SettingRepository;
import com.kpd.kpd_bot.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	private final SettingService settingService;
	private final SubscriptionService subscriptionService;
	private final ExchangeRatesSettingService exchangeRatesSettingService;

	public UserInfo saveNewUser(User user) {
		UserSetting setting = settingService.saveNewSetting();
		Subscription subscription = subscriptionService.saveNewSubscription();
		ExchangeRatesSetting exchangeRatesSetting = exchangeRatesSettingService.saveNewExchangeRatesSetting();
		UserInfo newUser = new UserInfo(user.getId(), user.getUserName(), setting, subscription, exchangeRatesSetting);
		return userRepository.save(newUser);
	}

	public boolean existUser(Long userId) {
		return userRepository.existsById(userId);
	}

}
