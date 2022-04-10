package com.ghco.service;

import com.ghco.model.TradeCondition;
import com.ghco.model.TradeRecord;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TradeProcessingService {


    public void processTrades(List<TradeRecord> trades) {
        //
        System.out.println("bbgCode:portfolio"+" ======== "+"Currency"+" ======== "+"AggregatedVolume Of trades");
        System.out.println();
        Map<TradeCondition, Map<String, List<TradeRecord>>> tradesMap = getTradesMap(trades);

        Iterator<Map.Entry<TradeCondition, Map<String, List<TradeRecord>>>> tradesMapIterator = tradesMap.entrySet().iterator();
        Map<String, Double> map = new HashMap<>();
        while (tradesMapIterator.hasNext()) {
            Map.Entry<TradeCondition, Map<String, List<TradeRecord>>> tradesMapNext = tradesMapIterator.next();
            Map<String, List<TradeRecord>> actionMap = tradesMapNext.getValue();
            Iterator<Map.Entry<String, List<TradeRecord>>> actionListIterator = actionMap.entrySet().iterator();
            while (actionListIterator.hasNext()) {
                List<TradeRecord> tradeRecordList = actionListIterator.next().getValue();

                if (tradeRecordList.size() > 1) {

                    List<TradeRecord> onlyNewAndAmmend = tradeRecordList.stream().filter(x -> !Objects.equals(x.getAction(), "CANCEL")).collect(Collectors.toList());
                    onlyNewAndAmmend.stream().filter(x -> x.getAction().equalsIgnoreCase("AMEND"))
                            .forEach(x -> {
                                map.computeIfPresent(x.getCurrency(), (key, val) -> val + Double.parseDouble(x.getVolume()));
                                map.computeIfAbsent(x.getCurrency(), k-> Double.valueOf(x.getVolume()));
                            });
                } else {
                    TradeRecord tradeRecord = tradeRecordList.get(0);
                    // We consider this as NEW only because , AMEND and CANCEL can happen only after NEW. And its already handled in above condition
                    map.computeIfPresent(tradeRecord.getCurrency(), (key, val) -> val + Double.parseDouble(tradeRecord.getVolume()));
                    map.computeIfAbsent(tradeRecord.getCurrency(), k-> Double.valueOf(tradeRecord.getVolume()));
                }
            }
            map.forEach((key, value) -> System.out.println(tradesMapNext.getKey().getBbgCode()+":"+tradesMapNext.getKey().getPortfolio() + " ======== " + key + " ======= " + value));
            System.out.println("                    ====================             ");

            // bbgCode:Portfolio Currency AggregatedVolume

        }


    }

    private Map<TradeCondition, Map<String, List<TradeRecord>>> getTradesMap(List<TradeRecord> trades) {
        Map<TradeCondition, Map<String, List<TradeRecord>>> tradesMap = new HashMap<>();
        for (TradeRecord record : trades) {

            TradeCondition condition = TradeCondition.builder()
                    .bbgCode(record.getBbgCode())
                    .portfolio(record.getPortfolio())
                    .build();

            if (tradesMap.containsKey(condition)) {

                Map<String, List<TradeRecord>> map = tradesMap.get(condition);
                List<TradeRecord> tradeRecords = map.get(record.getTradeId());
                if (tradeRecords != null) {
                    tradeRecords.add(record);
                } else {
                    List<TradeRecord> tradeRecordList = new ArrayList<>();
                    tradeRecordList.add(record);
                    map.put(record.getTradeId(), tradeRecordList);
                }

            } else {
                Map<String, List<TradeRecord>> actionMap = new HashMap<>();
                List<TradeRecord> tradeRecordList = new ArrayList<>();
                tradeRecordList.add(record);
                actionMap.put(record.getTradeId(), tradeRecordList);
                tradesMap.put(condition
                        , actionMap);
            }

        }
        return tradesMap;
    }


}
