package com.example.Food.Store.Entity.dto.DetallePedido;

import com.example.Food.Store.Entity.Pedido;
import com.example.Food.Store.Entity.Producto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record DetallePedidoCreate(
        Integer cantidad,
        Double subtotal,
        @JsonProperty("producto_id")
        Long productoId
) {
}
