import com.sun.jndi.ldap.BerDecoder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


    public class conversor{

        private static final String API_URL = " https://v6.exchangerate-api.com/v6/a30ecf89b1b73ddc3a00d27c/latest/USD";

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // Solicita moneda de origen
            System.out.print("Introduce la moneda de origen (ej. USD, EUR): ");
            String baseCurrency = scanner.nextLine().toUpperCase();

            // Solicita moneda de destino
            System.out.print("Introduce la moneda de destino (ej. MXN, GBP): ");
            String targetCurrency = scanner.nextLine().toUpperCase();

            // Solicita cantidad para convertir
            System.out.print("Introduce la cantidad que deseas convertir: ");
            double cantidad = scanner.nextDouble();

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + baseCurrency))
                    .build();

            try {

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

               if (response.statusCode() == 200) {

                   BerDecoder JsonParser = null;
                   JsonParser.parseString(Boolean.parseBoolean(response.body()));

                   Module exchangeRate = Class.class.getModule();

                   double convertedCantidad = cantidad * exchangeRate;

                   System.out.println(cantidad + " " + baseCurrency + " es igual a " + convertedCantidad + " " + targetCurrency);

                } else {

                    System.out.println("Error al obtener las tasas de cambio. Código de estado: " + response.statusCode());
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            scanner.close();
        }
    }

