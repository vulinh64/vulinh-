package ntmthuy;
import java.util.Scanner ;
public class BT02 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nhập n (50 <= n <= 100): ");
		int n = scanner.nextInt();

		if (n < 50 || n > 100) {
			System.out.println("n phải nằm trong khoảng từ 50 đến 100.");
		} else {
			double sum = 0;
			for (int i = 1; i <= n; i++) {
				sum += 1.0 / i;
			}
			System.out.println("Kết quả của biểu thức là: " + sum);
		}

	}
}

