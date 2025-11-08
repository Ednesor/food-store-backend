package com.example.Food.Store.Entity.dto.Mapper;

import com.example.Food.Store.Entity.Pedido;
import com.example.Food.Store.Entity.dto.Pedido.PedidoCreate;
import com.example.Food.Store.Entity.dto.Pedido.PedidoDto;
import com.example.Food.Store.Entity.dto.Pedido.PedidoEdit;

public class PedidoMapper {
    public static Pedido toEntity(PedidoCreate p) {
        return Pedido.builder()
                .total(p.total())
                .build();
    }

    public static PedidoDto toDto(Pedido p) {
        if(p == null) return null;
        return new PedidoDto(
                p.getId(),
                p.getTotal(),
                p.getEstado(),
                p.getFecha(),
                p.getDetallePedidos()
        );
    }

    public static void updateEntity(Pedido pedido, PedidoEdit pedidoEdit){
        pedido.setTotal(pedidoEdit.total());
        pedido.setEstado(pedidoEdit.estado());
    }
}
