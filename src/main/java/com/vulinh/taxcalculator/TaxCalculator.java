package com.vulinh.taxcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class TaxCalculator {

  // BHXH
  static final double SOCIAL_INSURANCE_RATE = 0.08;

  // BHYT
  static final double HEALTH_INSURANCE_RATE = 0.015;

  // BT thất nghiệp
  static final double UNEMPLOYMENT_RATE = 0.01;

  // Thu nhập miễn thuế
  static final long NO_TAX_INCOME = 11_000_000;

  // Giảm trừ gia cảnh cho mỗi một người phụ thuộc
  static final long DEDUCTION_PER_DEPENDANT = 4_400_000;

  // Bậc thuế luỹ tiến
  static final double[] PROGRESSIVE_TAX_THRESHOLD = {
    0.0,
    5_000_000.0,
    10_000_000.0,
    18_000_000.0,
    32_000_000.0,
    52_000_000.0,
    80_000_000.0,
    Double.MAX_VALUE
  };

  // % bậc thuế luỹ tiến
  static final double[] PROGRESSIVE_TAX_RATES = {0.0, 0.05, 0.1, 0.15, 0.2, 0.25, 0.3, 0.35};

  // Lương cơ bản (đa số công ty lấy mức này là mức tối thiểu)
  static final double MIN_BASIC_SALARY = 5_100_000.0;

  static final Scanner SCANNER = new Scanner(System.in);

  public static void main(String[] args) {
    double basicIncome = enterNumber("Nhập vào lương đóng BH: ", MIN_BASIC_SALARY);
    double totalIncome = enterNumber("Nhập vào lương trước thuế: ", 0);
    int numberOfDependants = enterNumber("Số người phụ thuộc: ", 0).intValue();

    double totalInsurance = calculateInsurance(totalIncome, basicIncome);

    // Thu nhập chịu thuế = Tổng thu nhập - bảo hiểm - thu nhập miễn thuế - giảm trừ gia cảnh
    double taxedIncome =
        totalIncome - totalInsurance - NO_TAX_INCOME - DEDUCTION_PER_DEPENDANT * numberOfDependants;

    double taxAmount = calculateTaxAmount(taxedIncome);

    System.out.println();

    System.out.println("Tiền đóng BH: " + format(totalInsurance));
    System.out.println("Thu nhập chịu thuế: " + format(taxedIncome));
    System.out.println("Thuế TNCN: " + format(taxAmount));
    System.out.println("Thu nhập thực lãnh: " + format(totalIncome - taxAmount - totalInsurance));
  }

  static double calculateInsurance(double totalIncome, double basicIncome) {
    return totalIncome >= basicIncome
        ? (SOCIAL_INSURANCE_RATE + HEALTH_INSURANCE_RATE + UNEMPLOYMENT_RATE) * basicIncome
        : 0.0;
  }

  static double calculateTaxAmount(double taxedIncome) {
    int taxLevel = 0;

    double taxAmount = 0;

    while (taxedIncome > PROGRESSIVE_TAX_THRESHOLD[taxLevel]) {
      taxAmount +=
          taxedIncome < PROGRESSIVE_TAX_THRESHOLD[taxLevel + 1]
              // Tính thuế của thu nhập chịu thuế trừ đi mốc thuế dưới 1 bậc
              // nếu chưa đạt ngưỡng mốc tiếp theo
              ? (taxedIncome - PROGRESSIVE_TAX_THRESHOLD[taxLevel])
                  * PROGRESSIVE_TAX_RATES[taxLevel + 1]
              // Lấy mốc tiếp theo trừ mốc trước, nhân với % thuế mốc tiếp theo
              : (PROGRESSIVE_TAX_THRESHOLD[taxLevel + 1] - PROGRESSIVE_TAX_THRESHOLD[taxLevel])
                  * PROGRESSIVE_TAX_RATES[taxLevel + 1];

      taxLevel++;
    }

    return taxAmount;
  }

  private static Double enterNumber(String messageInput, double threshold) {
    while (true) {
      System.out.print(messageInput);

      double result = Double.parseDouble(SCANNER.nextLine());

      if (result >= threshold) {
        return result;
      }

      System.out.println("Giá trị phải lớn hơn " + threshold);
    }
  }

  static BigDecimal format(double value) {
    return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
  }
}
