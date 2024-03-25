package ru.tomsknipineft.entities.areaObjects;

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
 * Временная вертолетная площадка (ВВП)
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vvps")
public class Vvp implements OilPad, EntityProject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean active;

    @Column(name = "object_type")
    @Enumerated(EnumType.STRING)
    private ObjectType objectType;

    //    для посадки какого вертолета предназначена ВВП
    private String helicopterModel;

    //    наличие зала ожидания
    @Column(name = "hall_exist")
    private boolean hallExist;

    //    площадь отсыпки, га
    @Positive(message = "Сan not be less than 0")
    private Long square;

    //    этап строительства
    @Min(value = 1, message = "Сan not be less than 1")
    private Integer stage;

    //    необходимые ресурсы, чел/дней
    private Integer resource;
}