package csc1035.project3;

import javax.persistence.*;

@Entity
@Table(name = "Items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "isPerishable")
    private boolean isPerishable;
    @Column(name = "makeCost")
    private double makeCost;
    @Column(name = "stock")
    private int stock;
    @Column(name = "sellPrice")
    private double sellPrice;

    public Items(String name, boolean isPerishable, double makeCost, int stock, double sellPrice) {
        this.name = name;
        this.isPerishable = isPerishable;
        this.makeCost = makeCost;
        this.stock = stock;
        this.sellPrice = sellPrice;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isPerishable() {
        return isPerishable;
    }

    public double getMakeCost() {
        return makeCost;
    }

    public void setMakeCost(float makeCost) {
        this.makeCost = makeCost;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

}
