package com.codedito.itemservice;

import com.codedito.itemservice.model.Item;
import com.codedito.itemservice.model.User;
import com.codedito.itemservice.service.ItemService;
import com.codedito.itemservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
@EnableAutoConfiguration
public class ItemserviceApplication implements CommandLineRunner {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    public static void main(final String[] args) {
        SpringApplication.run(ItemserviceApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        final User user = userService.findByUsername("jadams");

        final Item item1 = new Item();
        item1.setName("Item1");
        item1.setItemCondition("New");
        item1.setStatus("Active");
        item1.setAddDate(new Date());
        item1.setDescription("This is an item description.");
        item1.setUser(user);

        itemService.addItemByUser(item1, user.getUsername());

        final Item item2 = new Item();
        item2.setName("Item2");
        item2.setItemCondition("Used");
        item2.setStatus("Inactive");
        item2.setAddDate(new Date());
        item2.setDescription("This is an item description for item2.");
        item2.setUser(user);

        itemService.addItemByUser(item2, user.getUsername());
    }
}
