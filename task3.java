import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose base currency (e.g., USD, EUR, GBP):");
        String baseCurrency = scanner.nextLine().toUpperCase();
        System.out.println("Choose target currency (e.g., USD, EUR, GBP):");
        String targetCurrency = scanner.nextLine().toUpperCase();

        double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
        if (exchangeRate == -1) {
            System.out.println("Failed to fetch exchange rates. Please try again later.");
            return;
        }

        System.out.println("Enter the amount in " + baseCurrency + " to convert:");
        double amount = scanner.nextDouble();

        double convertedAmount = amount * exchangeRate;

        System.out.printf("%.2f %s = %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);

        scanner.close();
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) {
        double exchangeRate = -1;
        try {
            URL url = new URL("https://api.exchangerate-api.com/v4/latest/" + baseCurrency);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                String jsonResponse = response.toString();
                String rateKey = "\"" + targetCurrency + "\":";
                int startIndex = jsonResponse.indexOf(rateKey) + rateKey.length();
                int endIndex = jsonResponse.indexOf(",", startIndex);
                String rateValue = jsonResponse.substring(startIndex, endIndex);
                exchangeRate = Double.parseDouble(rateValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exchangeRate;
    }
}

