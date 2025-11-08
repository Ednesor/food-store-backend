package com.example.Food.Store.Service;

import com.example.Food.Store.Entity.dto.DetallePedido.DetallePedidoCreate;
import com.example.Food.Store.Entity.dto.DetallePedido.DetallePedidoDto;
import com.example.Food.Store.Entity.dto.DetallePedido.DetalllePedidoEdit;

public interface DetallePedidoService {
    DetallePedidoDto save (DetallePedidoCreate dto);
    DetallePedidoDto update (Long id, DetalllePedidoEdit dto);
}
