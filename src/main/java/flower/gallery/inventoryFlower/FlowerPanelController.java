package flower.gallery.inventoryFlower;

import com.jfoenix.controls.JFXDrawer;
import flower.gallery.flower.FlowerData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class FlowerPanelController {

    @FXML
    private Label headerLabel;

    @FXML
    public TextField flowerID_txtField;

    @FXML
    public TextField categoryID_txtField;

    @FXML
    public TextField name_txtField;

    @FXML
    public TextField color_txtField;

    @FXML
    public TextField origin_txtField;

    @FXML
    private TextField category_txtField;

    @FXML
    private TextField description_txtField;

    @FXML
    private JFXDrawer drawer;

    private NewInventoryController parentController;

    public void loadFlowerDetails(FlowerData flowerData) {
        flowerID_txtField.setText(String.valueOf(flowerData.getFlower_id()));
        categoryID_txtField.setText(String.valueOf(flowerData.getCategory_id()));
        name_txtField.setText(flowerData.getName());
        color_txtField.setText(flowerData.getColor());
        origin_txtField.setText(flowerData.getOrigin());
        category_txtField.setText(flowerData.getCategoryName());
        description_txtField.setText(flowerData.getFlower_description());

    }

    public void setDrawer(JFXDrawer drawer) {
        this.drawer = drawer;
    }

    public void setParentController(NewInventoryController parentController) {
        this.parentController = parentController;
    }

    public void cancel(ActionEvent actionEvent) {
        drawer.close();
    }
}


