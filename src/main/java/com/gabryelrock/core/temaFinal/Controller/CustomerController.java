package com.gabryelrock.core.temaFinal.Controller;

import com.gabryelrock.core.temaFinal.Model.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerController {

    private List<Customer> customers = new ArrayList<>();

    public void newCustomer(String line){
        char separator = line.charAt(3);
        String[] textSplited = line.split(Character.toString(separator));
        Customer customer = new Customer();
        customer.setCnpj(textSplited[1]);
        customer.setName(textSplited[2]);
        customer.setBusinessArea(textSplited[3]);
        customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
