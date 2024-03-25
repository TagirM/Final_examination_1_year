package ru.tomsknipineft.entities.oilPad;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tomsknipineft.entities.EntityProject;
import ru.tomsknipineft.entities.ObjectType;

/**
 * Площадка МУПН (мобильная установка подготовки нефти)
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mupns")
public class Mupn implements OilPad, EntityProject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean active;

    @Column(name = "object_type")
    @Enumerated(EnumType.STRING)
    private ObjectType objectType;

    //    площадь отсыпки, га
    @Positive(message = "Сan not be less than 0")
    private Long square;

    //    этап строительства
    @Min(value = 1, message = "Сan not be less than 1")
    private Integer stage;

    //    необходимые ресурсы, чел/дней
    private Integer resource;
}
