package com.kd.example.mailsender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kd.example.mailsender.util.DataServiceFactory;

@Service
public class EmployeeService {
    @Autowired
    private DataServiceFactory dataServiceFactory;

    public DataService getDataService() {
        return dataServiceFactory.getInstance();
    }
}
