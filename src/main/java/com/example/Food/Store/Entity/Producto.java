package com.example.Food.Store.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="productos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Producto extends Base{
    private String nombre;
    private String descripcion;
    private Double precio;
    private String urlImagen;
    private Integer stock;

    @Builder.Default
    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name="categoriaId")
    private Categoria categoria;

}
