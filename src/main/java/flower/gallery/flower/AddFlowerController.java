package flower.gallery.flower;

import com.jfoenix.controls.JFXDrawer;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class AddFlowerController {

    @FXML
    private HBox flower_name_hbox;

    @FXML
    private TextField flower_name_txtField;

    @FXML
    private HBox color_hbox;

    @FXML
    private TextField color_txtField;

    @FXML
    private HBox origin_hbox;

    @FXML
    private TextField origin_txtField1;

    @FXML
    private TextField id_txtField;

    @FXML
    private HBox categoryid_hbox;

    @FXML
    private TextField categoryid_txtField1;

    @FXML
    private HBox description_hbox;

    @FXML
    private TextArea description_txtField;

    @FXML
    private MFXButton submitBtn;

    @FXML
    private MFXButton cancelBtn;

    public void cancel(ActionEvent actionEvent) {
        drawer.close();
    }

    private MainFlowerController parentController;
    private JFXDrawer drawer;
    private boolean isAddingNew = false;

    public void setParentController(MainFlowerController thisController) {
        this.parentController = thisController;
    }

    public void setDrawer(JFXDrawer drawer) {
        this.drawer = drawer;
    }

    public void updateBook(ActionEvent actionEvent) {
        FlowerData flowerData = getEntry();
        if (isAddingNew) {
            FlowerManager.addSingleFlower(flowerData);
        } else {
            FlowerManager.updateFlower(flowerData);
        }
        submitBtn.setDisable(true);
        parentController.getFlowerData();
        drawer.close();

    }

    private FlowerData getEntry() {
        FlowerData e = new FlowerData(
                Integer.parseInt(id_txtField.getText()),
                Integer.parseInt(categoryid_txtField1.getText()),
                flower_name_txtField.getText(),
                color_txtField.getText(),
                origin_txtField1.getText(),
                description_txtField.getText()
        );

        return e;
    }


    public void setEntry(FlowerData flowerData) {
        resetForm();
        id_txtField.setText(String.valueOf(flowerData.getFlower_id()));
        categoryid_txtField1.setText(String.valueOf(flowerData.getCategory_id()));
        flower_name_txtField.setText(flowerData.getName());
        color_txtField.setText(flowerData.getColor());
        origin_txtField1.setText(flowerData.getOrigin());
        description_txtField.setText(flowerData.getFlower_description());
    }

    private void resetForm() {
        id_txtField.clear();
        categoryid_txtField1.clear();
        flower_name_txtField.clear();
        color_txtField.clear();
        origin_txtField1.clear();
        description_txtField.clear();
    }

    public void setAddingNew(boolean b) {
        this.isAddingNew = b;
        if(b){
            id_txtField.setEditable(true);
        }else{
            id_txtField.setEditable(false);
        }
    }

}

