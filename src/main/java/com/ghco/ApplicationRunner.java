package com.ghco;

import com.ghco.model.TradeRecord;
import com.ghco.service.TradeProcessingService;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApplicationRunner implements CommandLineRunner {

    private final TradeProcessingService service;
    @Override
    public void run(String... args) throws Exception {

        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to Trade Aggregator Application.");
        String fileName = "static/trades1.csv";
     /*   System.out.print("Do you want to enter trade file? Y/N :");
        String response = in.next();

        if ("Y".equalsIgnoreCase(response)) {
            System.out.print("Enter trade file path: ");
            fileName = in.next();
            System.out.println();
            System.out.println("fileName entered is :" + fileName);
        }*/
        Reader reader = Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource(fileName).toURI()));

        CSVReader csvReader = new CSVReader(reader);
        List<TradeRecord> trades = new CsvToBeanBuilder(csvReader)
                .withType(TradeRecord.class)
                .build()
                .parse();

        //System.out.print(trades);
        System.out.print(trades.size());
        Set<String> set=trades.stream().map(TradeRecord::getAction).collect(Collectors.toSet());
        System.out.println(set);
        service.processTrades(trades);


    }
}
