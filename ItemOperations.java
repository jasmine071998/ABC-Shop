package service;

import static dao.OrderDAO.insertOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Scanner;

import dao.ItemDAO;
import dao.OrderDAO;
import entity.Item;
import entity.Order;
import validation.Validation;

public class ItemOperations {
	Scanner sc = new Scanner(System.in);

	public void sellItem(Connection con, Order o) throws SQLException, ParseException {

		int aa = 0;
		Statement stmt = con.createStatement();
		String sql = "select * from item";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {

			if (rs.getInt(1) == o.getItemId()) {

				int a = rs.getInt(4);
				int b = o.getQuantity();

				if (a > b) {
					a = a - b;

					aa = 1;

					System.out.println("Items sold successfully!!");

					ItemDAO.updateRecord(con, a, rs.getInt(1));

					if (a < rs.getInt(5)) {
						reorder(con, a, rs.getInt(5), rs.getInt(1));
					}

				} else {
					aa = 1;
					System.out.println("Not enough stock in inventory..");
				}

			} else

				continue;

		}
		if (aa < 1)
			System.out.println("No matching item found in inventory");

	}

	public void reorder(Connection con, int a, int b, int c) throws SQLException, ParseException {
		Statement stmt = con.createStatement();
		String sql = "select * from item";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {

			if (c == rs.getInt(1)) {
				int d = a;
				int order = b - a;

				d = d + order;

				// ps.setInt(4, d);
				ItemDAO.updateRecord(con, d, rs.getInt(1));
				// new code

			}

		}

	}

	public void purchaseItem(Connection con, Order o) throws SQLException {

		Statement stmt = con.createStatement();
		String sql = "select * from item";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {

			if (rs.getInt(1) == o.getItemId()) {

				int a = rs.getInt(4);
				int b = o.getQuantity();

				a = a + b;
				System.out.println("Items purchased successfully!!");
				ItemDAO.updateRecord(con, a, rs.getInt(1));

			}

		}

	}

	public void totalQuantitySold(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "select i.itemId,i.itemname,soldQuantity from Item i inner join (select itemId,sum(quantity) as soldQuantity from orders where type='Sale' group by itemId) o on i.itemId=o.itemId";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int prodId = rs.getInt(1);
			String name = rs.getString(2);
			int totalQuantity = rs.getInt(3);
			System.out.println(
					"Total quantity sold for Item ID " + prodId + " Item Name " + name + " is " + totalQuantity);
		}
	}

	public double calTotalBill(Connection con, int quantity1, int id) throws SQLException {

		Double totalBill = 0.0;
		Statement stmt = con.createStatement();
		String sql = "select * from item";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {

			if (rs.getInt(1) == id) {

				int a = rs.getInt(3);

				totalBill = (double) (a * quantity1);

			}
		}
		return totalBill;

	}

}
