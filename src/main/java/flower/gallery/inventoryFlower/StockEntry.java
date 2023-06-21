package flower.gallery.inventoryFlower;

public class StockEntry {
    private int inv_id;
    private int flower_id;
    private String name;
    private int list_price;
    private int qty;
    private int min_qty;


    public StockEntry(int inv_id, int flower_id, int list_price, int qty, int min_qty) {
        this.inv_id = inv_id;
        this.flower_id = flower_id;
        this.list_price = list_price;
        this.qty = qty;
        this.min_qty = min_qty;
    }

    public StockEntry(int flower_id, String name, int list_price, int qty, int min_qty) {
        setFlower_id(flower_id);
        setName(name);
        setList_price(list_price);
        setQty(qty);
        setMin_qty(min_qty);
    }

    public int getInv_id() {
        return inv_id;
    }

    public void setInv_id(int inv_id) {
        this.inv_id = inv_id;
    }

    public int getFlower_id() {
        return flower_id;
    }

    public void setFlower_id(int flower_id) {
        this.flower_id = flower_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getList_price() {
        return list_price;
    }

    public void setList_price(int list_price) {
        this.list_price = list_price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getMin_qty() {
        return min_qty;
    }

    public void setMin_qty(int min_qty) {
        this.min_qty = min_qty;
    }

    @Override
    public String toString() {
        return "StockEntry{" +
                "inv_id=" + inv_id +
                ", flower_id=" + flower_id +
                ", name='" + name + '\'' +
                ", list_price=" + list_price +
                ", qty=" + qty +
                ", min_qty=" + min_qty +
                '}';
    }
}
