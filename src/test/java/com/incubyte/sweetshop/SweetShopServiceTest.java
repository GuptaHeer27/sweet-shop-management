package com.incubyte.sweetshop;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class SweetShopServiceTest {

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
}
