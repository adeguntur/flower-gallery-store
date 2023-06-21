package flower.gallery.inventoryFlower;

import com.jfoenix.controls.JFXDrawer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditInventoryController {

    @FXML
    private JFXDrawer drawer;

    @FXML
    private TextField txtInvId;

    @FXML
    public TextField txtFlowerId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtMin;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button submitBtn;

    private InventoryManager inventoryManager;

    private NewInventoryController parentController;

    private boolean isAddingNew = false;


    public void setTextFields(int inv_id ,int flowerid,int price,int qty,int min){
        txtFlowerId.setText(String.valueOf(flowerid));
        txtPrice.setText(String.valueOf(price));
        txtQty.setText(String.valueOf(qty));
        txtMin.setText(String.valueOf(min));
    }

    public void clearTextFields(){
        txtFlowerId.clear();
        txtPrice.setText("");
        txtQty.setText("");
        txtMin.setText("");
    }

    public StockEntry getStockEntryFromForm(){
        int flower_id = Integer.parseInt(txtFlowerId.getText());
        int price = Integer.parseInt(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        int min = Integer.parseInt(txtMin.getText());

        return new StockEntry(0,flower_id,price,qty,min);
    }

    public void cancel(ActionEvent actionEvent) {
        drawer.close();
    }

    public void setDrawer(JFXDrawer drawer) {
        this.drawer = drawer;
    }

    public void setParentController(NewInventoryController parentController) {
        this.parentController = parentController;
    }

    /*public void setParentController(InventoryController parentController) {
        this.parentController = parentController;
    }*/

    public void setInventoryManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public void setAddingNew(boolean addingNew) {
        isAddingNew = addingNew;
//        txtInvId.setDisable(false);
        txtFlowerId.setEditable(true);
    }

    public void updateInventory(ActionEvent actionEvent) {
        if(!isAddingNew){
            System.out.println(getStockEntryFromForm().toString());
            inventoryManager.updateSingleEntry(getStockEntryFromForm());
            clearTextFields();
            drawer.close();
            parentController.loadTableData();
        }else{
            inventoryManager.addSingleEntry(getStockEntryFromForm());
            setAddingNew(false);
            clearTextFields();
            drawer.close();
            parentController.loadTableData();
        }
    }

    public void setEntry(StockEntry entry) {
        setTextFields(
                entry.getInv_id(),
                entry.getFlower_id(),
                entry.getList_price(),
                entry.getQty(),
                entry.getMin_qty()
                );
    }
}
