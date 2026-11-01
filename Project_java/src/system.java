
import java.util.Scanner;

public class system {

	static int[] productId = { 101, 102, 103, 104, 105 };
	static String[] productName = { "T-Shirt", "Shoes", "Cap", "Backpack", "Jacket" };
	static double[] productPrice = { 590, 2500, 450, 990, 1590 };

	static int[] cartId = new int[100];
	static int[] cartQuantity = new int[100];
	static int[] next = new int[100];

	static int head = -1;
	static int lastNode = 0;

	public static void showProducts() {
		System.out.println("\n===== PRODUCT LIST =====");

		for (int i = 0; i < productId.length; i++) {
			System.out.println(productId[i] + " - " + productName[i] + " - " + productPrice[i] + " Baht");
		}
	}

	public static void addProduct(int id, int quantity) {

		int index = -1;

		for (int i = 0; i < productId.length; i++) {
			if (productId[i] == id) {
				index = i;
				break;
			}
		}

		if (index == -1 || quantity <= 0) {
			System.out.println("Invalid Product or Quantity.");
			return;
		}

		int newNode = lastNode;

		cartId[newNode] = id;
		cartQuantity[newNode] = quantity;
		next[newNode] = -1;

		if (head == -1) {
			head = newNode;
		} else {
			int current = head;

			while (next[current] != -1) {
				current = next[current];
			}

			next[current] = newNode;
		}

		lastNode++;

		System.out.println(productName[index] + " added to cart.");
	}

	public static void viewCart() {

		if (head == -1) {
			System.out.println("Cart is empty.");
			return;
		}

		System.out.println("\n===== YOUR CART =====");

		int current = head;
		double total = 0;

		while (current != -1) {

			for (int i = 0; i < productId.length; i++) {

				if (cartId[current] == productId[i]) {

					double price = productPrice[i] * cartQuantity[current];

					System.out.println(productName[i] + " x " + cartQuantity[current] + " = " + price + " Baht");

					total += price;
					break;
				}
			}

			current = next[current];
		}

		System.out.println("---------------------");
		System.out.println("Total: " + total + " Baht");
	}

	public static void removeLastItem() {

		if (head == -1) {
			System.out.println("Cart is empty.");
			return;
		}

		if (next[head] == -1) {
			head = -1;
		} else {

			int current = head;

			while (next[next[current]] != -1) {
				current = next[current];
			}

			next[current] = -1;
		}

		System.out.println("Last item removed.");
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int choice;

		do {
			System.out.println("\n===== ONLINE SHOPPING =====");
			System.out.println("1. Show Products");
			System.out.println("2. Add Product");
			System.out.println("3. View Cart");
			System.out.println("4. Remove Last Item");
			System.out.println("5. Exit");
			System.out.print("Choose: ");

			choice = sc.nextInt();

			switch (choice) {

			case 1:
				showProducts();
				break;

			case 2:
				System.out.print("Enter Product ID: ");
				int id = sc.nextInt();

				System.out.print("Enter Quantity: ");
				int quantity = sc.nextInt();

				addProduct(id, quantity);
				break;

			case 3:
				viewCart();
				break;

			case 4:
				removeLastItem();
				break;

			case 5:
				System.out.println("Thank you very much!");
				break;

			default:
				System.out.println("Invalid choice.");
			}

		} while (choice != 5);

		sc.close();
	}
}
