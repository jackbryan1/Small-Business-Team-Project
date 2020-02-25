package csc1035.project3;

public class Item {
    private int id;
    private String name;
    private boolean isPerishable;
    private float makeCost;
    private int stock;
    private float sellPrice;

    public Item(int id, String name, boolean isPerishable, float makeCost, int stock, float sellPrice) {
        this.id = id;
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
