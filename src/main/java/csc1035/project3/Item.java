package csc1035.project3;

public class Item {
    private int id;
    private String name;
    private boolean isPerishable;
    private float makeCost;
    private int stock;
    private float sellPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPerishable() {
        return isPerishable;
    }

    public void setPerishable(boolean perishable) {
        isPerishable = perishable;
    }

    public float getMakeCost() {
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

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }
}
