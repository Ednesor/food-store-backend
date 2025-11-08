package com.example.Food.Store.Entity.dto.DetallePedido;

import com.example.Food.Store.Entity.Pedido;
import com.example.Food.Store.Entity.Producto;

public record DetalllePedidoEdit(
        Long id,
        Integer cantidad,
        Double subtotal,
        Long productoId
) {
}
