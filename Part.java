public class Part {
    String part;
    int quantity;
    double price;
    //getter
    public String getPart() {
        return part;
    }
    //constructor mutating all variables
    public Part(String part, int quantity, double price) {
        this.part = part;
        this.quantity = quantity;
        this.price = price;
    }
    //toString printing all variables
    public String toString(){
        return part + "," + quantity + "," + price;

    //print statement printing all variables
    }public void Print(){
        System.out.println("For the " + part + " part, there are " + quantity + " units, at the price of $" + price);
    }
}
