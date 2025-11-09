package com.example.Food.Store.Service;

import com.example.Food.Store.Constants.Estado;
import com.example.Food.Store.Entity.DetallePedido;
import com.example.Food.Store.Entity.Pedido;
import com.example.Food.Store.Entity.Producto;
import com.example.Food.Store.Entity.User;
import com.example.Food.Store.Entity.dto.DetallePedido.DetallePedidoCreate;
import com.example.Food.Store.Entity.dto.DetallePedido.DetalllePedidoEdit;
import com.example.Food.Store.Entity.dto.Mapper.DetallePedidoMapper;
import com.example.Food.Store.Entity.dto.Mapper.PedidoMapper;
import com.example.Food.Store.Entity.dto.Pedido.PedidoChangeStatus;
import com.example.Food.Store.Entity.dto.Pedido.PedidoCreate;
import com.example.Food.Store.Entity.dto.Pedido.PedidoDto;
import com.example.Food.Store.Entity.dto.Pedido.PedidoEdit;
import com.example.Food.Store.Repository.DetallePedidoRepository;
import com.example.Food.Store.Repository.PedidoRepository;
import com.example.Food.Store.Repository.ProductoRepository;
import com.example.Food.Store.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImp implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetallePedidoService detallePedidoService;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public PedidoDto save(PedidoCreate p) {

        User usuario = userRepository.findById(p.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + p.usuarioId()));

        // Crear la entidad Pedido (puede venir del mapper)
        Pedido pedido = PedidoMapper.toEntity(p, usuario);

        List<DetallePedido> detalles = new ArrayList<>();

        for (DetallePedidoCreate detalleCreate : p.detallePedidos()) {
            Producto producto = productoRepository.findById(detalleCreate.productoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + detalleCreate.productoId()));

            //verficar stock
            if(detalleCreate.cantidad() <= 0){
                throw new RuntimeException("La cantidad debe ser mayor a cero para el producto: " + producto.getNombre());
            }
            if (producto.getStock() < detalleCreate.cantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            //restar stock
            producto.setStock(producto.getStock() - detalleCreate.cantidad());

            //guardar producto actualizado
            productoRepository.save(producto);

            // construir DetallePedido
            DetallePedido detalle = DetallePedido.builder()
                    .cantidad(detalleCreate.cantidad())
                    .subtotal(detalleCreate.subtotal())
                    .producto(producto)
                    .pedido(pedido) // importante: referencia al padre
                    .build();

            detalles.add(detalle);
        }

        pedido.setDetallePedidos(detalles);

        Pedido saved = pedidoRepository.save(pedido);

        return PedidoMapper.toDto(saved);
    }

    @Override
    public List<PedidoDto> findAllByUsuarioId(Long usuarioId) {
        List<Pedido> pedidos = pedidoRepository.findByUsuarioId(usuarioId);
        return pedidos.stream()
                .map(PedidoMapper::toDto)
                .toList();
    }

    @Override
    public PedidoDto findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
        return PedidoMapper.toDto(pedido);
    }

    @Override
    public List<PedidoDto> findAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream()
                .map(PedidoMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void updateStatusCancel(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));

        if (pedido.getEstado() == Estado.CANCELADO) {
            throw new RuntimeException("El pedido ya está cancelado");
        }

        for (DetallePedido detalle : pedido.getDetallePedidos()) {
            Producto producto = detalle.getProducto();

            // Validar que el producto no sea nulo
            if (producto == null) {
                throw new RuntimeException("El detalle con id " + detalle.getId() + " no tiene producto asociado");
            }

            // Restaurar el stock
            int nuevaCantidad = producto.getStock() + detalle.getCantidad();
            producto.setStock(nuevaCantidad);

            productoRepository.save(producto);
        }

        pedido.setEstado(Estado.CANCELADO);

        pedidoRepository.save(pedido);
    }

    @Override
    public PedidoDto updateStatus(Long id, Estado nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));

        pedido.setEstado(nuevoEstado);

        pedido = pedidoRepository.save(pedido);

        return PedidoMapper.toDto(pedido);
    }

}
