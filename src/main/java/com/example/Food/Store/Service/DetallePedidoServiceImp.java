package com.example.Food.Store.Service;

import com.example.Food.Store.Entity.DetallePedido;
import com.example.Food.Store.Entity.Pedido;
import com.example.Food.Store.Entity.Producto;
import com.example.Food.Store.Entity.dto.DetallePedido.DetallePedidoCreate;
import com.example.Food.Store.Entity.dto.DetallePedido.DetallePedidoDto;
import com.example.Food.Store.Entity.dto.DetallePedido.DetalllePedidoEdit;
import com.example.Food.Store.Entity.dto.Mapper.DetallePedidoMapper;
import com.example.Food.Store.Repository.DetallePedidoRepository;
import com.example.Food.Store.Repository.PedidoRepository;
import com.example.Food.Store.Repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoServiceImp implements DetallePedidoService{

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public DetallePedidoDto save(DetallePedidoCreate dto) {
        DetallePedido detallePedido = DetallePedidoMapper.toEntity(dto);
        detallePedido = detallePedidoRepository.save(detallePedido);
        return DetallePedidoMapper.toDto(detallePedido);
    }

    @Override
    public DetallePedidoDto update(Long id, DetalllePedidoEdit dto) {
        DetallePedido detallePedido = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DetallePedido no encontrado con id: " + id));
        DetallePedidoMapper.updateEntity(detallePedido, dto);
        detallePedido = detallePedidoRepository.save(detallePedido);
        return DetallePedidoMapper.toDto(detallePedido);
    }
}
