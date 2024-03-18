public class MenuItem {
    private final String name;
    private final int price;

    public MenuItem(String n, int p) {
        name = n;
        price = p;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
