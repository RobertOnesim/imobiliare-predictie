package ro.uaic.info.data_mining.aggregation;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationCoefficientCalculator {

    private List<Construction> constructions;
    private Construction.Parameter parametersOfInterest;
    private Map<String, List<Construction>> aggregateViaZone;

    public LocationCoefficientCalculator(@NotNull List<Construction> constructions, Construction.Parameter parameters) {
        this.constructions = constructions;
        this.parametersOfInterest = parameters;
    }

   public double getZoneCoefficient(@NotNull LocationCoefficientRequest myRequest) {

        double sumOfPricesByZone = 0;
        double sumOfAllPrices = 0;
        int numberOfConstructionsByZone = 0;
        int numberOfConstructionsByCity = 0;

        Map<String, List<Construction>> aggregateViaZone = new HashMap<>();
        List<Construction> constructionsInZone = new ArrayList<>();

        for (Construction construction : constructions) {
            if (construction.getParameter(Construction.Parameter.Zone).equals(myRequest.getZone())) {
                constructionsInZone.add(construction);
                sumOfPricesByZone += (double) construction.getParameter(Construction.Parameter.Price);
                numberOfConstructionsByZone++;
            }
            sumOfAllPrices += (double) construction.getParameter(Construction.Parameter.Price);
            numberOfConstructionsByCity++;
        }

        aggregateViaZone.put(myRequest.getZone(), constructionsInZone);
        this.aggregateViaZone = aggregateViaZone;

        return (sumOfPricesByZone / numberOfConstructionsByZone) / (sumOfAllPrices / numberOfConstructionsByCity);
    }
}
