package flower.gallery.flower;

public class FlowerData {

    private int flower_id;
    private int category_id;
    private String name;
    private String color;
    private String origin;
    private String flower_description;
    private String categoryName;

    public FlowerData(int flower_id, int category_id, String name, String color, String origin, String flower_description) {
        this.flower_id = flower_id;
        this.category_id = category_id;
        this.name = name;
        this.color = color;
        this.origin = origin;
        this.flower_description = flower_description;
    }

    public FlowerData(int flower_id, int category_id, String name, String color, String origin, String flower_description,String categoryName) {
        this.flower_id = flower_id;
        this.category_id = category_id;
        this.name = name;
        this.color = color;
        this.origin = origin;
        this.flower_description = flower_description;
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getFlower_id() {
        return flower_id;
    }

    public void setFlower_id(int flower_id) {
        this.flower_id = flower_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getFlower_description() {
        return flower_description;
    }

    public void setFlower_description(String flower_description) {
        this.flower_description = flower_description;
    }

    @Override
    public String toString() {
        return "FlowerData{" +
                "flower_id=" + flower_id +
                ", category_id=" + category_id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", origin='" + origin + '\'' +
                ", flower_description='" + flower_description + '\'' +
                '}';
    }

}
