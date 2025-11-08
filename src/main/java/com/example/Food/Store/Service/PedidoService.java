package com.example.Food.Store.Service;

import com.example.Food.Store.Constants.Estado;
import com.example.Food.Store.Entity.dto.Pedido.PedidoChangeStatus;
import com.example.Food.Store.Entity.dto.Pedido.PedidoCreate;
import com.example.Food.Store.Entity.dto.Pedido.PedidoDto;
import com.example.Food.Store.Entity.dto.Pedido.PedidoEdit;

import java.util.List;

public interface PedidoService {
    PedidoDto save(PedidoCreate p);
    PedidoDto findById(Long id);
    List<PedidoDto> findAll();
    void updateStatusCancel(Long id);
    PedidoDto updateStatus(Long id, Estado nuevoEstado);
}
