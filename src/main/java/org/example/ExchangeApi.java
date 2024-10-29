package org.example;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ExchangeApi {
    private Map<String, Double> currencies = new HashMap<>();

    public Map<String, Double> getCurrencies(){
        return currencies;
    }
    public ExchangeApi() {
        try {
            String url_str = "https://v6.exchangerate-api.com/v6/apikey/latest/USD";

            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod("GET");
            request.connect();


            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();
            JsonObject conversionRates = jsonobj.getAsJsonObject("conversion_rates");

            for (Map.Entry<String, JsonElement> entry : conversionRates.entrySet()) {
                String currency = entry.getKey();
                double rate = entry.getValue().getAsDouble();

                currencies.put(currency,rate);

                System.out.println("Currency: " + currency + ", Rate: " + rate);
            }


        } catch (Exception err) {
            System.out.println("Eror");
        }
    }
}
