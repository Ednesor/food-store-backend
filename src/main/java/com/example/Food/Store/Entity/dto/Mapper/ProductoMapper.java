package com.example.Food.Store.Entity.dto.Mapper;

import com.example.Food.Store.Entity.Categoria;
import com.example.Food.Store.Entity.Producto;
import com.example.Food.Store.Entity.dto.Producto.ProductoCreate;
import com.example.Food.Store.Entity.dto.Producto.ProductoDto;

public class ProductoMapper {
    public static Producto toEntity(ProductoCreate p, Categoria categoria) {
        return Producto.builder()
                .nombre(p.nombre())
                .descripcion(p.descripcion())
                .precio(p.precio())
                .urlImagen(p.urlImagen())
                .stock(p.stock())
                .categoria(categoria)
                .build();
    }

    public static ProductoDto toDto (Producto p) {
        if (p == null) return null;
        return new ProductoDto(
                p.getId(),
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getUrlImagen(),
                p.getStock(),
                p.getActivo(),
                p.getCategoria()
        );
    }

    public static void updateEntity(Producto producto, ProductoCreate dto, Categoria categoria) {
        producto.setNombre(dto.nombre());
        producto.setDescripcion(dto.descripcion());
        producto.setPrecio(dto.precio());
        producto.setUrlImagen(dto.urlImagen());
        producto.setStock(dto.stock());
        producto.setCategoria(categoria);
    }
}
