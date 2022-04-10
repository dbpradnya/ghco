package com.ghco.data;

import com.ghco.model.TradeRecord;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static List<TradeRecord> getTrades(){

        List<TradeRecord> ll=new ArrayList<>();
        TradeRecord record = TradeRecord.builder()
                .tradeId("trade1")
                .bbgCode("BC94")
                .currency("KRW")
                .side("B")
                .price("1694")
                .volume("133544")
                .portfolio("portfolio4")
                .action("NEW")
                .account("Account3")
                .strategy("Strategy 3")
                .user("User5")
                .build();
        TradeRecord record1 = TradeRecord.builder()
                .tradeId("trade2 ")
                .bbgCode("BC94")
                .currency("KRW")
                .side("B")
                .price("169")
                .volume("13544")
                .portfolio("portfolio4")
                .action("NEW")
                .account("Account3")
                .strategy("Strategy 3")
                .user("User5")
                .build();
        ll.add(record);
        ll.add(record1);

        return ll;
    }
}
