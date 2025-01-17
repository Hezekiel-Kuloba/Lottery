package lottery;
import java.util.*;

public class FairLottery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take the prices as input
        System.out.println("Enter the prices separated by commas:");
        String pricesInput = scanner.nextLine();
        List<Integer> prices = new ArrayList<>();
        for (String price : pricesInput.split(",")) {
            prices.add(Integer.parseInt(price.trim()));
        }

        // Take the names as input
        System.out.println("Enter the names separated by commas:");
        String namesInput = scanner.nextLine();
        List<String> names = Arrays.asList(namesInput.split(","));

        distributePrices(prices, names);
    }

    public static void distributePrices(List<Integer> prices, List<String> names) {
        // Create a map to store the distribution
        Map<String, List<Integer>> distribution = new HashMap<>();

        // Initialize the map
        for (String name : names) {
            distribution.put(name, new ArrayList<>());
        }

        // Sort the prices in descending order
        Collections.sort(prices, Collections.reverseOrder());

        // Distribute the prices
        int i = 0;
        while (!prices.isEmpty()) {
            // Find the name with the least total price
            String nameWithLeastTotalPrice = names.get(0);
            int leastTotalPrice = getTotalPrice(distribution.get(nameWithLeastTotalPrice));
            for (String name : names) {
                int totalPrice = getTotalPrice(distribution.get(name));
                if (totalPrice < leastTotalPrice) {
                    nameWithLeastTotalPrice = name;
                    leastTotalPrice = totalPrice;
                }
            }

            // Assign the highest price to the name with the least total price
            distribution.get(nameWithLeastTotalPrice).add(prices.get(0));
            prices.remove(0);
            i++;
        }

        // Print the distribution
        for (Map.Entry<String, List<Integer>> entry : distribution.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().toString().replaceAll("[\\[\\] ]", ""));
        }
    }

    public static int getTotalPrice(List<Integer> prices) {
        int total = 0;
        for (int price : prices) {
            total += price;
        }
        return total;
    }
}
