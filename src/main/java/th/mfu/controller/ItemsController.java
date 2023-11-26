package th.mfu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.domain.Item;
import th.mfu.repository.ItemRepository;

@Transactional
@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemRepository itemRepo;
    
    @GetMapping
    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }
    @PostMapping
    public ResponseEntity<String> addItem(Item item) {
        try {
            itemRepo.save(item);
            return ResponseEntity.ok("Item added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add item");
        }
    }
}
