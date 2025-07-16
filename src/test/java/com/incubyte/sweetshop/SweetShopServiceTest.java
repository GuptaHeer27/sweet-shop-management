package com.incubyte.sweetshop;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class SweetShopServiceTest {

	// Add Sweets
    @Test
    public void shouldAddSweetToInventory() {
        SweetShopService service = new SweetShopService();

        Sweet sweet = new Sweet("S101", "Rasgulla", "Dry", 20.0, 10);
        service.addSweet(sweet);

        List<Sweet> sweets = service.getAllSweets();

        assertEquals(1, sweets.size());
        assertEquals("S101", sweets.get(0).getId());
        assertEquals("Rasgulla", sweets.get(0).getName());
    }
    
    
    // Delete sweet
    @Test
    public void shouldDeleteSweetFromInventory() {
        SweetShopService service = new SweetShopService();

        Sweet sweet = new Sweet("S102", "Ladoo", "Festive", 25.0, 15);
        service.addSweet(sweet);

        boolean deleted = service.deleteSweet("S102");

        assertTrue(deleted); // verify it returned true
        assertEquals(0, service.getAllSweets().size()); // verify item is gone
    }
    
    
    // View Sweets
    @Test
    public void shouldReturnAllSweetsFromInventory() {
        SweetShopService service = new SweetShopService();

        service.addSweet(new Sweet("S101", "Rasgulla", "Dry", 20.0, 10));
        service.addSweet(new Sweet("S102", "Ladoo", "Festive", 25.0, 15));

        List<Sweet> sweets = service.getAllSweets();
        
        
        List<String> ids = new ArrayList<>();
        for (Sweet sweet : sweets) {
            ids.add(sweet.getId());
        }

        assertEquals(2, sweets.size());
        assertTrue(ids.contains("S101"));
        assertTrue(ids.contains("S102"));
      
    }
    
    
    // Search sweet by Name
    @Test
    public void shouldReturnSweetsMatchingKeyword() {
        SweetShopService service = new SweetShopService();

        service.addSweet(new Sweet("S101", "Rasgulla", "Dry", 20.0, 10));
        service.addSweet(new Sweet("S102", "Ladoo", "Festive", 25.0, 15));
        service.addSweet(new Sweet("S103", "Kesar Ladoo", "Festive", 30.0, 8));

        List<Sweet> results = service.searchByName("ladoo");

        assertEquals(2, results.size());
        assertTrue(results.get(0).getName().toLowerCase().contains("ladoo"));
        assertTrue(results.get(1).getName().toLowerCase().contains("ladoo"));
    }
    
    
    // Search sweet by Category
    @Test
    public void shouldReturnSweetsByCategory() {
        SweetShopService service = new SweetShopService();
        service.addSweet(new Sweet("S101", "Rasgulla", "Dry", 20, 10));
        service.addSweet(new Sweet("S102", "Ladoo", "Festive", 25, 15));

        List<Sweet> results = service.searchByCategory("Festive");

        assertEquals(1, results.size());
        assertEquals("S102", results.get(0).getId());
    }
    
    //Search Sweet by price range
    @Test
    public void shouldReturnSweetByWithinPriceRange() {
    	 SweetShopService service = new SweetShopService();
    	    service.addSweet(new Sweet("S101", "Rasgulla", "Dry", 20, 10));
    	    service.addSweet(new Sweet("S102", "Ladoo", "Festive", 40, 15));

    	    List<Sweet> results = service.searchByPriceRange(15, 30);

    	    assertEquals(1, results.size());
    	    assertEquals("S101", results.get(0).getId());
    }


    // Sort Sweet by Price:Low to high
    @Test
    public void shouldSortSweetsByPrice() {
        SweetShopService service = new SweetShopService();
        service.addSweet(new Sweet("S1", "Ladoo", "Festive", 30.0, 10));
        service.addSweet(new Sweet("S2", "Rasgulla", "Dry", 20.0, 15));

        List<Sweet> sorted = service.sortByPrice();

        assertEquals("S2", sorted.get(0).getId());  // cheaper one first
        assertEquals("S1", sorted.get(1).getId());
    }



    
    
    
    
    
    
    
}
