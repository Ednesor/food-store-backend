package com.example.Food.Store.Controller;

import com.example.Food.Store.Constants.Estado;
import com.example.Food.Store.Entity.dto.Pedido.PedidoCreate;
import com.example.Food.Store.Entity.dto.Pedido.PedidoEdit;
import com.example.Food.Store.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<?> save (@RequestBody PedidoCreate p){
        try{
            return ResponseEntity.ok(pedidoService.save(p));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll (){
        try{
            return ResponseEntity.ok(pedidoService.findAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error: " + e.getMessage());
        }
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<?> update (@PathVariable Long id){
        try{
            pedidoService.updateStatusCancel(id);
            return ResponseEntity.ok("Pedido cancelado correctamente");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error: " + e.getMessage());
        }
    }

    @PostMapping("/terminar/{id}")
    public ResponseEntity<?> terminar (@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pedidoService.updateStatus(id, Estado.TERMINADO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrio un error: " + e.getMessage());
        }
    }

    @PostMapping("/confirmar/{id}")
    public ResponseEntity<?> confirmar (@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pedidoService.updateStatus(id, Estado.CONFIRMADO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrio un error: " + e.getMessage());
        }
    }

    @GetMapping("/getAll/{id}")
    public ResponseEntity<?> getAllByUserId (@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pedidoService.findAllByUsuarioId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrio un error: " + e.getMessage());
        }
    }
}
