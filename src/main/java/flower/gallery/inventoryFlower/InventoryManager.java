package flower.gallery.inventoryFlower;

import flower.gallery.dbUtil.DatabaseHandler;
import flower.gallery.flower.FlowerData;


import java.sql.*;
import java.util.ArrayList;

public class InventoryManager {
    private Connection conn;

    public InventoryManager() {
        try {
            this.conn = DatabaseHandler.getInstance().getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getTotalCountInStock() throws SQLException {
        String sumQuery = "SELECT SUM(qty) as sum_qty FROM inventory";
        int totalQty = -1;
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sumQuery);
        while (resultSet.next()) {
            totalQty = resultSet.getInt("sum_qty");
        }
        return totalQty;
    }

    public ArrayList<String> getFlowerIdsForCategory(int categoryId) {
        String query = "SELECT flower_id FROM flower where category_id = ?";
        PreparedStatement preparedStatement = null;
        ArrayList<String> idList = new ArrayList<>();
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, categoryId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                idList.add(resultSet.getString("flower_id"));
            }

            return idList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getCategoryName(int categoryId) {
        String query = "SELECT category_name FROM flower_category where category_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, categoryId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getString("category_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return "";
    }

    private ArrayList<StockEntry> createOrderList(ResultSet rs) {
        ArrayList<StockEntry> orderList = new ArrayList<>();
        try {
            while (rs.next()) {
                orderList.add(new StockEntry(
                        rs.getInt("flower_id"),
                        rs.getString("name"),
                        rs.getInt("list_price"),
                        rs.getInt("qty"),
                        rs.getInt("min_qty")

                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public ArrayList<StockEntry> getStockItemList(int entriesPerPage, int pageNumber) {
        ResultSet rs = getInventoryFromDatabase(entriesPerPage, pageNumber);
        return createOrderList(rs);
    }

    private ResultSet getInventoryFromDatabase(int entriesPerPage, int pageNumber) {
        int offset = entriesPerPage * (pageNumber - 1);
        String query = "SELECT inventory_flower.*,flower.name FROM inventory_flower,flower where inventory_flower.flower_id = flower.flower_id";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateSingleEntry(StockEntry entry){
        String updateQuery = "UPDATE inventory_flower set list_price = ?, qty = ?, min_qty = ? where flower_id = ?";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(updateQuery);
            preparedStatement.setInt(1,entry.getList_price());
            preparedStatement.setInt(2,entry.getQty());
            preparedStatement.setInt(3,entry.getMin_qty());
            preparedStatement.setInt(4,entry.getFlower_id());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count > 0;
    }

    public boolean deleteSingleEntry(StockEntry entry){
        String updateQuery = "DELETE FROM inventory_flower where flower_id = ?";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(updateQuery);
            preparedStatement.setInt(1,entry.getFlower_id());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count > 0;
    }

    public boolean addSingleEntry(StockEntry entry){
        String addQuery = "insert into inventory_flower (flower_id, list_price, qty, min_qty) values (?,?,?,?)";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(addQuery);
            preparedStatement.setInt(1,entry.getFlower_id());
            preparedStatement.setInt(2,entry.getList_price());
            preparedStatement.setInt(3,entry.getQty());
            preparedStatement.setInt(4,entry.getMin_qty());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count > 0;
    }

    public FlowerData getFlowerDetails(int flowerId) {
        ResultSet rs = getFlowerDetailsFromDatabase(flowerId);
        FlowerData flowerInfo = null;

        try {
            while (rs.next()) {
                int category_id = rs.getInt("category_id");
                flowerInfo = new FlowerData(
                        rs.getInt("flower_id"),
                        rs.getInt("category_id"),
                        rs.getString("name"),
                        rs.getString("color"),
                        rs.getString("origin"),
                        rs.getString("flower_description"),
                        getCategoryName(category_id)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flowerInfo;
    }


    private ResultSet getFlowerDetailsFromDatabase(int flowerId) {
        String query = "SELECT * FROM flower WHERE flower_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, flowerId);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
