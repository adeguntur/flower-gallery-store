package flower.gallery.Bill;

import flower.gallery.Bill.data.BillItem;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BillItemController implements Initializable {

    @FXML
    private TableView<BillItem> itemTable;
    @FXML
    private TableColumn<BillItem, Integer> itemId;

    @FXML
    private TableColumn<BillItem, String> itemName;

    @FXML
    private TableColumn<BillItem, String>  isbn;

    @FXML
    private TableColumn<BillItem, Integer>  unit_price;
    @FXML
    private TableColumn<BillItem, Integer> activeQty;
    @FXML
    private TableColumn addItem;

    @FXML
    private Label name;

    @FXML
    private MFXTextField searchItem;

    @FXML
    private MFXButton search;


    private ItemManager ItemManager ;
    private CustomerManager customerManager ;

    private BillingController parentController;

    private ObservableList<BillItem> itemObservableList = FXCollections.observableArrayList();
    private FilteredList<BillItem> itemFilteredList = new FilteredList<>(itemObservableList);
    private BillItemController thisControl = this;

    @Override
    public void initialize(URL location , ResourceBundle resources) {
        searchItem.setIcon(new MFXIconWrapper(new MFXFontIcon("mfx-search", 28, Color.web("#4D4D4D")), 24));
        try {
            ItemManager = new ItemManager();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        customerManager = new CustomerManager();
        buttons();
        setColumns();
        loadDataItemTable();


    }

    public void loadDataItemTable() {
        ArrayList<BillItem> ItemList = ItemManager.getItemList();
       itemTable.setItems(itemObservableList);
        itemObservableList.clear();

        for (BillItem currentItem : ItemList) {
            itemObservableList.add(currentItem);

        }
    }
//    public void subQty(int book_id , ArrayList<BillItem> itemList){
//
//        for(BillItem item: itemList){
//            if(item.getItem_id() == book_id){
//                item.setActiveQty(item.getActiveQty() - 1 );
//            }
//        }
//
//    }
    public void setParentController(BillingController parentController) {
        this.parentController = parentController;
    }
    private void setColumns() {

        itemId.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        itemName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        unit_price.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        activeQty.setCellValueFactory(new PropertyValueFactory<>("activeQty"));


     addItem.setCellFactory((tableColumn) -> {
        TableCell<BillItem, Integer> tableCell = new TableCell<>() {
//            javafx.scene.image.Image imgSelect = new Image(getClass().getResourceAsStream("/images/book.png"));
            final javafx.scene.control.Button btnAdd = new Button();
            FontIcon icon3 = new FontIcon("anto-plus-circle");


            @Override
            protected void updateItem(Integer customer_id, boolean empty) {
                super.updateItem(customer_id, empty);

                if(empty)
                {
                    this.setText(null);
                    this.setGraphic(null);
                }
                else{

                    btnAdd.setOnAction(e ->{
                        System.out.println("Clicked Select");
                        BillItem entry = getTableView().getItems().get(getIndex());

                        loadDataItemTable();
//                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/Billing.fxml"));
//
//                        BillingController controller = loader.getController() ;
//                        controller.setParentController(thisControl);


                        parentController.getOrderDetails(entry.getItem_id(), entry.getItem_name() , entry.getUnit_price());
                        parentController.loadBillTable();

                    });


                    btnAdd.setStyle(" -fx-background-color: #2A2A2A;\n" +
                            "\n" +
                            "    -fx-background-color: #2A2A2A;\n" +
                            "    -fx-background-radius: 9,8,5,4,3;\n" +
                            "\n" +
                            "    -fx-font-size: 13px;\n" +
                            "\n" +
                            "    -fx-text-fill: #fff;\n" +
                            "    -fx-alignment: center;\n" +
                            "    -fx-font-family: \"Segoe UI\", Helvetica, Arial, sans-serif;\n" +
                            "\n" +
                            "    -fx-background-insets: 0,0,0,0;\n" +
                            "    -fx-padding: 5 5 5 5;\n" +
                            "\n" +
                            "    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");

                    icon3.setIconColor(Color.WHITE);
                    icon3.setIconSize(30);

                    ImageView iv = new ImageView();
                    iv.setPreserveRatio(true);
                    iv.setSmooth(true);
                    iv.setCache(true);
                    btnAdd.setGraphic(icon3);

                    this.setGraphic(btnAdd);
                    this.setAlignment(Pos.CENTER);

                }
            }

        };
        return tableCell;
    });

}
    public void searchItemTable(String key) {
        System.out.println("Searching ...");
            itemFilteredList.setPredicate(billItem-> {
            String filter = key.toLowerCase();

                if(CustomerManager.StringOrNot(filter)){
                    boolean id_matches = String.valueOf(billItem.getItem_id()).toLowerCase().contains(filter);
                    return id_matches;
                }else{
                    boolean title_matches = String.valueOf(billItem.getItem_name()).toLowerCase().contains(filter);
                    return title_matches ;
                }
        });
    }
            @FXML
        void searchItem(KeyEvent keyEvent) {
            String key = searchItem.getText();
            searchItemTable(key);
            itemTable.setItems(itemFilteredList);
        }
//        public void removeItem(ArrayList<BillItem> ItemList){
//
//            for (BillItem currentItem : ItemList) {
//                itemObservableList.remove(currentItem);
//            }


//        }

    public void buttons(){

       /* search.setText("");
        FontIcon icon  =  new FontIcon("anto-search") ;
        icon.setIconSize(40);
        search.setGraphic(icon);*/

    }

public void goToSelectCustomer(ActionEvent action){
        parentController.moveToSelectCustomer();

}
}
