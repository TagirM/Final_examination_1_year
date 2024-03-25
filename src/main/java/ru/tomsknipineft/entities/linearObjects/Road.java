package ru.tomsknipineft.entities.linearObjects;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tomsknipineft.entities.EntityProject;
import ru.tomsknipineft.entities.ObjectType;
import ru.tomsknipineft.entities.oilPad.OilPad;

/**
 * Автомобильная дорога
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roads")
public class Road extends BridgeRoad  implements OilPad, EntityProject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean active;

    @Column(name = "object_type")
    @Enumerated(EnumType.STRING)
    private ObjectType objectType;

    //    есть ли мост у дороги
    @Column(name = "bridge_exist")
    private boolean bridgeExist;

    //    категория дороги
    private String category;

    //    протяженность дороги
    @Positive(message = "Сan not be less than 0")
    private Integer length;

    //    этап строительства
    @Min(value = 1, message = "Сan not be less than 1")
    private Integer stage;

    //    необходимые ресурсы, чел/дней
    private Integer resource;

    public Road(String category, Integer length, Integer resource) {
        this.category = category;
        this.length = length;
        this.resource = resource;
    }
}
