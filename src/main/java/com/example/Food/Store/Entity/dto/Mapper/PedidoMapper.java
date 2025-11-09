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
                .build();
    }

    public static PedidoDto toDto(Pedido p) {
        if(p == null) return null;
        return new PedidoDto(
                p.getId(),
                p.getTotal(),
                p.getEstado(),
                p.getFecha(),
                p.getUsuario() != null ? p.getUsuario().getId() : null,
                p.getDetallePedidos()
        );
    }

    public static void updateEntity(Pedido pedido, PedidoEdit pedidoEdit){
        pedido.setTotal(pedidoEdit.total());
        pedido.setEstado(pedidoEdit.estado());
    }
}
