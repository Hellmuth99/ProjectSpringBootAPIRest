package tech.escalab.proyecto.model.packet;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.escalab.proyecto.model.product.Product;
import tech.escalab.proyecto.model.truck.Truck;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "packet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Packet {


   /* @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)")
    private String uuid;*/

    @Id
    private UUID uuid = UUID.randomUUID();

    private String code;
    private double weight;
    private LocalDateTime schedule;
    /*
    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusPacket status;*/

    private StatusPacketEnum status;
    private boolean isDeleted;
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "truck_uuid")
    private Truck truck;

    @OneToMany(mappedBy = "packet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();


    // Método para eliminar un producto del paquete por UUID del producto
    public void removeProduct(UUID productUuid) {
        // Busca el producto en la lista de productos del paquete por su UUID
        Product productToRemove = null;
        for (Product product : products) {
            if (product.getUuid().equals(productUuid)) {
                productToRemove = product;
                break;
            }
        }

        if (productToRemove != null) {
            // Elimina el producto de la lista y establece su referencia de paquete a null
            products.remove(productToRemove);
            productToRemove.setPacket(null);
        }
    }



}
