package com.incubyte.sweetshop;

import java.util.*;


public class SweetShopService {

    private Map<String, Sweet> inventory = new HashMap<>();

    
    // Add Sweets
    public boolean addSweet(Sweet sweet) {
    	
    	if (inventory.containsKey(sweet.getId())) {
            throw new IllegalArgumentException("Sweet with this ID already exists");
        }
        inventory.put(sweet.getId(), sweet);
        return true;
    }

    // View Sweets
    public List<Sweet> getAllSweets() {
        return new ArrayList<>(inventory.values());
    }

    // Delete Sweets
    public boolean deleteSweet(String id) {
    	
    	 if (!inventory.containsKey(id)) {
    	        throw new IllegalArgumentException("Sweet not found");
    	    }
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
    
    // Search sweet by Category
    public List<Sweet> searchByCategory(String category) {
        List<Sweet> result=new ArrayList<>();
        
        for(Sweet sweet: inventory.values()) {
        	
        	if(sweet.getCategory().equalsIgnoreCase(category)) {
        		result.add(sweet);
        	}
        }
        return result;
    }
    
    // Search by Price Range
    public List<Sweet> searchByPriceRange(double minPrice, double maxPrice) {
    	List<Sweet> result=new ArrayList<>();
    	
    	for (Sweet sweet : inventory.values()) {
            double price = sweet.getPrice();
            if (price >= minPrice && price <= maxPrice) {
                result.add(sweet);
            }
        }

        return result;
    }
    
    // Sort sweet by Price: Low to High
    public List<Sweet> sortByPrice(){
    	List<Sweet> list=new ArrayList<>(inventory.values());
    	
    	 list.sort(Comparator.comparingDouble(Sweet::getPrice));
    	    return list;
    	
    }

    // Sort sweet by Quantity: Low to high
    public List<Sweet> sortByQuantityAscending() {
    	List<Sweet> list = new ArrayList<>(inventory.values());
        list.sort(Comparator.comparingInt(Sweet::getQuantity));
        return list;
    }
    
    
 // Sort sweet by Quantity: Low to high
    public List<Sweet> sortByQuantityDescending() {
    	List<Sweet> list = new ArrayList<>(inventory.values());
        list.sort(Comparator.comparingInt(Sweet::getQuantity).reversed());
        return list;
    	
    	
    }

 // Inventory Management: Purchase
    public boolean purchaseSweet(String id, int quantity) {
        Sweet sweet = inventory.get(id);
        
        if (sweet == null) {
            throw new IllegalArgumentException("Sweet not found");
        }
        
        if (sweet.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough stock available");
        }

        sweet.setQuantity(sweet.getQuantity() - quantity);
        return true;
    }

    
    // Restore Sweet
    public boolean restoreSweet(String id,int quantity) {
    	Sweet sweet=inventory.get(id);
    	
    	if(sweet==null) {
    		  throw new IllegalArgumentException("Sweet not found");
    	}
    	
    	sweet.setQuantity(sweet.getQuantity() + quantity);
    	return true;
    }

    


}
