package entity;

public class Order {
	private int orderId;
	private int itemId;
	private int quantity;
	private String orderDate;
	private double totalBill;
	private String type;

	public Order(int orderId, int itemId, int quantity, String orderDate, double totalBill, String type) {
		super();
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.orderDate = orderDate;
		this.totalBill = totalBill;
		this.type = type;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(double totalBill) {
		this.totalBill = totalBill;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Order id " + orderId + " ItemId " + itemId + "Quantity " + quantity + "OrderDate " + orderDate
				+ "TotalBill=" + totalBill + "Type=" + type;
	}

}
