package com.javaweb.platform.outerservice.rmi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.javaweb.web.UserService;
import com.platform.outerservice.rmi.RemoteCacheService;
import com.platform.sys.SystemInitializer;



//@ContextConfiguration(locations = {"classpath:spring-rmi.xml"})
//@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
@ContextConfiguration(locations = {"classpath:spring-rmi-client.xml"})
public class RmiClientTest extends AbstractJUnit4SpringContextTests{
	
	private static final Logger log = LoggerFactory.getLogger(RmiClientTest.class);


	@Autowired
	@Qualifier("rmiClientQualifier")
	private RemoteCacheService rmiClient;
	
	@Before
	public void setUp(){
		//System.setProperty("java.rmi.server.hostname" , "192.168.1.196");
	}
  
	
	@Test
    public void simpleTest() {
        log.info("This is a simple log message....");

        final int THREADS = 10;
        
        // 测试可以得到service.. 还要测试是否可以得到
        SystemInitializer sysInit = rmiClient.getSystemInitializer();
        
        
        System.out.println( sysInit.getMessage()  );
        
        
        ApplicationContext app =  sysInit.getApp();
        
        System.out.println("app:" + app);
        
        UserService us = (UserService)app.getBean("userService");
        System.out.println("......" + us.getString());

/*        Thread[] threads = new Thread[THREADS];
        for (int j = 0; j < threads.length; j++) {
            threads[j] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        String result = rmiClient.getMessage();
                        Assert.assertEquals(result, "xxoo");
                        log.info("I've got a response!!!");
                    }
                }
            });

            threads[j].start();
        }

        log.debug("Joining...");
        for (int j = 0; j < threads.length; j++) {
            try {
                threads[j].join();
                log.info("Thread {} has been joined successfully", j);
            } catch (InterruptedException e) {
                log.warn("An error was handled during joining of thread {}", j);
            }
        }*/
    }
	

}
