package com.ghco.service;


import com.ghco.data.TestData;
import com.ghco.model.TradeRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TradeProcessingServiceTest {

    TradeProcessingService service=new TradeProcessingService();

    @Test
    public void runTest() {
        service.processTrades(TestData.getTrades());
    }

    @Test
    public void runTestWithDifferentActions(){

        List<TradeRecord> tradeRecordList=TestData.getTrades();
        tradeRecordList.add(TradeRecord.builder()
                .tradeId("trade1")
                .bbgCode("BC94")
                .currency("KRW")
                .side("B")
                .price("1694")
                .volume("13")
                .portfolio("portfolio4")
                .action("AMEND")
                .account("Account3")
                .strategy("Strategy 3")
                .user("User5")
                .build());

        service.processTrades(tradeRecordList);
    }
}
