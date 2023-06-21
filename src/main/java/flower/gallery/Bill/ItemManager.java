package flower.gallery.Bill;

import flower.gallery.Bill.data.BillItem;
import flower.gallery.dbUtil.DatabaseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemManager extends CustomerManager {

    private Connection conn;

    public ItemManager() throws SQLException {
        this.conn = DatabaseHandler.getInstance().getConn();
    }

    public ResultSet getItemFromDatabase() {

        String query = "SELECT flower.flower_id, flower.name, flower.origin , inventory_flower.list_price , inventory_flower.qty FROM flower JOIN inventory_flower ON flower.flower_id=inventory_flower.flower_id where qty> 0 order by flower_id";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);
//
//            preparedStatement.setInt(1, viewitem);
            resultSet = preparedStatement.executeQuery();
            return resultSet;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    private ArrayList<BillItem> createItemList(ResultSet rs) {
        ArrayList<BillItem> ItemList = new ArrayList<>();
        try {
            while (rs.next()) {
                ItemList.add(new BillItem(
                        rs.getInt("flower_id") ,
                        rs.getString("name"),
                        rs.getString("origin") ,
                        rs.getInt("list_price"),
                        rs.getInt("qty")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ItemList;
    }

    public ArrayList<BillItem> getItemList() {
        ResultSet rs = getItemFromDatabase();
        return createItemList(rs);
    }



}
