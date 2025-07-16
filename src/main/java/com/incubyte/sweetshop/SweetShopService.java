package com.incubyte.sweetshop;

import java.util.*;


public class SweetShopService {

    private Map<String, Sweet> inventory = new HashMap<>();

    public void addSweet(Sweet sweet) {
        inventory.put(sweet.getId(), sweet);
    }

    public List<Sweet> getAllSweets() {
        return new ArrayList<>(inventory.values());
    }

    public boolean deleteSweet(String id) {
        return inventory.remove(id) != null;
    }
    
    // Just to run method in Test
    public List<Sweet> searchByName(String category) { 
        throw new UnsupportedOperationException("Not implemented yet");
    }

    


}
