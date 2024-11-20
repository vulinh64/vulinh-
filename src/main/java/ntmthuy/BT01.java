package ntmthuy;
import java.util.Scanner;

public class BT01 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Nhập số thứ nhất
		System.out.print("Nhập vào số thứ nhất: ");
		int a = Integer.parseInt(scanner.nextLine());

		// Nhập số thứ hai
		System.out.print("Nhập vào số thứ hai: ");
		int b = Integer.parseInt(scanner.nextLine());

		// Tính toán các phép toán
		int tong = a + b;
		int hieu = a - b;
		int tich = a * b;

		// Kiểm tra b khác 0 để tránh chia cho 0
		int phanNguyen = (b != 0) ? a / b : 0; // Chia lấy nguyên
		int phanDu = (b != 0) ? a % b : 0; // Chia lấy dư

		// In ra kết quả
		System.out.println("Tổng của a và b = " + tong);
		System.out.println("Hiệu của a - b = " + hieu);
		System.out.println("Tích của a và b = " + tich);
		System.out.println("Phần nguyên của a / b = " + phanNguyen);
		System.out.println("Phần dư của a % b = " + phanDu);
		System.out.println("a lớn hơn b: " + (a > b));
		System.out.println("a bé hơn b: " + (a < b));


	}
}