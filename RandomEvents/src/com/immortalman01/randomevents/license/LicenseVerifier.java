package com.immortalman01.randomevents.license;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Simple license verification via DevLeoko LicenseGate service.
 */
public class LicenseVerifier {

    private static final String API_KEY = "e9409d76-2006-455d-8c64-13b469845b64";
    private static final String VERIFY_URL = "https://license.leoko.dev/api/verify";

    /**
     * Checks the given license key against the LicenseGate API.
     *
     * @param plugin      the plugin requesting verification
     * @param licenseKey  the user provided license key
     * @return true if the license is valid
     */
    public static boolean verify(JavaPlugin plugin, String licenseKey) {
        try {
            String query = VERIFY_URL + "?plugin=" +
                    URLEncoder.encode(plugin.getName(), "UTF-8") +
                    "&key=" + URLEncoder.encode(licenseKey, "UTF-8") +
                    "&apiKey=" + URLEncoder.encode(API_KEY, "UTF-8");
            URL url = new URL(query);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            int code = conn.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK) {
                plugin.getLogger().warning("License server responded with code " + code);
                return false;
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line = reader.readLine();
                return line != null && line.trim().equalsIgnoreCase("VALID");
            }
        } catch (Exception e) {
            plugin.getLogger().severe("License verification failed: " + e.getMessage());
            return false;
        }
    }
}
