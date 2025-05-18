package com.complaints.component;

import com.complaints.exception.NullResponseException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CountryIpExtractor {

    public String extractCountryIp(String remoteAddress) {
        ResponseEntity<String> ipCheckerResponse = new RestTemplate().getForEntity("http://ip-api.com/json/" + remoteAddress + "?fields=country", String.class);
        if (ipCheckerResponse.getBody() == null) {
            throw new NullResponseException("Response body is null for address: " + remoteAddress);
        }
        JsonObject jsonObject = JsonParser.parseString(ipCheckerResponse.getBody()).getAsJsonObject();
        return jsonObject.get("country") == null ? "UNRECOGNIZED" : jsonObject.get("country").getAsString();
    }
}
