package com.ogp.dataanalytics.model;

public class DataPayload {

    private String datasetId;
    private String agency;
    private int downloads;
    private String period;

    public DataPayload() {}

    public DataPayload(String datasetId, String agency, int downloads, String period) {
        this.datasetId = datasetId;
        this.agency = agency;
        this.downloads = downloads;
        this.period = period;
    }

    public String getDatasetId() {
        return datasetId;
    }

    public String getAgency() {
        return agency;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public String getPeriod() {
        return period;
    }

    // to print result
    @Override
    public String toString() {
        return datasetId + "(" + downloads + ")";
    }
}
