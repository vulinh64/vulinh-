package ntmthuy;
import java.util.Scanner;
public class BT03 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nhập ngày trong tuần (1-7, thứ hai đến chủ nhật): ");
		int day = scanner.nextInt();

		switch (day) {
			case 6:
			case 7:
				System.out.println("Ngày thứ bảy hoặc chủ nhật, OT là 200%.");
				break;
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				System.out.println("Ngày bình thường, không có OT.");
				break;
			default:
				System.out.println("Ngày không hợp lệ. Vui lòng nhập từ 1 đến 7.");
		}

	}
}

