package com.kpd.kpd_bot.message_services;

import com.kpd.kpd_bot.api.weather.WeatherAPI;
import com.kpd.kpd_bot.api.weather.model.Weather;
import com.kpd.kpd_bot.api.weather.model.WeatherMain;
import com.kpd.kpd_bot.api.weather.model.Wind;
import com.kpd.kpd_bot.dto.response.BaseQuoteResponseDTO;
import com.kpd.kpd_bot.dto.response.BaseWeatherResponseDTO;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;

@Service
@RequiredArgsConstructor
public class WeatherAdapter implements Adapter {

    private final WeatherAPI weatherAPI;
    @Override
    public String getTextFromMessageService() {
        BaseWeatherResponseDTO responseDTO = weatherAPI.getWeather();
        return this.formatFromObjectToText(responseDTO);
    }

    private String formatFromObjectToText(BaseWeatherResponseDTO dto) {
        StringBuilder sb = new StringBuilder();
        Weather weather = dto.getWeather().get(0);
        WeatherMain weatherMain = dto.getMain();
        Wind wind = dto.getWind();
        sb.append("Погода в ")
                .append(dto.getName()).append(":\n")
                .append(weather.getMain()).append("\n")
                .append(weather.getDescription()).append("\n")
                .append("Температура: ").append(weatherMain.getTemp()).append("°C\n")
                .append("Ощущается как: ").append(weatherMain.getFeels_like()).append("°C\n")
                .append("Мин. температура: ").append(weatherMain.getTemp_min()).append("°C\n")
                .append("Макс. температура: ").append(weatherMain.getTemp_max()).append("°C\n")
                .append("Давление: ").append(weatherMain.getPressure()).append("\n")
                .append(weatherMain.getHumidity()).append("\n")
                .append(wind.getSpeed()).append("\n")
                .append(wind.getDeg()).append("\n")
                .append(wind.getGust()).append("\n");
        return sb.toString();
    }
}
