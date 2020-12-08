package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Item;

public class ItemDAO {

	public static void insertRecord(Connection con, Item i) throws SQLException {
		String str1 = "insert into item values(?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(str1);

		ps.setInt(1, i.getItemId());
		ps.setString(2, i.getItemName());
		ps.setDouble(3, i.getPrice());
		ps.setInt(4, i.getQuantityAvailable());
		ps.setInt(5, i.getReorderLevel());

		int n = ps.executeUpdate();
		if (n > 0)
			System.out.println(n + " record inserted....");
		else
			System.out.println("Error..");

	}

	public static void updateRecord(Connection con, int quantity, int itemid) throws SQLException {

		String str1 = "update item set quantityAvailable=? where itemId=?";
		PreparedStatement ps = con.prepareStatement(str1);
		ps.setInt(1, quantity);
		ps.setInt(2, itemid);

		int n = ps.executeUpdate();

		if (n > 0)
			System.out.println(n + " record updated...");
		else
			System.out.println("No such item exists in inventory..");

	}

	public static void updateQuantitySold(Connection con, int quantity, int itemid) throws SQLException {

		String str1 = "update item set quantitysold=? where itemId=?";
		PreparedStatement ps = con.prepareStatement(str1);
		ps.setInt(1, quantity);
		ps.setInt(2, itemid);

		int n = ps.executeUpdate();

		if (n > 0)
			System.out.println(n + " record updated...");
		else
			System.out.println("No such item exists in inventory..");

	}

	public static void deleteRecord(Connection con, int id) throws SQLException {

		String str1 = "delete from item where itemId=?";
		PreparedStatement ps = con.prepareStatement(str1);
		ps.setInt(1, id);

		int n = ps.executeUpdate();

		if (n > 0)
			System.out.println(n + " record deleted...");
		else
			System.out.println("No such item exists in inventory..");

	}

	public static ArrayList<Item> getRecords(Connection con) throws SQLException {

		System.out.println("Displaying Item details..");

		ArrayList<Item> item = new ArrayList<Item>();
		Statement stmt = con.createStatement();
		String sql = "select * from item";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int itemid = rs.getInt(1);
			String ename = rs.getString(2);
			int price = rs.getInt(3);
			int qa = rs.getInt(4);
			int rl = rs.getInt(5);

			Item i = new Item(itemid, ename, price, qa, rl);
			item.add(i);

		}

		return item;

	}

}
