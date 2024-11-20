package ntmthuy;

public class BT05 {
	public static void main(String[] args) {
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				System.out.print(i * j + "\t"); // \t dùng để căn đều các số
			}
			System.out.println(); // Xuống dòng sau mỗi dòng kết quả
		}
	}
}
