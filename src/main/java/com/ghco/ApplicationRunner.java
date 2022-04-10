package com.ghco;

import com.ghco.model.TradeRecord;
import com.ghco.service.TradeProcessingService;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ApplicationRunner implements CommandLineRunner {

    private final TradeProcessingService service;

    @Override
    public void run(String... args) throws Exception {

        Scanner in = new Scanner(System.in);
        System.out.println("========================================");
        System.out.println("Welcome to Trade Aggregator Application.");

        System.out.println("Would you like to pass a file to application ? Y/N");
        String input = in.nextLine();

        CSVReader csvReader;
        String fileName;
        if ("Y".equalsIgnoreCase(input)) {
            System.out.println("Enter FileName : ");
            fileName = in.nextLine();
            System.out.println("Filename Entered :" + fileName);
            csvReader = new CSVReader(new FileReader(fileName));
        } else {
            System.out.println("Reading from trades.csv file from resources folder");
            fileName = "static/trades.csv";
            Reader reader = Files.newBufferedReader(Paths.get(
                    ClassLoader.getSystemResource(fileName).toURI()));


            csvReader = new CSVReader(reader);
        }
        List<TradeRecord> trades = new CsvToBeanBuilder(csvReader)
                .withType(TradeRecord.class)
                .build()
                .parse();

        System.out.println("Records read from file :" + fileName);

        System.out.println("Would you like to enter new records? Y/ N ");
        while("Y".equalsIgnoreCase(in.nextLine())){
            System.out.println("Please enter the record :\n");
            String record = in.nextLine();
            String[] recordArray = record.split(",");
            TradeRecord tradeRecord = TradeRecord.builder()
                    .tradeId(recordArray[0])
                    .bbgCode(recordArray[1])
                    .currency(recordArray[2])
                    .side(recordArray[3])
                    .price(recordArray[4])
                    .volume(recordArray[5])
                    .portfolio(recordArray[5])
                    .action(recordArray[6])
                    .account(recordArray[7])
                    .strategy(recordArray[8])
                    .user(recordArray[9])
                    .tradeTimeUtc(recordArray[10])
                    .valueDate(recordArray[11])
                    .build();
            System.out.println(tradeRecord);
            trades.add(tradeRecord);
            System.out.println("Enter another record?");
        }

       service.processTrades(trades);


    }
}
