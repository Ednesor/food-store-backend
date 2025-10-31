package com.example.Food.Store.Service;

import com.example.Food.Store.Entity.dto.Producto.ProductoCreate;
import com.example.Food.Store.Entity.dto.Producto.ProductoDto;

import java.util.List;

public interface ProductoService {
    ProductoDto save(ProductoCreate p);
    ProductoDto findById(Long id);
    List<ProductoDto> findAll();
    void delete(Long id);
    ProductoDto update(Long id, ProductoCreate p);
    ProductoDto updateStatus(Long id);
    List<ProductoDto> findByCategoriaId(Long categoriaId);
}
