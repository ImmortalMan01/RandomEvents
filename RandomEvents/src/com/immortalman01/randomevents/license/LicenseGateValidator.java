package com.immortalman01.randomevents.license;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.bukkit.Bukkit;

/**
 * Simple license validator using DevLeoko's LicenseGate API.
 */
public class LicenseGateValidator {
    private final String apiKey;

    public LicenseGateValidator(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Attempts to validate the plugin license.
     *
     * @return true if the license is valid
     */
    public boolean validate() {
        HttpURLConnection connection = null;
        try {
            String hwid = Bukkit.getServer().getIp();
            if (hwid == null || hwid.isEmpty()) {
                hwid = "unknown";
            }
            URL url = new URL("https://license.leoko.de/api/validate.php?key=" + apiKey + "&hwid=" + hwid);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setDoInput(true);
            int code = connection.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK) {
                return false;
            }
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String response = reader.readLine();
                return response != null && response.trim().equalsIgnoreCase("VALID");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
