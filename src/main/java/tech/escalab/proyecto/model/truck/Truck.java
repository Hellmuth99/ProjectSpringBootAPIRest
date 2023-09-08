package tech.escalab.proyecto.model.truck;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.escalab.proyecto.model.driver.Driver;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "truck")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Truck {


    /*@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)")
    private String uuid;*/
    @Id
    private UUID uuid = UUID.randomUUID();

    //  @Column(name = "code" )
    private String code;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "is_deleted", nullable = false)
    private boolean is_deleted;

    @Column(name = "deleted_at")
    private LocalDateTime deleted_at;

    @ManyToOne
    @JoinColumn(name = "driver_uuid")
    private Driver driver;

    /*@OneToOne(mappedBy = "truck",fetch = FetchType.LAZY)
    private Driver driver;*/

}
