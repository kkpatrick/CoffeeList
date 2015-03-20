package com.xiaoqian.coffee.model;

/**
 * Created by abc on 3/19/15.
 */
public class Coffee {

    private String coffeeName, coffeeDesc, imageUrl;

    public Coffee() {
    }

    public Coffee(String name, String desc, String Url) {
        this.coffeeName = name;
        this.coffeeDesc = desc;
        this.imageUrl = Url;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public void setCoffeeDesc(String coffeeDesc) {
        this.coffeeDesc = coffeeDesc;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public String getCoffeeDesc() {
        return coffeeDesc;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
