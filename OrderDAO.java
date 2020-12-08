package dao;

//import java.text.SimpleDateFormat;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.text.*;

import entity.Item;
import entity.Order;

public class OrderDAO {

	public static void deleteRecord(Connection con, int id) throws SQLException {

		String str1 = "delete from orders where orderId=?";
		PreparedStatement ps = con.prepareStatement(str1);
		ps.setInt(1, id);
		int n = ps.executeUpdate();
		if (n > 0)
			System.out.println(n + " record deleted...");
		else
			System.out.println("No such item exists in inventory..");
	}

	public static void insertOrder(Connection con, Order o) throws ParseException, SQLException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date uod = sdf1.parse(o.getOrderDate());
		long ms = uod.getTime();

		java.sql.Date sqldob = new java.sql.Date(ms);

		String str1 = "insert into orders values(?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(str1);
		ps.setInt(1, o.getOrderId());
		ps.setInt(2, o.getItemId());
		ps.setInt(3, o.getQuantity());
		ps.setDate(4, sqldob);
		ps.setDouble(5, o.getTotalBill());
		ps.setString(6, o.getType());

		int result = ps.executeUpdate();

		if (result > 0)
			System.out.println("Order inserted");
		else
			System.out.println("Not inserted");

	}

	public static void getOrders(Connection con) throws SQLException {
		System.out.println("Displaying order details..");

		Statement stmt = con.createStatement();
		String sql = "select * from orders";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {

			System.out.println("Order ID is " + rs.getInt(1));
			System.out.println("Item ID is " + rs.getInt(2));
			System.out.println("Quantity is " + rs.getInt(3));
			System.out.println("Order date is " + rs.getDate(4));
			System.out.println("Total bill is " + rs.getDouble(5));
			System.out.println("Order Type is " + rs.getString(6));
			System.out.println("====================================");

		}

	}

}
