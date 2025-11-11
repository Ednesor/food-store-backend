package com.example.Food.Store.Entity.dto.Pedido;

import com.example.Food.Store.Constants.Estado;
import com.example.Food.Store.Entity.DetallePedido;
import com.example.Food.Store.Entity.dto.User.UserDto;

import java.time.LocalDate;
import java.util.List;

public record PedidoDto(
        Long id,
        Double total,
        Estado estado,
        LocalDate fecha,
        UserDto usuario, // Antes: Long userId
        List<DetallePedido> detallePedidos,
        String telefono,
        String direccion,
        String metodoPago,
        String notas
) {
}