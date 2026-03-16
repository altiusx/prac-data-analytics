package com.ogp.dataanalytics;

import com.ogp.dataanalytics.model.DataPayload;
import com.ogp.dataanalytics.service.DataAnalyticsService;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        DataAnalyticsService service = new DataAnalyticsService();

        List<DataPayload> parsedData = service.processRawData();

        // Part 1
        System.out.println(service.getDownloadsPerAgency(parsedData));

        // Part 2
        System.out.println(service.getTopDatasetPerAgency(parsedData));

        // Part 3
        System.out.println(service.getTotalDownloadByDateRange(parsedData, "2024-01", "2024-03"));

        // add additional method calls here as the problem expands:
        // service.processOperations(parsedData);
        // service.printSummary();
    }
}
