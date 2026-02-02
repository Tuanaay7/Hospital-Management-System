package com.example.util;

public class TcKimlikValidator {

    public static boolean isValid(String tc) {

        if (tc == null || !tc.matches("\\d{11}")) return false;
        if (tc.charAt(0) == '0') return false;

        int[] d = new int[11];
        for (int i = 0; i < 11; i++) {
            d[i] = Character.getNumericValue(tc.charAt(i));
        }

        int sumOdd = d[0] + d[2] + d[4] + d[6] + d[8];
        int sumEven = d[1] + d[3] + d[5] + d[7];

        int digit10 = ((sumOdd * 7) - sumEven) % 10;
        if (digit10 < 0) digit10 += 10;

        int digit11 = (sumOdd + sumEven + d[9]) % 10;

        return d[9] == digit10 && d[10] == digit11;
    }
}
