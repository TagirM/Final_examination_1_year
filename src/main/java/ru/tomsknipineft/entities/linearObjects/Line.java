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
 * ЛЭП
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lines")
public class Line implements OilPad, EntityProject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean active;

    @Column(name = "object_type")
    @Enumerated(EnumType.STRING)
    private ObjectType objectType;

    //    мощность ВЛ (или габариты ВЛ)
    @Positive(message = "Сan not be less than 0")
    private Integer power;

    //    протяженность эстакады
    @Positive(message = "Сan not be less than 0")
    private Integer length;

    //    этап строительства
    @Min(value = 1, message = "Сan not be less than 1")
    private Integer stage;

    //    необходимые ресурсы, чел/дней
    private Integer resource;
}
