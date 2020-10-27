package com.BokingSystem;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Currency;

public class Converter {
    private static Currency euro = Currency.getInstance("EUR");
    private static Currency krona = Currency.getInstance("SEK");

    public static int getURLStatus() {
        int status = 0;
        try {
            URL url = new URL("https://v6.exchangerate-api.com/v6/920e068564319991b01fec7d/latest/EUR");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            status = connection.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * The method fetches the current exchange rates from fixer.io API
     * @param from - the currency that we want to change
     * @param to - the  currency that we want to change to
     */
    public static double convertEURtoSEK(Currency from, Currency to, double amount) {
        if (getURLStatus() != 200) {
            throw new RuntimeException("HttpResponse Code: " + getURLStatus());
        } else {
            try {
                URL url = new URL("https://data.fixer.io/api/convert?" +
                        "access_key=f6e761000395f28c832dd764e7f28e17" +
                        "&from=" + from.getCurrencyCode() +
                        "&to=" + to.getCurrencyCode() +
                        "&amount=" + amount +
                        "&format=1");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader((InputStream) connection.getContent()));
                connection.disconnect();

                return convertToDouble(jsonObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0.0;
    }

    private static double convertToDouble(JSONObject object) {
        return (Double) object.get("result");
    }
}
