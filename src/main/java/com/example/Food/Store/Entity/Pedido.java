package com.example.Food.Store.Entity;

import com.example.Food.Store.Constants.Estado;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pedidos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Pedido extends Base{
    private Double total;

    @Builder.Default
    private Estado estado = Estado.PENDIENTE;

    @Builder.Default
    private LocalDate fecha = LocalDate.now();

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetallePedido> detallePedidos = new ArrayList<>();

}
