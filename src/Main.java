import java.util.Arrays;
import java.util.List;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        String number = "";
        int baseOrigin = 10;
        int baseDest = 36;

        try {
            List<String> parameterList = Arrays.stream(args)
                    .flatMap(param -> Arrays.stream(param.split("\\s+"))) // Split by spaces
                    .map(param -> param.replaceAll("[^\\d.-]", "")) // Remove non-numeric characters
                    .filter(param -> !param.isEmpty()) // Filter out empty strings
                    .toList();

            number = parameterList.get(0);
            baseOrigin = Integer.parseInt(parameterList.get(1));
            baseDest = Integer.parseInt(parameterList.get(2));

        } catch (Exception e) {
            System.out.println("The parameters must be Number, Source base and Destiny Base");
            exit(1);
        }

        validateBases(baseDest, baseOrigin);
        printNumberAndBase(number, baseOrigin);

        //Base Conversion
        String resp = Long.toString(Long.parseLong(number, baseOrigin), baseDest).toUpperCase();

        printNumberAndBase(resp, baseDest);
    }

    private static void printNumberAndBase(String number, int base) {
        System.out.printf("Num: %s, Base: %s%n", number, base);
    }

    private static void validateBases(int base_dest, int base_origin) {
        if (base_dest > 36 || base_origin > 36 || base_dest < 2 || base_origin < 2) {
            System.out.println("the bases must be between 2 and 36");
            exit(-1);
        }
    }
}
