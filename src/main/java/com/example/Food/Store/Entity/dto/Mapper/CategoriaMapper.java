package com.example.Food.Store.Entity.dto.Mapper;

import com.example.Food.Store.Entity.Categoria;
import com.example.Food.Store.Entity.dto.Categoria.CategoriaCreate;
import com.example.Food.Store.Entity.dto.Categoria.CategoriaDto;
import com.example.Food.Store.Entity.dto.Categoria.CategoriaEdit;

public class CategoriaMapper {
    public static Categoria toEntity(CategoriaCreate c) {
        return Categoria.builder()
                .nombre(c.nombre())
                .descripcion(c.descripcion())
                .urlImagen(c.urlImagen())
                .build();
    }

    public static CategoriaDto toDto(Categoria c) {
        if (c == null) return null;
        return new CategoriaDto(
                c.getId(),
                c.getNombre(),
                c.getDescripcion(),
                c.getUrlImagen()
        );
    }

    public static void updateEntity(Categoria categoria, CategoriaEdit edit) {
        categoria.setNombre(edit.nombre());
        categoria.setDescripcion(edit.descripcion());
        categoria.setUrlImagen(edit.urlImagen());
    }
}

