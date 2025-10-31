package com.example.Food.Store.Entity.dto.Producto;

public record ProductoCreate(
        String nombre,
        String descripcion,
        Double precio,
        String urlImagen,
        Integer stock,
        Long categoriaId
) {

}
