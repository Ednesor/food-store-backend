package com.example.Food.Store.Service;

import com.example.Food.Store.Entity.Categoria;
import com.example.Food.Store.Entity.dto.Categoria.CategoriaCreate;
import com.example.Food.Store.Entity.dto.Categoria.CategoriaDto;
import com.example.Food.Store.Entity.dto.Categoria.CategoriaEdit;
import com.example.Food.Store.Entity.dto.Mapper.CategoriaMapper;
import com.example.Food.Store.Repository.CategoriaRepository;
import com.example.Food.Store.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImp implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public CategoriaDto save(CategoriaCreate c) {
        //Verificamos si ya existe la categoria por nombre
        Categoria categoriaAlreadyExists = categoriaRepository.findByNombre(c.nombre()).orElse(null);
        if (categoriaAlreadyExists != null) {
            throw new IllegalArgumentException("La categoria con nombre " + c.nombre() + " ya existe.");
        }
        //Guardar la categoria
        Categoria categoria = CategoriaMapper.toEntity(c);
        categoria = categoriaRepository.save(categoria);
        return CategoriaMapper.toDto(categoria);
    }

    @Override
    public CategoriaDto update(Long id, CategoriaEdit c) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("No se encontro la categoria con el id " + id));
        CategoriaMapper.updateEntity(categoria, c);
        categoria = categoriaRepository.save(categoria);
        return CategoriaMapper.toDto(categoria);
    }

    @Override
    public CategoriaDto findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("No se encontro la categoria con el id " + id));
        return CategoriaMapper.toDto(categoria);
    }

    @Override
    public List<CategoriaDto> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(CategoriaMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("No se encontro la categoria con el id " + id));

        if (!productoRepository.findByCategoriaId(id).isEmpty()) {
            throw new IllegalStateException("No se puede eliminar la categoría porque tiene productos asociados.");
        }

        categoriaRepository.delete(categoria);
    }
}
