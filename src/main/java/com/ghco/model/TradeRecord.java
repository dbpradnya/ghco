package com.ghco.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeRecord {

    private String tradeId;
    private String bbgCode;
    private String currency;
    private String side;
    private String price;
    private String volume;
    private String portfolio;
    private String action;
    private String account;
    private String strategy;
    private String user;
    private String tradeTimeUtc;
    private String valueDate;

}

