# Project

Scenario: Dataset Analytics

Agencies upload datasets. You need to analyse download activity.

```
[
{ "datasetId": "ds_001", "agency": "MOH", "downloads": 1500, "period": "2024-01" },
{ "datasetId": "ds_002", "agency": "MOH", "downloads": 3200, "period": "2024-02" },
{ "datasetId": "ds_003", "agency": "MOE", "downloads": 800, "period": "2024-01" },
{ "datasetId": "ds_004", "agency": "MOM", "downloads": 2100, "period": "2024-01" },
{ "datasetId": "ds_005", "agency": "MOE", "downloads": 950, "period": "2024-02" },
{ "datasetId": "ds_006", "agency": "MOH", "downloads": 410, "period": "2024-03" }
]

```

**Part 1:**

Total downloads per agency, sorted descending:

```

MOH: 5110
MOM: 2100
MOE: 1750

```

**Part 2:**

Top dataset per agency (highest downloads):

```

MOH: ds_002 (3200)
MOM: ds_004 (2100)
MOE: ds_005 (950)

```

**Part 3:**

Filter by date range, return total downloads across all agencies:

````

getTotal("2024-01", "2024-02") → 8550

```



## Run the app

```bash
mvn compile exec:java
````

## Run tests

```bash
mvn test
```

---
