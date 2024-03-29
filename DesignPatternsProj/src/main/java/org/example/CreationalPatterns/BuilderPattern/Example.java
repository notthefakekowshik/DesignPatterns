package org.example.CreationalPatterns.BuilderPattern;
class Pizza {
    private String dough;
    private String sauce;
    private String topping;

    public void setDough(String dough) {
        this.dough = dough;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "dough='" + dough + '\'' +
                ", sauce='" + sauce + '\'' +
                ", topping='" + topping + '\'' +
                '}';
    }
}

class PizzaBuilder {
    private Pizza pizza;

    public PizzaBuilder() {
        this.pizza = new Pizza();
    }

    public PizzaBuilder withDough(String dough) {
        pizza.setDough(dough);
        return this;
    }

    public PizzaBuilder withSauce(String sauce) {
        pizza.setSauce(sauce);
        return this;
    }

    public PizzaBuilder withTopping(String topping) {
        pizza.setTopping(topping);
        return this;
    }

    public Pizza build() {
        return pizza;
    }
}

public class Example {
    public static void main(String[] args) {
        Pizza pizza = new PizzaBuilder()
                .withDough("Thin crust")
                .withSauce("Tomato")
                .withTopping("Cheese")
                .build();

        System.out.println(pizza);
    }
}
/*
 one key thing to observe is, in the builder pattern we return the same type of that class for get/set methods.

 (Note For WFGuys :
    There is a class called SimpleCriteriaBuilder.java in CT source code. You can refer that for better clarity.
  )


 */