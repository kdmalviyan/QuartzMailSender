package com.kd.example.mailsender.service;

import java.util.List;

import com.kd.example.mailsender.beans.Employee;
import com.kd.example.mailsender.beans.Filter;

public interface DataService {
    public List<Employee> getEmployees(Filter filter);
}
