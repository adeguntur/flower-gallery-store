package flower.gallery.orders;

import flower.gallery.dbUtil.DatabaseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class orderDetailsManager {
    private static Connection conn;

    static {
        try {
            conn = DatabaseHandler.getInstance().getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<orderDetailsData> getOrderDetList(int orderId) {
        System.out.println(orderId);
        ResultSet rs = getOrderDetFromDatabase(orderId);
        return createOrderDetList(rs);
    }

    private static ResultSet getOrderDetFromDatabase(int orderId) {

        String query = "SELECT order_details.*,flower.name FROM order_details,flower WHERE order_details.flower_id = flower.flower_id AND order_details.order_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, orderId);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static ArrayList<orderDetailsData> createOrderDetList(ResultSet rs) {
        ArrayList<orderDetailsData> orderList = new ArrayList<>();
        try {
            while (rs.next()) {
                orderList.add(new orderDetailsData(
                        "", // changed this just for testing
                        String.valueOf(rs.getInt("flower_id")),
                        String.valueOf(rs.getInt("order_id")),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getInt("unit_price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }



}
