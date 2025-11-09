package com.example.Food.Store.Entity;

import com.example.Food.Store.Constants.Estado;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;
}
