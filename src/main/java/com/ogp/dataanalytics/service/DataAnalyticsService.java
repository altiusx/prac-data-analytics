package com.ogp.dataanalytics.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ogp.dataanalytics.model.DataPayload;
import java.io.File;
import java.io.IOException;
import java.time.YearMonth;
import java.util.*;

public class DataAnalyticsService {

    public List<DataPayload> processRawData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
            new File("src/main/resources/input.json"),
            new TypeReference<List<DataPayload>>() {}
        );
    }

    // **Part 1:** Total downloads per agency, sorted descending
    public LinkedHashMap<String, Integer> getDownloadsPerAgency(List<DataPayload> dataset) {
        Map<String, Integer> mappedResult = getDownloadMap(dataset);
        LinkedHashMap<String, Integer> sorted = new LinkedHashMap<>();

        // linked hashmap will preserve the sorted order
        mappedResult
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .forEach(e -> sorted.put(e.getKey(), e.getValue()));
        return sorted;

        // if you just want one Map (no getDownloadMap)
        // dataset.stream()
        // .collect(Collectors.groupingBy(d -> d.agency, Collectors.summingInt(d -> d.downloads)))
        // .entrySet().stream()
        // .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        // .forEach(e -> downloadsMap.put(e.getKey(), e.getValue()));
    }

    private Map<String, Integer> getDownloadMap(List<DataPayload> dataset) {
        Map<String, Integer> downloadsMap = new HashMap<>();
        for (DataPayload data : dataset) {
            String agency = data.getAgency();
            Integer downloads = data.getDownloads();
            downloadsMap.merge(agency, downloads, Integer::sum);
        }
        return downloadsMap;
    }

    // **Part 2:** Top dataset per agency (highest downloads), e.g. MOH: ds_002 (3200)
    public Map<String, DataPayload> getTopDatasetPerAgency(List<DataPayload> dataset) {
        Map<String, DataPayload> output = new HashMap<>();
        for (DataPayload data : dataset) {
            // if the key-value pair does not exist yet, add it to the map
            // else if the data's download count > current key-value pair's, replace it
            if (!output.containsKey(data.getAgency())) {
                output.put(data.getAgency(), data);
            } else if (data.getDownloads() > output.get(data.getAgency()).getDownloads()) {
                output.put(data.getAgency(), data);
            }
        }
        return output;
    }

    // **Part 3:** Filter by date range, return total downloads across all agencies:
    public Integer getTotalDownloadByDateRange(
        List<DataPayload> dataset,
        String startDate,
        String endDate
    ) {
        Integer totalDownloads = 0;
        YearMonth start = YearMonth.parse(startDate);
        YearMonth end = YearMonth.parse(endDate);

        for (DataPayload data : dataset) {
            YearMonth dataDate = YearMonth.parse(data.getPeriod());
            // to filter within range: check if data is dated !before start and !after end
            if (!dataDate.isBefore(start) && !dataDate.isAfter(end)) {
                totalDownloads += data.getDownloads();
            }
        }
        return totalDownloads;
    }
}
