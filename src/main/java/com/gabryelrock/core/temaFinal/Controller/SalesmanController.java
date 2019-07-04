package com.gabryelrock.core.temaFinal.Controller;

import com.gabryelrock.core.temaFinal.Model.Salesman;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SalesmanController {

    private List<Salesman> salesMen = new ArrayList<>();

    public void newSalesman(String line){
        char separator = line.charAt(3);
        String[] textSplited = line.split(Character.toString(separator));
        Salesman salesman = new Salesman();
        salesman.setCpf(textSplited[1]);
        salesman.setName(textSplited[2]);
        salesman.setSalary(Double.parseDouble(textSplited[3]));
        salesMen.add(salesman);
    }

    public List<Salesman> getSalesMen() {
        return salesMen;
    }
}
