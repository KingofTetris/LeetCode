package 算法实验作业7;

/**
 * @author by KingOfTetris
 * @date 2023/6/1
 */

import java.util.ArrayList;
import java.util.List;

class Item {
    private String name;
    private double size;
    private double worth;
    private boolean canTakePart;

    public Item(String name, double size, double worth, boolean canTakePart) {
        this.name = name;
        this.size = size;
        this.worth = worth;
        this.canTakePart = canTakePart;
    }

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

    public double getWorth() {
        return worth;
    }

    public boolean canTakePart() {
        return canTakePart;
    }
}

class Suitcase {
    private int capacity;
    private List<Item> items;

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Suitcase(int capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }
}

public class ItemSelection {
    public static void main(String[] args) {
        int numSuitcases = 5;
        int[] suitcaseCapacities = {80, 150, 200, 90, 50};
        int totalItems = 10;
        String[] itemNames = {"Violin", "Sugar", "Camera", "Book", "Cloth", "Keyboard",
                "PC", "Ball", "Photo", "Table"};
        double[] itemSizes = {20, 30, 40, 50, 60, 10, 15, 20, 23.5, 200};
        int[] itemWorths = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
        int[] suitcasesWorth = new int[10];
        int[] visited = new int[10];
        boolean[] itemCanTakePart = {true, true, false, true, false, true, true, false, false, false};

        List<Suitcase> suitcases = new ArrayList<>();
        for (int i = 0; i < numSuitcases; i++) {
            suitcases.add(new Suitcase(suitcaseCapacities[i]));
        }

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < totalItems; i++) {
            Item item = new Item(itemNames[i], itemSizes[i], itemWorths[i], itemCanTakePart[i]);
            items.add(item);
        }

        // Sort items by worth (descending order)
        items.sort((i1, i2) -> Double.compare(i2.getWorth(), i1.getWorth()));


        int s = 0;
        // Select items for each suitcase
        for (Suitcase suitcase : suitcases) {
            int k = 0;
            for (Item item : items) {
                int capacity = suitcase.getCapacity();
                if (item.canTakePart() && visited[k] == 0 && capacity >= item.getSize()){
                    visited[k] = 1;
                    suitcase.addItem(item);
                    capacity -= item.getSize();
                    suitcase.setCapacity(capacity);
                    suitcasesWorth[s] += item.getWorth();
                }
                k++;
            }
            s++;
        }

        // Print the selected items for each suitcase
        long sum = 0;
        for (int i = 0; i < numSuitcases; i++) {
            Suitcase suitcase = suitcases.get(i);
            System.out.println("Items in Suitcase " + (i + 1) + ":");
            for (Item item : suitcase.getItems()) {
                System.out.println("- " + item.getName());
            }
            System.out.println("rest of suitcase " + (i+1) + " is " + suitcase.getCapacity());
            System.out.println("worth of suitcases" + (i+1)  + ":" + suitcasesWorth[i]);
            sum += suitcasesWorth[i];
            System.out.println();
        }

        System.out.println("the max worth is " + sum);
    }
}

