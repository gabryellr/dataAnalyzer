package com.gabryelrock.core.temaFinal;

import com.gabryelrock.core.temaFinal.Controller.CustomerController;
import com.gabryelrock.core.temaFinal.Controller.SaleController;
import com.gabryelrock.core.temaFinal.Controller.SalesmanController;
import com.gabryelrock.core.temaFinal.ErrorHandler.ErrorReadFile;
import com.gabryelrock.core.temaFinal.ErrorHandler.ErrorWriteFile;
import com.gabryelrock.core.temaFinal.Model.Item;
import com.gabryelrock.core.temaFinal.Model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
@Service
public class DatFile extends TemplateMethod {

    private List<Item> allItens = new ArrayList<>();

    @Autowired
    private CustomerController customerController;

    @Autowired
    private SalesmanController salesmanController;

    @Autowired
    private SaleController saleController;

    private final String SALES_MAN = "001";
    private final String CUSTOMER = "002";
    private final String SALE = "003";

    @Value("${name.file}" )
    private String fileName;

    @Value("${name.to.save.file}")
    private String nameToSaveFile;

    @Override
    boolean fileExist() {
        File file = new File(fileName);
        return file.exists() && file.canRead();
    }

    @Override
    List<String> readFile() {
        List<String> text;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            text = br.lines().collect(Collectors.toList());
        } catch (IOException erro) {
            throw new ErrorReadFile("There was an error reading the file");
        }
        return text;
    }

    @Override
    void processFile() {
        for (String line : readFile()) {
            char separator = line.charAt(3);
            String[] textSplited = line.split(Character.toString(separator));

            switch (textSplited[0]){
                case SALES_MAN:
                    salesmanController.newSalesman(line);
                   break;

                case CUSTOMER:
                   customerController.newCustomer(line);
                   break;

                case SALE:
                    saleController.newSale(line);
                    String removebracket = textSplited[2]
                            .substring(textSplited[2].indexOf("[") + 1, textSplited[2].indexOf("]"));

                    String[] itemSplited = removebracket.split(",");
                    Pattern pattern = Pattern.compile("([^\\.\\d])");

                    for (int i = 0; i < itemSplited.length; i++) {
                        Item item = new Item();
                        String[] subItem = pattern.split(itemSplited[i]);
                        item.setItemId(Integer.parseInt(subItem[0]));
                        item.setItemQuantity(Integer.parseInt(subItem[1]));
                        item.setItemPrice(Double.parseDouble(subItem[2]));
                        allItens.add(item);
                    }
                    break;
            }
        }
    }

    @Override
    boolean saveFile() {
        allItens.sort(Comparator.comparingDouble(Item::getItemPrice).reversed());
        saleController.getSales().sort(Comparator.comparingDouble(Sale::getSalesAmount));

        Optional<Sale> worstSalesman = saleController.getSales().stream().findFirst();
        Optional<Item> expensiveSale = allItens.stream().findFirst();

        List<Sale> expensiveSaleIdList = new ArrayList<>();

        saleController.getSales().forEach(sale -> {
            sale.getItem().forEach(item -> {
                if (item.getItemPrice() == expensiveSale.get().getItemPrice()) {
                    expensiveSaleIdList.add(sale);
                }
            });
        });

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Amount of clients: " + customerController.getCustomers().size());
        stringBuilder.append("\nAmount of salesman: " + salesmanController.getSalesMen().size());
        stringBuilder.append("\nID of the most expensive sale: " + expensiveSaleIdList.stream().findFirst().get().getSaleId());
        stringBuilder.append("\nWorst salesman ever: " + worstSalesman.get().getSalesManName());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nameToSaveFile))) {
            writer.write(stringBuilder.toString());
            System.out.println("file was created with success!");
            return true;

        } catch (Exception error) {
            throw  new ErrorWriteFile("There was an error writing the file");
        }
    }
}

