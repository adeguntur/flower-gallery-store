package flower.gallery.flower;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.events.JFXDrawerEvent;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MainFlowerController implements Initializable {

    public MFXTableView flowerTable;
    public JFXDrawer drawer;
    private MainFlowerController thisController = this;
    private ObservableList<FlowerData> flowerObservableList = FXCollections.observableArrayList();
    private FilteredList<FlowerData> flowerFilteredList;

    private static FlowerData selectedFlower = null;
    private final MFXTextField searchField = new MFXTextField();
    private final MFXComboBox<String> searchCombo = new MFXComboBox<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getFlowerData();
        setColumnProps();

        flowerTable.setHeaderSupplier(() -> {
            HBox mainContainer = new HBox();
            mainContainer.setPrefHeight(50);
            mainContainer.setPrefWidth(1030);
            mainContainer.setAlignment(Pos.CENTER_LEFT);
            mainContainer.setPadding(new Insets(10,0,5,5));

            HBox spanMid = new HBox();
            spanMid.setMinWidth(610);
            spanMid.setMinHeight(50);

            HBox spanEnd = new HBox();
            spanEnd.setMinHeight(50);
            spanEnd.setMaxWidth(10);

            HBox searchContainer = new HBox(10);
            searchContainer.setMinHeight(50);
            searchContainer.setAlignment(Pos.CENTER_RIGHT);
            searchField.setPromptText("Search Flower");
            searchField.setIcon(new MFXIconWrapper(new MFXFontIcon("mfx-search", 28, Color.web("#4D4D4D")), 24));
            searchField.setIconInsets(new Insets(0,0,10,0));
            searchField.setMinHeight(50);
            searchField.setMinWidth(240);
            searchField.setStyle("-fx-font-size: 18px;");

            searchField.setOnKeyTyped(actionEvent -> {
                flowerFilteredList = new FilteredList<FlowerData>(flowerObservableList);
                thisController.searchTableFromText(searchField.getText());
                flowerTable.setItems(flowerFilteredList);
            });

           /* searchCombo.setItems(FXCollections.observableArrayList("Name", "Salary", "Gender"));
            searchCombo.setPromptText("Select Category");
            searchCombo.setMinHeight(50);
            searchCombo.setComboStyle(Styles.ComboBoxStyles.STYLE2);
            searchCombo.setStyle("-fx-font-size: 30px;");*/

            FontIcon addIcon = new FontIcon("anto-plus-circle");
            addIcon.setIconColor(Color.WHITE);
            addIcon.setIconSize(25);

            MFXButton addBtn = new MFXButton();
            addBtn.setText("Add Flower");
            addBtn.setStyle("-fx-background-color: #2B2B2B;-fx-font-size: 20px;-fx-background-radius: 9,8,5,4,3;-fx-text-fill: #fff;");
            addBtn.setGraphic(addIcon);
            addBtn.setOnAction(actionEvent -> {
                thisController.addNewFlower();
            });

            searchContainer.getChildren().addAll(searchField);
            mainContainer.getChildren().addAll(searchContainer,spanMid,addBtn,spanEnd);
            return mainContainer;
        });
    }

    public void getFlowerData() {
        ArrayList<FlowerData> eList = FlowerManager.getFlowerList(100,1);
        Platform.runLater(()->{
            flowerObservableList.clear();
            for (FlowerData e :
                    eList) {
                flowerObservableList.add(e);

            }
        });
        setDataData();
    }

    private void setDataData() {
        flowerTable.setItems(flowerObservableList);
    }

    private void setColumnProps() {
        MFXTableColumn<FlowerData> flowerIdColumn = new MFXTableColumn<>("Flower ID", Comparator.comparing(FlowerData::getFlower_id));
        MFXTableColumn<FlowerData> flowerNameColumn = new MFXTableColumn<>("Name", Comparator.comparing(FlowerData::getName));
        MFXTableColumn<FlowerData> flowerOriginColumn = new MFXTableColumn<>("Origin", Comparator.comparing(FlowerData::getName));
        MFXTableColumn<FlowerData> categoryColumn = new MFXTableColumn<>("Category", Comparator.comparing(FlowerData::getCategoryName));
        MFXTableColumn<FlowerData> updateColumn = new MFXTableColumn<>("", Comparator.comparing(FlowerData::getFlower_id));
        MFXTableColumn<FlowerData> deleteColumn = new MFXTableColumn<>("", Comparator.comparing(FlowerData::getFlower_id));

        flowerIdColumn.setRowCellFunction(flower -> new MFXTableRowCell(String.valueOf(flower.getFlower_id())));
        flowerNameColumn.setRowCellFunction(flower -> new MFXTableRowCell(String.valueOf(flower.getName())));
        flowerOriginColumn.setRowCellFunction(flower -> new MFXTableRowCell(String.valueOf(flower.getOrigin())));
        categoryColumn.setRowCellFunction(flower -> new MFXTableRowCell(String.valueOf(flower.getCategoryName())));

        updateColumn.setMinWidth(100);
        updateColumn.setShowLockIcon(false);
        updateColumn.setRowCellFunction(flowerData -> {
            MFXTableRowCell rowCell = new MFXTableRowCell("Update");
            rowCell.setGraphicTextGap(5);
            rowCell.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                System.out.println("Update");

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/flower/gallery/fxml/flower/addflower.fxml"));
                    VBox box = loader.load();
                    AddFlowerController controller = loader.getController();

                    controller.setParentController(thisController);
                    controller.setDrawer(drawer);
                    controller.setEntry(flowerData);
                    controller.setAddingNew(false);
                    drawer.setSidePane(box);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                if (drawer.isHidden()) {
                    drawer.open();
                    drawer.toFront();
                    System.out.println("open");
                } else {
                    drawer.toBack();
                    drawer.close();

                    System.out.println("close");
                }

            });
            MFXFontIcon icon = new MFXFontIcon("mfx-file", 25);
            rowCell.setRowAlignment(Pos.CENTER);
            rowCell.setLeadingGraphic(icon);
            rowCell.borderProperty().set(new Border(new BorderStroke(Color.LIMEGREEN, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
            rowCell.setPadding(new Insets(5, 5, 5, 5));
            return rowCell;
        });


        deleteColumn.setMinWidth(100);
        deleteColumn.setShowLockIcon(false);
        deleteColumn.setRowCellFunction(flowerData -> {
            MFXTableRowCell rowCell = new MFXTableRowCell("Delete");
            rowCell.setGraphicTextGap(5);
            rowCell.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                System.out.println("Delete");
                FlowerManager.deleteSingleFlower(flowerData);
                getFlowerData();
            });
            MFXFontIcon icon = new MFXFontIcon("mfx-minus-circle", 25);
            rowCell.setRowAlignment(Pos.CENTER);
            rowCell.setLeadingGraphic(icon);
            rowCell.borderProperty().set(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
            rowCell.setPadding(new Insets(5, 5, 5, 5));
            return rowCell;
        });

        flowerTable.getTableColumns().addAll(flowerIdColumn,flowerNameColumn,flowerOriginColumn,categoryColumn,updateColumn,deleteColumn);
    }

    public void hideDrawer(JFXDrawerEvent jfxDrawerEvent) {
        drawer.toBack();
    }

    public void addNewFlower() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/flower/gallery/fxml/flower/addflower.fxml"));
            VBox box = loader.load();
            AddFlowerController controller = loader.getController();

            controller.setParentController(thisController);
            controller.setDrawer(drawer);
            controller.setAddingNew(true);
            drawer.setSidePane(box);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (drawer.isHidden()) {
            drawer.open();
            drawer.toFront();
            System.out.println("open");
        } else {
            drawer.toBack();
            drawer.close();

            System.out.println("close");
        }
    }

    public void searchTableFromText(String key) {
        System.out.println("Searching ...");
        flowerFilteredList.setPredicate(flowerData -> {
            String filter = key.toLowerCase();
            return String.valueOf(flowerData.getName()).toLowerCase().contains(filter);
        });
    }
}
