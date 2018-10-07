package com.codedito.itemservice.controller;

import com.codedito.itemservice.model.Item;
import com.codedito.itemservice.model.User;
import com.codedito.itemservice.service.ItemService;
import com.codedito.itemservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public Item addItem(@RequestBody final Item item) {
        final String username = "jadams";

        return itemService.addItemByUser(item, username);
    }

    @RequestMapping("/itemsByUser")
    public List<Item> getAllItemsByUser() {
        final String username = "jadams";

        return itemService.getItemsByUsername(username);
    }

    @RequestMapping("/all")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @RequestMapping("/{id}")
    public Item getItemById(@PathVariable final Long id) {
        return itemService.getItemById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Item updateItem(@PathVariable final Long id, @RequestBody final Item item) throws IOException {
        item.setId(id);
        return itemService.updateItem(item);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteItemById(@PathVariable final Long id) throws IOException {
        itemService.deleteItemById(id);
    }

    @RequestMapping("/user/{username}")
    public User getUserByUsername(@PathVariable final String username) {
        return itemService.getUserByUsername(username);
    }
}
