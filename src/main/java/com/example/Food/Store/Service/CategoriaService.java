package com.example.Food.Store.Service;

import com.example.Food.Store.Entity.dto.Categoria.CategoriaCreate;
import com.example.Food.Store.Entity.dto.Categoria.CategoriaDto;
import com.example.Food.Store.Entity.dto.Categoria.CategoriaEdit;

import java.util.List;

public interface CategoriaService {
    CategoriaDto save(CategoriaCreate c);
    CategoriaDto update(Long id, CategoriaEdit c);
    CategoriaDto findById(Long id);
    List<CategoriaDto> findAll();
    void delete(Long id);
}
