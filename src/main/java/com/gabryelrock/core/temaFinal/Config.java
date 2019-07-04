package com.gabryelrock.core.temaFinal;

import com.gabryelrock.core.temaFinal.Controller.CustomerController;
import com.gabryelrock.core.temaFinal.Controller.SaleController;
import com.gabryelrock.core.temaFinal.Controller.SalesmanController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.gabryelrock.core.temaFinal")
public class Config {

    @Bean
    public CustomerController customerController(){
        return new CustomerController();
    }

    @Bean
    public SaleController saleController(){
        return new SaleController();
    }

    @Bean
    public SalesmanController salesmanController(){
        return new SalesmanController();
    }

    @Bean
    public DatFile datFile(){
        return new DatFile();
    }

}
