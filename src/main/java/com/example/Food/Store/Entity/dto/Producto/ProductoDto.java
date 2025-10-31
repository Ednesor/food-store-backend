package com.example.Food.Store.Entity.dto.Producto;

import com.example.Food.Store.Entity.Categoria;

public record ProductoDto(
        Long id,
        String nombre,
        String descripcion,
        Double precio,
        String urlImagen,
        Integer stock,
        Boolean activo,
        Categoria categoria
) {
}
