package com.example.Food.Store.Service;

import com.example.Food.Store.Entity.Categoria;
import com.example.Food.Store.Entity.Producto;
import com.example.Food.Store.Entity.dto.Mapper.ProductoMapper;
import com.example.Food.Store.Entity.dto.Producto.ProductoCreate;
import com.example.Food.Store.Entity.dto.Producto.ProductoDto;
import com.example.Food.Store.Repository.CategoriaRepository;
import com.example.Food.Store.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImp implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public ProductoDto save(ProductoCreate p) {
        Producto productoAlreadyExists = productoRepository.findByNombre(p.nombre()).orElse(null);
        if (productoAlreadyExists != null) {
            throw new IllegalArgumentException("El producto con nombre " + p.nombre() + " ya existe.");
        }

        Categoria categoria = categoriaRepository.findById(p.categoriaId()).orElse(null);
        if (categoria == null) {
            throw new IllegalArgumentException("La categoria con id " + p.categoriaId() + " no existe.");
        }

        Producto producto = ProductoMapper.toEntity(p, categoria);
        producto = productoRepository.save(producto);
        return ProductoMapper.toDto(producto);
    }

    @Override
    public ProductoDto findById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("No se encontro el producto con el id " + id));
        return ProductoMapper.toDto(producto);
    }

    @Override
    public List<ProductoDto> findAll() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(ProductoMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public ProductoDto update(Long id, ProductoCreate p) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("No se encontro el producto con el id " + id));
        Categoria categoria = categoriaRepository.findById(p.categoriaId())
                        .orElseThrow(() -> new IllegalArgumentException("La categoria con id " + p.categoriaId() + " no existe."));
        ProductoMapper.updateEntity(producto, p, categoria);
        producto = productoRepository.save(producto);
        return ProductoMapper.toDto(producto);
    }

    @Override
    public ProductoDto updateStatus(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("No se encontro el producto con el id " + id));
        producto.setActivo(!producto.getActivo());
        producto = productoRepository.save(producto);
        return ProductoMapper.toDto(producto);
    }

    @Override
    public List<ProductoDto> findByCategoriaId(Long categoriaId) {
        List<Producto> productos = productoRepository.findByCategoriaId(categoriaId);
        return productos.stream()
                .map(ProductoMapper::toDto)
                .toList();
    }
}
