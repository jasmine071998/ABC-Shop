package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import dao.ItemDAO;
import dao.OrderDAO;

import static dao.ItemDAO.*;
import static dao.OrderDAO.*;
import entity.*;
//import jdbc.Employee;
import service.ItemOperations;
import validation.Validation;

public class TrySwitch3 { 

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		Connection con=null;
		Validation v=new Validation();
		ItemOperations io=new ItemOperations();
		 int f;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","hr","hr");
			
			do {
			System.out.println("Connection established");
			System.out.println("Welcome to ABC shop!!!");
			System.out.println("1. Accept Item details");
			System.out.println("2. Update Item quantity");
			System.out.println("3. Delete Item");
			System.out.println("4. Accept Order details");
			System.out.println("5. Delete Order");
			System.out.println("6. Display Item details");
			System.out.println("7. Display Order details");
			System.out.println("8. Display total quantity sold for items");
			System.out.println("9. Exit");
			
			System.out.println("Enter your choice");
			int ch=sc.nextInt();
			
			switch(ch) {
			case 1:
				
				System.out.println("Enter item name");
				//int id=sc.nextInt();
				String name=sc.next();
				System.out.println("Enter price");
				double price=sc.nextDouble();
				System.out.println("Enter quantity");
				int q=sc.nextInt();
				System.out.println("Enter reorder level");
				int rl=sc.nextInt();
				
				int id=0;
				//System.out.println(id);
				int genid = v.generateItemId(con, id);
				//System.out.println(genid);
				if(v.checkQuantity(q, rl) && v.checkPrice(price)) {
					
					Item i = new Item(genid,name, price, q, rl);
					insertRecord(con, i);
					
				}else
					System.out.println("Please enter valid inputs..");
		
				break;
			case 2:
				System.out.println("Enter id to update quantity");
				int id1=sc.nextInt();
				System.out.println("Enter quantity");
				int qq=sc.nextInt();
				updateRecord(con, qq, id1);
				break;
				
			case 3:
				System.out.println("Enter id to delete");
				int d=sc.nextInt();
				ItemDAO.deleteRecord(con, d);
				//deleteRecord(con, d);
				break;
			
			case 4:
				//System.out.println("Enter order id");
				//int oid=sc.nextInt();
				System.out.println("Enter item id");
				int iid=sc.nextInt();
				System.out.println("Enter quantity");
				int quantity=sc.nextInt();
				System.out.println("Enter order date dd-mm-yyyy");
				String od=sc.next();
			//	System.out.println("Enter total bill");
			//	Double b=sc.nextDouble();
				System.out.println("Enter order type (Sale/Purchase)");
				String ty=sc.next();
				
				int oid=0;
				int genoid=v.generateOrderId(con, oid);
				Double b=io.calTotalBill(con, quantity, iid);
				Order o=new Order(genoid, iid, quantity, od, b, ty);
			//	OrderDAO.insertOrder(con, o);
				
				if(ty.equalsIgnoreCase("Purchase")) {
					//System.out.println("Call purchase method");
					io.purchaseItem(con, o);
				}else if (ty.equalsIgnoreCase("Sale")){
					//System.out.println("Call sale method");
					io.sellItem(con, o);
					
				}else
					System.out.println("Please enter a valid type of transaction");
				
				
				insertOrder(con, o);
				
				break;	
				
			case 5:
				System.out.println("Enter order id to delete");
				int oidd=sc.nextInt();
				OrderDAO.deleteRecord(con, oidd);
				//OrderDAO.deleteRecord(con, 2);
				break;
			
			
				
			case 6:
				ArrayList<Item> item= getRecords(con);
				
				for(int i=0;i<item.size();i++) {
					System.out.println(item.get(i));
				}
				break;
				
			case 7:
				getOrders(con);
				break;
				
			case 8:
				io.totalQuantitySold(con);
				break;
			
			case 9:
				//System.out.println("Exiting application");
				System.exit(0);
				break;
				
			default:
				System.out.println("Please enter a valid choice");
				break;
			
			}	
			System.out.println("Enter 1 to continue");
			f=sc.nextInt();
			
			}while(f==1);		
		}
		
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			con.close();
		}

	}  

}
