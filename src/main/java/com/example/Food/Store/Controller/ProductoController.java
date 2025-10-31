package com.example.Food.Store.Controller;

import com.example.Food.Store.Entity.dto.Producto.ProductoCreate;
import com.example.Food.Store.Entity.dto.User.UserCreate;
import com.example.Food.Store.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProductoCreate p) {
        try {
            return ResponseEntity.ok(productoService.save(p));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrio un error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productoService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrio un error: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(productoService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrio un error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            productoService.delete(id);
            return ResponseEntity.ok().body("Producto eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrio un error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductoCreate p) {
        try {
            return ResponseEntity.ok(productoService.update(id, p));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrio un error: " + e.getMessage());
        }
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productoService.updateStatus(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrio un error: " + e.getMessage());
        }
    }

    @GetMapping("/categoria/{categoryId}")
    public ResponseEntity<?> findByCategoryId(@PathVariable Long categoryId) {
        try {
            return ResponseEntity.ok(productoService.findByCategoriaId(categoryId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrio un error: " + e.getMessage());
        }
    }
}
