package com.zaurtregulov.spring.rest;

import com.zaurtregulov.spring.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//с помощью объекта этого класса и его методов мы будем общаться с REST сервисом,
// то есть осуществлять Http запросы и получать ответы
@Component
public class Communication {
    private RestTemplate restTemplate;//бин используется для осуществления http request
    private final String URL = "http://localhost:8080/spring_course_rest/api/employees";

    @Autowired
    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Employee> getAllEmployees() {
        //обёртка http response
        //с помощью этого метода мы отправляем request и его результат получаем в response entity
        ResponseEntity<List<Employee>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null, //hhtp request, null - в тело ничего не добавляем
                        new ParameterizedTypeReference<List<Employee>>() {
                        });//вспомогательный класс цель которого передача generic типа
        List<Employee> allEmployees = responseEntity.getBody(); //получаем из responseEntity payLoad
        return allEmployees;
    }

    public Employee getEmployee(int id) {
        Employee employee = restTemplate.getForObject(URL + "/" + id, Employee.class);
        return employee;
    }

    public void saveEmployee(Employee employee) {
        //3.String - какого типа будет тело response
        //2.Что мы добавляем в тело метода POST
        int id = employee.getId();
        if (id == 0) {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, employee, String.class);//String - потому что тело response в виде json, а это String
            System.out.println("New employee was added to Database");
            System.out.println(responseEntity.getBody());//будет выводиться новоcозданный работник с его id
        } else {
            restTemplate.put(URL, employee); //если id не 0, то мы должны изменять существующего работника
            System.out.println("Employee with ID = " + id + " was updated");
        }

    }

    public void deleteEmployee(int id) {
        restTemplate.delete(URL + "/" + id);
        System.out.println("Employee with ID = " + id + " was deleted from database");
    }
}
