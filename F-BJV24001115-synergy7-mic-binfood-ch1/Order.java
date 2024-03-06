public class Order {
    private final MenuItem menuItem;
    private int qty;

    public Order(MenuItem m, int q) {
        menuItem = m;
        qty = q;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQty() {
        return qty;
    }

    public void addQty(int q) {
        qty += q;
    }
}
