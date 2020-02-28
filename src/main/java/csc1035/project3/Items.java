package csc1035.project3;

import javax.persistence.*;

@Entity
@Table(name = "Items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "category")
    private String category;
    @Column(name = "isPerishable")
    private boolean isPerishable;
    @Column(name = "makeCost")
    private double makeCost;
    @Column(name = "stock")
    private int stock;
    @Column(name = "sellPrice")
    private double sellPrice;

    public Items(String name, String category, boolean isPerishable, double makeCost, int stock, double sellPrice) {
        this.name = name;
        this.category = category;
        this.isPerishable = isPerishable;
        this.makeCost = makeCost;
        this.stock = stock;
        this.sellPrice = sellPrice;
    }

    public Items() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isPerishable() {
        return isPerishable;
    }

    public double getMakeCost() {
        return makeCost;
    }

    public void setMakeCost(double makeCost) {
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

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

}
