package com.incubyte.sweetshop;

import java.util.*;


public class SweetShopService {

    private Map<String, Sweet> inventory = new HashMap<>();

    
    // Add Sweets
    public void addSweet(Sweet sweet) {
        inventory.put(sweet.getId(), sweet);
    }

    // View Sweets
    public List<Sweet> getAllSweets() {
        return new ArrayList<>(inventory.values());
    }

    // Delete Sweets
    public boolean deleteSweet(String id) {
        return inventory.remove(id) != null;
    }
    
    
    // Search sweets by Name
    public List<Sweet> searchByName(String keyword) {
        List<Sweet> result = new ArrayList<>();

        for (Sweet sweet : inventory.values()) {
            if (sweet.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(sweet);
            }
        }

        return result;
    }


    


}
