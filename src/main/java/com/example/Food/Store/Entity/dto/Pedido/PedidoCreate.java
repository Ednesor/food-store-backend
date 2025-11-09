package com.example.Food.Store.Entity.dto.Pedido;

import com.example.Food.Store.Entity.dto.DetallePedido.DetallePedidoCreate;

import java.util.List;

public record PedidoCreate(
    Double total,
    List<DetallePedidoCreate> detallePedidos,
    Long usuarioId,
    String telefono,
    String direccion,
    String metodoPago,
    String notas
) {
}
