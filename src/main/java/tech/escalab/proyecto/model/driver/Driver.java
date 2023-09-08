package tech.escalab.proyecto.model.driver;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.escalab.proyecto.model.truck.Truck;


import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "driver")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Driver {


    /*@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)")
    private String uuid;*/

    @Id
    private UUID uuid = UUID.randomUUID();

   // @Column(name = "code" )
    private String code;

    // @Column(name = "name" )
    private String name;


    // @Column(name = "cellphone" )
    private String cellphone;

    //  @Column(name = "email" )
    private String email;


    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "is_deleted", nullable = false)
    private boolean is_deleted;

    @Column(name = "deleted_at")
    private LocalDateTime deleted_at;

   /* @OneToOne
    @JoinColumn(name = "truck_uuid") // Nombre de la columna en la tabla de conductores que será la llave foránea
    private Truck truck;*/
}
