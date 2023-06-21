package flower.gallery.Dash;

import flower.gallery.dbUtil.DatabaseHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class DashManager {
    private static Connection conn;

    static {
        try {
            conn = DatabaseHandler.getInstance().getConn();
//            System.out.println("OK");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ResultSet getResultSet(String query){
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (stmt != null) {
            try {
                rs = stmt.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rs;
    }

    private static int getSingleResult(String query) throws SQLException {
        int result = 0;
        ResultSet rs = getResultSet(query);
        if(rs != null){
            while (rs.next()){
                result = rs.getInt(1);
            }
        }

        return result;
    }

    public static int getTotalStockQuantity() throws SQLException {
        String query = "SELECT SUM(qty) FROM inventory_flower";
        return getSingleResult(query);
    }

    public static int getTotalCashSales() throws SQLException {
        String query = "SELECT SUM(total_price) FROM orders";
        return getSingleResult(query);
    }

    public static int getTotalQuantity() throws SQLException {
        String query = "SELECT SUM(total_quantity) FROM orders";
        return getSingleResult(query);
    }

    public static int getTotalDiscount() throws SQLException {
        String query = "SELECT SUM(total_discount) FROM orders";
        return getSingleResult(query);
    }

    public static int getTotalOrders() throws SQLException {
        String query = "SELECT COUNT(*) FROM orders";
        return getSingleResult(query);
    }

    public static ArrayList<MonthlyEntry> getMonthlyReport() throws SQLException {
        ArrayList<MonthlyEntry> entries = new ArrayList<>();
        String query = "SELECT " +
        "YEAR(order_date) as orderYear,"+
        "MONTHNAME(order_date) as orderMonth,"+
        "SUM(total_price) as salesPerMonth,"+
        "SUM(total_quantity) as quantityPerMonth,"+
        "SUM(total_discount) as discountPerMonth,"+
        "COUNT(*) ordersPerMonth "+
        "FROM orders "+
        "GROUP BY YEAR(order_date), MONTH(order_date)";
        ResultSet rs = getResultSet(query);

        while (rs.next()){
            entries.add(
            new MonthlyEntry(
                    rs.getLong("orderYear"),
                    rs.getString("orderMonth"),
                    rs.getInt("salesPerMonth"),
                    rs.getInt("quantityPerMonth"),
                    rs.getInt("discountPerMonth"),
                    rs.getInt("ordersPerMonth")
            ));
        }
        return entries;
    }

    public static ArrayList<BookSale> getPopularBooks(int n) throws SQLException {
        ArrayList<BookSale> result = getPopularBookSales(n);
        return result;
        /*for (BookSale e:
             result) {
            int i = (e.getBook_id());
            BookManager.getBookDetails(i);
        }*/
    }

    private static ArrayList<BookSale> getPopularBookSales(int n) throws SQLException {
        ArrayList<BookSale> entries = new ArrayList<>();
        String query = "SELECT flower.flower_id,flower.name,COUNT(*) as numOrders,SUM(quantity) as totalQtySold FROM order_details,flower WHERE order_details.flower_id=flower.flower_id GROUP BY flower_id ORDER BY SUM(quantity) DESC LIMIT "
                + String.valueOf(n);
        ResultSet rs = getResultSet(query);
        while (rs.next()){
            entries.add(
                    new BookSale(
                            rs.getInt("flower_id"),
                            rs.getString("name"),
                            rs.getInt("numOrders"),
                            rs.getInt("totalQtySold")
                    ));
        }
        return entries;

    }

    public static Dictionary<String, Integer> getStockItems() throws SQLException {
        Dictionary<String, Integer> dict = new Hashtable<String, Integer>();
        String query = "SELECT inventory_flower.flower_id, flower.name, SUM(qty) as qty FROM inventory_flower,flower WHERE inventory_flower.flower_id = flower.flower_id GROUP BY flower_id";
        ResultSet resultSet = getResultSet(query);
        while (resultSet.next()){
            dict.put(resultSet.getString("name"), resultSet.getInt("qty")); // insert key/value into dictionary
        }
        return dict;
    }






    /*String query = "SELECT SUM(qty) FROM inventory";
    String query2 = "SELECT SUM(total_price) FROM orders";
    String query4 = "SELECT SUM(total_quantity) FROM orders";
    String query5 = "SELECT SUM(total_discount) FROM orders";
    String query3 = "SELECT COUNT(*) FROM orders";
    String query3 = "SELECT book_id,COUNT(*) as numOrders,SUM(quantity) as totalQtySold FROM order_details GROUP BY book_id ORDER BY SUM(quantity) DESC LIMIT 5";
    String query6 = "SELECT
                            YEAR(order_date) as orderYear,
                            MONTHNAME(order_date) as orderMonth,
                            SUM(total_price) as salesPerMonth,
                            SUM(total_quantity) as quantityPerMonth,
                            SUM(total_discount) as discountPerMonth,
                    COUNT(*) ordersPerMonth
                    FROM    orders
                    GROUP BY YEAR(order_date), MONTH(order_date)";
      */

}
