import java.util.Scanner;

public class TemperatureConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for temperature value
        System.out.print("Enter the temperature value: ");
        double temperature = scanner.nextDouble();

        // Prompt user for original unit of measurement
        System.out.print("Enter the original unit of measurement (C, F, or K): ");
        scanner.nextLine(); // Consume newline character left by nextDouble()
        String originalUnit = scanner.nextLine().toUpperCase();

        // Variables to store converted temperatures
        double celsius = 0, fahrenheit = 0, kelvin = 0;

        // Perform conversion based on original unit of measurement
        switch (originalUnit) {
            case "C":
                celsius = temperature;
                fahrenheit = celsiusToFahrenheit(celsius);
                kelvin = celsiusToKelvin(celsius);
                break;
            case "F":
                fahrenheit = temperature;
                celsius = fahrenheitToCelsius(fahrenheit);
                kelvin = celsiusToKelvin(celsius);
                break;
            case "K":
                kelvin = temperature;
                celsius = kelvinToCelsius(kelvin);
                fahrenheit = celsiusToFahrenheit(celsius);
                break;
            default:
                System.out.println("Invalid unit of measurement. Please enter C, F, or K.");
                return;
        }

        // Display converted temperatures
        System.out.println("Converted temperatures:");
        System.out.println("Celsius: " + celsius + " °C");
        System.out.println("Fahrenheit: " + fahrenheit + " °F");
        System.out.println("Kelvin: " + kelvin + " K");

        scanner.close();
    }

    // Conversion methods
    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    public static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }
}

