package com.javaweb.spring.annotation.webservice.client;

import org.apache.log4j.Logger;
/*import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;*/

import com.javaweb.spring.annotation.webservice.IBookService;


import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: eknv
 * Date: 25.05.2010
 * Time: 00:58:32
 * To change this template use File | Settings | File Templates.
 */
public class Client {

    private static Logger log = Logger.getLogger(Client.class);

/* Call the Web service
    *
    */

    public static void main(String[] args) {

        //Create a metadata of the service
/*        Service serviceModel = new ObjectServiceFactory().create(IBookService.class);
        log.debug("callSoapServiceLocal(): got service model.");

        //Create a proxy for the deployed service
        XFire xfire = XFireFactory.newInstance().getXFire();
        XFireProxyFactory factory = new XFireProxyFactory(xfire);*/

        String serviceUrl = "http://localhost:8080/javaweb/service/BookService";

        IBookService bookService = null;
/*        try {
       	bookService = (IBookService) factory.create(serviceModel, serviceUrl);
        	
        	log.debug("bookService:" + bookService.getBook());
           System.out.println( bookService.getBook()) ; 
        	
        } catch (MalformedURLException e) {
            log.error("Client.callWebService(): EXCEPTION: " + e.toString());
        }
*/
/*        //Invoke the service
        try {
            TemperatureInfo temperature = getTodayTemperature(weatherService, "Houston");
            System.out.println("Min temperature : " + temperature.getMin());
            System.out.println("Max temperature : " + temperature.getMax());
            System.out.println("Average temperature : " + temperature.getAverage());
        } catch (Exception e) {
            log.error("Client.callWebService(): EXCEPTION: " + e.toString());
        }*/
    }

/*    public static TemperatureInfo getTodayTemperature(WeatherService weatherService, String city) {
        List<Date> dates = Arrays.asList(new Date[]{new Date()});
        List<TemperatureInfo> temperatures =
                weatherService.getTemperatures(city, dates);
        return temperatures.get(0);
    }*/

}
