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




    


}
