package com.example.Food.Store.Entity.dto.Pedido;

import com.example.Food.Store.Constants.Estado;
import com.example.Food.Store.Entity.DetallePedido;

import java.util.List;

public record PedidoEdit(
        Double total,
        List<DetallePedido> detallePedidos,
        Estado estado
) {
}
