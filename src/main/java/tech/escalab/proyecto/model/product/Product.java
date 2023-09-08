package tech.escalab.proyecto.model.product;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import tech.escalab.proyecto.model.packet.Packet;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {


    /*@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)")
    private String uuid;*/
    @Id
    private UUID uuid = UUID.randomUUID();

    private String code;
    private double weight;
    private String sku;
    private int quantity;
    private boolean isDeleted;
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "packet_uuid")
    private Packet packet;
}
