package model;

public class Product {
    private String name;
    private String description;
    private String price;

    public Product(String name,String description ,String price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(){

    }

    public String getName(){
        return name;
    }

    public Product withName(String name){
        this.name = name;
        return this;
    }

    public String getDescription(){
        return description;
    }

    public Product withDescription(String description){
        this.description = description;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public Product withPrice(String price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
