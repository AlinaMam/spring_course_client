package com.zaurtregulov.spring.rest;

import com.zaurtregulov.spring.rest.configuration.MyConfig;
import com.zaurtregulov.spring.rest.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        //из нашего приложения Клиента с помощью rest api обращаемся к rest сервису посредством http запроса
        //и получаем результат этого запроса
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);
       /* List<Employee> allEmployees = communication.getAllEmployees();
        System.out.println(allEmployees);*/

        /*Employee empById = communication.getEmployee(2);
        System.out.println(empById);*/

        /*Employee emp = new Employee("Marina", "Marusheva", "Sales", 1300);
        emp.setId(12);
        communication.saveEmployee(emp);*/
        communication.deleteEmployee(12);


    }
}
