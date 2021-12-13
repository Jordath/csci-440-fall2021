package edu.montana.csci.csci440.helpers;

import edu.montana.csci.csci440.model.Employee;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EmployeeHelper {

    public static String makeEmployeeTree() {
        // TODO, change this to use a single query operation to get all employees
        Employee employee = Employee.find(1); // root employee
        // and use this data structure to maintain reference information needed to build the tree structure
        Map<Long, List<Employee>> employeeMap = new HashMap<>();

        List<Employee> allEmployees = Employee.all();
        for (Employee currentEmployee : allEmployees) {
            Long reportsTo = currentEmployee.getReportsTo();
            List<Employee> employees = employeeMap.get(reportsTo);
            if(employees == null){
                employees = new LinkedList<>();
                employeeMap.put(reportsTo, employees);
            }
            employees.add(currentEmployee);

        }

        return "<ul>" + makeTree(employee, employeeMap) + "</ul>";
    }

    // TODO - currently this method just uses the employee.getReports() function, which
    //  issues a query.  Change that to use the employeeMap variable instead
    public static String makeTree(Employee employee, Map<Long, List<Employee>> employeeMap) {
        //List<Employee> employeeList = Employee.makeTreeHelper();
        String list = "<li><a href='/employees" + employee.getEmployeeId() + "'>"
                + employee.getEmail() + "</a><ul>";
        //List<Employee> reports = employee.getReports();
        String fullEmployeeListHTML = null;
//        for (Employee employee1 : employeeList) {
//            String list = "<li><a href='/employees" + employee1.getEmployeeId() + "'>"
//                    + employee1.getEmail() + "</a><ul>" + "</ul></li>";
//            fullEmployeeListHTML += list;
//            employeeMap.put(employee1.getEmployeeId(), employeeList);
//        }
        //employeeMap.put(employee.getEmployeeId(), employeeList.get(employee.getEmployeeId()))
        List<Employee> reports = employeeMap.get(employee.getEmployeeId());
        if(reports != null) {
            for (Employee report : reports) {
                list += makeTree(report, employeeMap);
            }
        }
//        for (Employee report : reports) {
//            list += makeTree(report, employeeMap);
//        }
        return list + "</ul></li>";
        //return fullEmployeeListHTML;
    }
}
