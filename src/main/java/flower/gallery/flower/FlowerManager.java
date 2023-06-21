package flower.gallery.flower;

import flower.gallery.commonUtil.NotificationCreator;
import flower.gallery.dbUtil.DatabaseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FlowerManager {
    private static Connection conn;

    static {
        try {
            conn = DatabaseHandler.getInstance().getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<FlowerData> getFlowerList(int entriesPerPage, int pageNumber) {
        ResultSet rs = getFlowersFromDatabase(entriesPerPage, pageNumber);
        return getFlowerList(rs);

    }

    private static ArrayList<FlowerData> getFlowerList(ResultSet rs) {
        ArrayList<FlowerData> flowerList = new ArrayList<>();
        try {
            while (rs.next()) {
                int category_id = rs.getInt("category_id");
                flowerList.add(new FlowerData(
                        rs.getInt("flower_id"),
                        rs.getInt("category_id"),
                        rs.getString("name"),
                        rs.getString("color"),
                        rs.getString("origin"),
                        rs.getString("flower_description"),
                        getCategoryName(category_id)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flowerList;
    }


    public static ResultSet getFlowersFromDatabase(int entriesPerPage, int pageNumber)  {
        int offset = entriesPerPage * (pageNumber - 1);
        String query = "SELECT * FROM flower LIMIT ?  OFFSET  ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, entriesPerPage);
            preparedStatement.setInt(2, offset);
            resultSet= preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static FlowerData getFlowerDetails(int flowerId) {
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

    private static ResultSet getFlowerDetailsFromDatabase(int flowerId) {
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

    public static String getCategoryName(int categoryId) {
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

    public static boolean updateFlower(FlowerData entry) {
        String updateQuery = "UPDATE flower set category_id = ?,name = ?,color = ?,origin = ?,flower_description = ? where flower_id = ?";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(updateQuery);
            preparedStatement.setInt(1,entry.getCategory_id());
            preparedStatement.setString(2,entry.getName());
            preparedStatement.setString(3,entry.getColor());
            preparedStatement.setString(4,entry.getOrigin());
            preparedStatement.setString(5,entry.getFlower_description());
            preparedStatement.setInt(6,entry.getFlower_id());
            count = preparedStatement.executeUpdate();
            NotificationCreator.showSuccessBottomRight("Operation Successfully Completed","Flower Updated Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            NotificationCreator.showErrorBottomRight("Error Updating Flower",e.getMessage());
        }

        return count > 0;
    }


    public static boolean deleteSingleFlower(FlowerData entry){
        String updateQuery = "DELETE FROM flower where flower_id = ?";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(updateQuery);
            preparedStatement.setInt(1, entry.getFlower_id());
            count = preparedStatement.executeUpdate();
            NotificationCreator.showSuccessBottomRight("Operation Successfully Completed","Flower Deleted Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            NotificationCreator.showErrorBottomRight("Error Deleting Flower",e.getMessage());
        }

        return count > 0;
    }

    public static boolean addSingleFlower(FlowerData entry){
        String addQuery = "insert into flower (category_id, name, color, origin, flower_description) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(addQuery);
            preparedStatement.setInt(1,entry.getCategory_id());
            preparedStatement.setString(2, entry.getName());
            preparedStatement.setString(3,entry.getColor());
            preparedStatement.setString(4, entry.getOrigin());
            preparedStatement.setString(5,entry.getFlower_description());
            count = preparedStatement.executeUpdate();
            NotificationCreator.showSuccessBottomRight("Operation Successfully Completed","Flower Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            NotificationCreator.showErrorBottomRight("Error Adding New Flower",e.getMessage());
        }

        return count > 0;
    }

}
