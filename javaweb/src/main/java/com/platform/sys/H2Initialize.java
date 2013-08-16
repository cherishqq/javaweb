package com.platform.sys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;

import org.h2.tools.Server;
import org.springframework.stereotype.Component;

/**
 * 
 * @author derek
 * @createtime: 2013-8-14 下午3:31:12 
 * @version  初始化H2 数据库
 *  desc: 
 *  web server： http://localhost:9095
 *  tcp server: http://localhost:9094
 *  
 */

//@Component
public class H2Initialize {
	
	private Server tcpServer;  
	private Server webServer;  
	
    private String tcpPort = "9094"; 
    private String webPort = "9095"; 
    
    
    private String dbDir = "./h2db/sample";   
    private String user = "root";   
    private String password = "root";   
  
   // @PostConstruct
    public void startServer() {   
        try {   
            System.err.print("正在启动h2...");  
            tcpServer = Server.createTcpServer(   
                    new String[] { "-tcpPort", tcpPort }).start();
            webServer = Server.createWebServer(   
            		new String[] { "-webPort", webPort }).start();
        } catch (SQLException e) {   
            System.out.println("启动h2出错：" + e.toString());   
            e.printStackTrace();   
            throw new RuntimeException(e);   
        }   
        System.out.println("启动h2数据库成功...");  
    }   
  
    public void stopServer() {   
        if (tcpServer != null) {   
            System.out.println("正在关闭h2...");   
            tcpServer.stop();   
            System.out.println("关闭成功.");   
        }  
        if (webServer != null) {   
            System.out.println("正在关闭h2...");   
            webServer.stop();   
            System.out.println("关闭成功.");   
        }  
    }   
  
    public void useH2() {   
        try {   
            Class.forName("org.h2.Driver");   
            Connection conn = DriverManager.getConnection("jdbc:h2:" + dbDir,   
                    user, password);   
            Statement stat = conn.createStatement();   
            // insert data   
            stat.execute("drop TABLE TEST");   
            stat.execute("CREATE TABLE TEST(NAME VARCHAR)");   
            stat.execute("INSERT INTO TEST VALUES('Hello World')");   
  
            // use data   
            ResultSet result = stat.executeQuery("select name from test ");   
            int i = 1;   
            while (result.next()) {   
                System.out.println(i++ + ":" + result.getString("name"));   
            }   
            result.close();   
            stat.close();   
            conn.close();   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
    }   
  
    public static void main(String[] args) {   
    	H2Initialize h2 = new H2Initialize();   
        h2.startServer();
       h2.useH2();   
        h2.stopServer();   
    //    System.out.println("==END==");   
    }   
	

}
