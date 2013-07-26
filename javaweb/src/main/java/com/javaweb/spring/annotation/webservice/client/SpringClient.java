package com.javaweb.spring.annotation.webservice.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceClient;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebServiceClient(
		name="SpringClient",
		targetNamespace="http://webservice.annotation.spring.javaweb.com",
		
		wsdlLocation="http://localhost:8080/javaweb/service/BookService?wsdl")
public class SpringClient  extends Service{

    protected SpringClient(URL wsdlDocumentLocation, QName serviceName) {
		super(wsdlDocumentLocation, serviceName);
	}

	public static void main(String[] args) {
        ApplicationContext context =
            new ClassPathXmlApplicationContext("client.xml");
        BookServiceClient client =
            (BookServiceClient) context.getBean("client");


    }
}