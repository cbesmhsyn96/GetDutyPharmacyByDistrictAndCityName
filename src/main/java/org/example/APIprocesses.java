package org.example;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIprocesses{
    private static String baseURL ="https://api.collectapi.com/health/dutyPharmacy";
    private String ilce;
    private String il;
    HttpResponse<String> response;

    public void setilce(String ilce) {
        this.ilce = ilce;
    }


    public void setil(String il) {
        this.il = il;
    }

    public APIprocesses(String ilce, String il) throws IOException, InterruptedException {
        setilce(ilce);
        setil(il);
        HttpClient client = HttpClient.newBuilder().build();
        String formattedURL = String.format("%s?ilce=%s&il=%s",
                baseURL,
                URLEncoder.encode(ilce,"UTF-8"),
                URLEncoder.encode(il,"UTF-8"));
        URL urlLocation = new URL(formattedURL);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.valueOf(urlLocation)))
                .header("content-type", "application/json")
                .header("authorization", "[apikey]")
                .method("GET",HttpRequest.BodyPublishers.noBody())
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void getDutyPharmacyList() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response.body());
        JsonNode userNode = rootNode.path("result");
        System.out.println("------------------------------------------------------------------------");
        for (JsonNode resultNode : userNode){
            System.out.println("Eczane ismi :"+ resultNode.get("name"));
            System.out.println("İlçe        :"+ resultNode.get("dist"));
            System.out.println("Adres       :"+resultNode.get("address"));
            System.out.println("Telefon     :"+resultNode.get("phone"));
            System.out.println("------------------------------------------------------------------------");
        }
    }
}