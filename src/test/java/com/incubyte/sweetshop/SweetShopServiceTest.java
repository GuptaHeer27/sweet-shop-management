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


    
    
    
    
    
    
    
}
