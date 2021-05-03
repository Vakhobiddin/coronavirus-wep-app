package uz.pdp.coronaviruswepapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationState {
    private String state;

    private String country;

    private Integer latestTotalCases;
}
