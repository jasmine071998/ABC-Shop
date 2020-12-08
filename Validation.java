package validation;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Validation {

	public Boolean checkQuantity(int q, int rl) {
		if (q < 0) {
			System.out.println("Quantity cannot be negative");
			return false;
		} else if (q < rl) {
			System.out.println("Quantity cannot be less than reorder level");
			return false;
		} else {
			return true;
		}
	}

	public Boolean checkPrice(double price) {

		if (price < 0) {
			System.out.println("Price cannot be negative");
			return false;
		} else
			return true;

	}

	public int generateItemId(Connection con, int id) throws SQLException {
		int a = 0;
		Statement stmt = con.createStatement();
		String sql = "select max(itemId) from item";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			a = rs.getInt(1);
			a = a + 1;

			// System.out.println(a);

		}

		return a;
	}

	public int generateOrderId(Connection con, int id) throws SQLException {
		int a = 0;
		Statement stmt = con.createStatement();
		String sql = "select max(orderId) from orders";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			a = rs.getInt(1);
			a = a + 1;

			// System.out.println(a);

		}

		return a;
	}

}
