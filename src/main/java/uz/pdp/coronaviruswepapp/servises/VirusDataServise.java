package uz.pdp.coronaviruswepapp.servises;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uz.pdp.coronaviruswepapp.models.LocationState;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service

public class VirusDataServise {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<LocationState> allLocationState = new ArrayList<>();

    public List<LocationState> getAllLocationState() {
        return allLocationState;
    }

    @Scheduled(cron = "* * 1 * * *")
    @PostConstruct
    public void fitchVirus() throws IOException, InterruptedException {
        List<LocationState> newStates = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL)).build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.body());
        StringReader cvsReader  = new StringReader(httpResponse.body());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(cvsReader);
        for (CSVRecord record : records) {
            LocationState locationState = new LocationState();
            locationState.setState(record.get("Province/State"));

            locationState.setCountry(record.get("Country/Region"));

            locationState.setLatestTotalCases(Integer.parseInt(record.get(record.size()-1)));
            newStates.add(locationState);
        }
        this.allLocationState=newStates;
    }
}
