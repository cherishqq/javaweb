package com.javaweb.spring.annotation.webservice.client;



import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.javaweb.spring.annotation.webservice.IBookService;

public class BookServiceClient {

    private IBookService bookService;

    public void setWeatherService(IBookService bookService) {
        this.bookService = bookService;
    }

/*    public TemperatureInfo getTodayTemperature(String city) {
        List<Date> dates = Arrays.asList(new Date[] { new Date() });
        List<TemperatureInfo> temperatures =
            weatherService.getTemperatures(city, dates);
        return temperatures.get(0);
    }*/
}


