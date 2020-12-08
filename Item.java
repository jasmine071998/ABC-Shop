package entity;

public class Item {
	private int itemId;
	private String itemName;
	private double price;
	private int quantityAvailable;
	private int reorderLevel;

	public Item(int itemId, String itemName, double price, int quantityAvailable, int reorderLevel) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
		this.quantityAvailable = quantityAvailable;
		this.reorderLevel = reorderLevel;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public int getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(int reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	@Override
	public String toString() {
		return "Item id is " + itemId + "\n" + "Item Name is " + itemName + "\n" + "Price is " + price + "\n"
				+ "Quantity is " + quantityAvailable + "\n" + "Reorder Level " + reorderLevel + "\n"
				+ "============================";
	}

}
