package com.ogp.dataanalytics.service;

import static org.junit.jupiter.api.Assertions.*;

import com.ogp.dataanalytics.model.DataPayload;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataAnalyticsServiceTest {

    private final DataAnalyticsService service = new DataAnalyticsService();

    List<DataPayload> input = new ArrayList<>();

    @BeforeEach
    void init() {
        input = List.of(
            new DataPayload("ds_001", "MOH", 1500, "2024-01"),
            new DataPayload("ds_002", "MOH", 3200, "2024-02"),
            new DataPayload("ds_003", "MOE", 100, "2024-03")
        );
    }

    // **Part 1:** Total downloads per agency, sorted descending
    @Test
    void getDownloadsPerAgencyTest() {
        // service method: parse downloads and returns it as a map
        LinkedHashMap<String, Integer> result = service.getDownloadsPerAgency(input);
        // get the items in order
        List<String> keys = new ArrayList<>(result.keySet());
        List<Integer> values = new ArrayList<>(result.values());

        assertEquals(List.of("MOH", "MOE"), keys);
        assertEquals(List.of(4700, 100), values);

        // works but roundabout
        // assertEquals(result.values().stream().findFirst().get(), 4700);
        // assertEquals(result.entrySet().stream().skip(1).findFirst().get().getValue(), 100);
    }

    // **Part 2:** Top dataset per agency (highest downloads), e.g. MOH: ds_002 (3200)
    @Test
    void getTopDatasetPerAgencyTest() {
        Map<String, DataPayload> result = service.getTopDatasetPerAgency(input);
        assertEquals("ds_002", result.get("MOH").getDatasetId());
        assertEquals(3200, result.get("MOH").getDownloads());

        assertEquals("ds_003", result.get("MOE").getDatasetId());
        assertEquals(100, result.get("MOE").getDownloads());
    }

    // **Part 3:** Filter by date range, return total downloads across all agencies:
    @Test
    void getTotalDownloadByDateRangeTest() {
        Integer result = service.getTotalDownloadByDateRange(input, "2024-01", "2024-02");
        assertEquals(result, 4700);
    }

    /* --- RED PHASE: define what the code should do, run and watch it fail --- */
    /* --- GREEN PHASE: write bare minimum to pass --- */
    /* --- REFACTOR PHASE: clean up, re-run to confirm still green --- */
}
