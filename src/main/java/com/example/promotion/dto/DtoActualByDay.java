package com.example.promotion.dto;

import java.util.Date;

public interface DtoActualByDay {
    Date getDate();

    Long getProductId();

    String getChainName();

    int getVolume();

    double getActualSalesValue();

}
