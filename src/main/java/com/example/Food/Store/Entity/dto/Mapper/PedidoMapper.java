package com.example.Food.Store.Entity.dto.Mapper;

import com.example.Food.Store.Entity.DetallePedido;
import com.example.Food.Store.Entity.Pedido;
import com.example.Food.Store.Entity.User;
import com.example.Food.Store.Entity.dto.Pedido.PedidoCreate;
import com.example.Food.Store.Entity.dto.Pedido.PedidoDto;
import com.example.Food.Store.Entity.dto.Pedido.PedidoEdit;

import java.util.List;

public class PedidoMapper {
    public static Pedido toEntity(PedidoCreate p, User usuario) {
        return Pedido.builder()
                .total(p.total())
                .usuario(usuario)
                .telefono(p.telefono())
                .direccion(p.direccion())
                .metodoPago(p.metodoPago())
                .notas(p.notas())
                .build();
    }

    public static PedidoDto toDto(Pedido p) {
        if(p == null) return null;
        return new PedidoDto(
                p.getId(),
                p.getTotal(),
                p.getEstado(),
                p.getFecha(),
                UserMapper.toDto(p.getUsuario()), // Antes: p.getUsuario() != null ? p.getUsuario().getId() : null,
                p.getDetallePedidos(),
                p.getTelefono(),
                p.getDireccion(),
                p.getMetodoPago(),
                p.getNotas()
        );
    }

    public static void updateEntity(Pedido pedido, PedidoEdit pedidoEdit){
        pedido.setTotal(pedidoEdit.total());
        pedido.setEstado(pedidoEdit.estado());
    }
}
