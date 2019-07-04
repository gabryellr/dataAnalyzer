package com.gabryelrock.core.temaFinal.Model;
import java.util.List;

public class Sale {
    private int saleId;
    private String salesManName;
    private List<Item> item;
    private double salesAmount;

    public double getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(double salesAmount) {
        this.salesAmount = salesAmount;
    }

    public String getSalesManName() {
        return salesManName;
    }

    public void setSalesManName(String salesManName) {
        this.salesManName = salesManName;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", salesManName='" + salesManName + '\'' +
                ", item=" + item +
                ", salesAmount=" + salesAmount +
                '}';
    }
}
