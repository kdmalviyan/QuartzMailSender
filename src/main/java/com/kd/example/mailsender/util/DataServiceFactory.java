package com.kd.example.mailsender.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kd.example.mailsender.service.DBService;
import com.kd.example.mailsender.service.DataService;
import com.kd.example.mailsender.service.RemoteService;

/** Create employee service and return on the basis of availability. */
@Component
public class DataServiceFactory {

    @Autowired
    private RemoteService pivotService;

    @Autowired
    private DBService dbService;

    /** Create employee service and return on the basis of availability.
     * 
     * @return */
    public DataService getInstance() {
        if (pingPivot()) {
            return pivotService;
        } else {
            return dbService;
        }
    }

    /** Check Pivot Service is available of not.
     * 
     * @return */
    public static boolean pingPivot() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("<END POINT URL FOR GETTING EMPLOYEE DATA>", 8088), 10000);
            return true;
        } catch (IOException e) {
            return false; // Either timeout or unreachable or failed DNS lookup.
        }
    }
}
