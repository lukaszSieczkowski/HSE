package com.codedito.itemservice.service.impl;


import com.codedito.itemservice.client.UserFeignClient;
import com.codedito.itemservice.model.Item;
import com.codedito.itemservice.model.User;
import com.codedito.itemservice.repository.ItemRepository;
import com.codedito.itemservice.service.ItemService;
import com.codedito.itemservice.service.UserService;
import com.codedito.itemservice.util.UserContextHolder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public Item addItemByUser(final Item item, final String username) {
        final Item localItem = itemRepository.findByName(item.getName());

        if (localItem != null) {
            LOG.info("Item with name {} already exists. Nothing will be done.", item.getName());
            return null;
        } else {
            final Date today = new Date();
            item.setAddDate(today);

            final User user = userService.findByUsername(username);
            item.setUser(user);
            final Item newItem = itemRepository.save(item);

            return newItem;
        }
    }

    @Override
    public List<Item> getAllItems() {

        return (List<Item>) itemRepository.findAll();
    }

    @Override
    public List<Item> getItemsByUsername(final String username) {
        final User user = userService.findByUsername(username);

        return (List<Item>) itemRepository.findByUser(user);
    }

    @Override
    public Item getItemById(final Long id) {
        return itemRepository.findOne(id);
    }

    @Override
    public Item updateItem(final Item item) throws IOException {
        final Item localItem = getItemById(item.getId());

        if (localItem == null) {
            throw new IOException("Item was not found.");
        } else {
            localItem.setName(item.getName());
            localItem.setItemCondition(item.getItemCondition());
            localItem.setStatus(item.getStatus());
            localItem.setDescription(item.getDescription());

            return itemRepository.save(localItem);
        }
    }

    @Override
    public void deleteItemById(final Long id) {
        itemRepository.delete(id);
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "buildFallbackUser",
            threadPoolKey = "itemByUserThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            }
    )
    public User getUserByUsername(final String username) {
        //    randomlyRunLong();
        LOG.debug("ItemService.getUserByUsername Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        return userFeignClient.getUserByUsername(username);
    }

    private void randomlyRunLong() {
        final Random rand = new Random();
        final int randomNum = rand.nextInt((3 - 1) + 1) + 1;
        if (randomNum == 3) sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(11000);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    private User buildFallbackUser(final String username) {
        final User user = new User();
        user.setId(123123L);
        user.setUsername("Temp Username");

        return user;
    }
}