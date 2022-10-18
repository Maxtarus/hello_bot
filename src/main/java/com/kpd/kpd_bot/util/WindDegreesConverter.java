package com.kpd.kpd_bot.util;

public class WindDegreesConverter {
    private WindDegreesConverter() {}

    private static final String[] windDirections = {"С↑", "СВ↗", "В→", "ЮВ↘", "Ю↓", "ЮЗ↙", "З←", "СЗ↖"};

    public static String convertWindDegreesToDirection(int degrees) {
       int windDirectionIndex = (int)((degrees + 22.5) / 45 % 8);
       String direction = windDirections[windDirectionIndex];
       return direction;
    }
}
