package flower.gallery.orders;

import flower.gallery.dbUtil.DatabaseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderManager {

    private static Connection conn;

    static {
        try {
            conn = DatabaseHandler.getInstance().getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ordersInfo> getOrderList(int entriesPerPage, int pageNumber) {
        ResultSet rs = getOrdersFromDatabase(entriesPerPage, pageNumber);
        return createOrderList(rs);
    }

    private static ResultSet getOrdersFromDatabase(int entriesPerPage, int pageNumber) {
        int offset = entriesPerPage * (pageNumber - 1);
        String query = "SELECT orders.*," +
                "customer.customer_name,employee.fname FROM orders,employee , customer WHERE orders.employee_id= employee.employee_id " +
                "And orders.customer_id = customer.customer_id LIMIT ?  OFFSET  ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, entriesPerPage);
            preparedStatement.setInt(2, offset);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<ordersInfo> createOrderList(ResultSet rs) {
        ArrayList<ordersInfo> orderList = new ArrayList<>();
        try {
//            order_id INT PRIMARY KEY,
//            customer_id INT,
//            employee_id INT,
//            order_date DATE,
//            total_quantity INT,
//            total_price INT,
//            total_discount INT,
            while (rs.next()) {
                orderList.add(new ordersInfo(
                        String.valueOf(rs.getInt("order_id")),
                        String.valueOf(rs.getInt("customer_id")),
                        String.valueOf(rs.getInt("employee_id")),
                        String.valueOf(rs.getDate("order_date")),
                        rs.getInt("total_quantity"),
                        rs.getInt("total_price"),
                        rs.getInt("total_discount"),
                        rs.getString("customer_name"),
                        rs.getString("fname")

                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

}
