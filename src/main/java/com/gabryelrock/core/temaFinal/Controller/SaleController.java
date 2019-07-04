package com.gabryelrock.core.temaFinal.Controller;

import com.gabryelrock.core.temaFinal.Model.Item;
import com.gabryelrock.core.temaFinal.Model.Sale;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class SaleController {
    private List<Sale> sales = new ArrayList<>();

    public void newSale(String line) {
        List<Item> itens = new ArrayList<>();
        char separator = line.charAt(3);
        String[] textSplited = line.split(Character.toString(separator));
        Sale sale = new Sale();
        double salesAmount = 0;
        sale.setSaleId(Integer.parseInt(textSplited[1]));
        sale.setSalesManName(textSplited[3]);
        String removebracket = textSplited[2].substring(textSplited[2].indexOf("[") + 1, textSplited[2].indexOf("]"));
        String[] itemSplited = removebracket.split(",");
        Pattern pattern = Pattern.compile("([^\\.\\d])");

        for (int i = 0; i < itemSplited.length; i++) {

            String[] subItem = pattern.split(itemSplited[i]);
            Item item = new Item();
            item.setItemId(Integer.parseInt(subItem[0]));
            item.setItemQuantity(Integer.parseInt(subItem[1]));
            item.setItemPrice(Double.parseDouble(subItem[2]));
            salesAmount += Double.parseDouble(subItem[2]);

            itens.add(item);
        }
        sale.setItem(itens);
        sale.setSalesAmount(salesAmount);
        sales.add(sale);
    }

    public List<Sale> getSales() {
        return sales;
    }
}
