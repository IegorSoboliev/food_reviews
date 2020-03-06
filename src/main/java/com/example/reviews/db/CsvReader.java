package com.example.reviews.db;


import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

@Component
public class CsvReader {
    private static final String FILENAME = "src/main/resources/Reviews.csv";

    public List<CSVRecord> readAll() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(FILENAME));
        List<CSVRecord> records = CSVFormat.
                EXCEL
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim()
                .parse(reader)
                .getRecords();
        reader.close();
        return records;
    }
}
