package com.example.Food.Store.Entity.dto.Mapper;

import com.example.Food.Store.Entity.DetallePedido;
import com.example.Food.Store.Entity.dto.DetallePedido.DetallePedidoCreate;
import com.example.Food.Store.Entity.dto.DetallePedido.DetallePedidoDto;
import com.example.Food.Store.Entity.dto.DetallePedido.DetalllePedidoEdit;

public class DetallePedidoMapper {
    public static DetallePedido toEntity(DetallePedidoCreate dto) {
        return DetallePedido.builder()
                .cantidad(dto.cantidad())
                .subtotal(dto.subtotal())
                .build();
    }

    public static DetallePedidoDto toDto(DetallePedido detallePedido) {
        if (detallePedido == null) return null;
        return new DetallePedidoDto(
                detallePedido.getId(),
                detallePedido.getCantidad(),
                detallePedido.getSubtotal(),
                detallePedido.getProducto()
        );
    }

    public static DetallePedidoCreate toCreate(DetallePedido detallePedido){
        if (detallePedido == null) return null;
        return new DetallePedidoCreate(
                detallePedido.getCantidad(),
                detallePedido.getSubtotal(),
                detallePedido.getProducto().getId()
        );
    }

    public static DetalllePedidoEdit toEdit(DetallePedido detallePedido){
        if (detallePedido == null) return null;
        return new DetalllePedidoEdit(
                detallePedido.getId(),
                detallePedido.getCantidad(),
                detallePedido.getSubtotal(),
                detallePedido.getProducto().getId()
        );
    }

    public static void updateEntity(DetallePedido detallePedido, DetalllePedidoEdit dto) {
        detallePedido.setCantidad(dto.cantidad());
        detallePedido.setSubtotal(dto.subtotal());
    }
}
