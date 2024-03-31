package ru.tomsknipineft.entities.linearObjects;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.group.GroupSequenceProvider;
import ru.tomsknipineft.entities.EntityProject;
import ru.tomsknipineft.entities.ObjectType;
import ru.tomsknipineft.entities.oilPad.OilPad;
import ru.tomsknipineft.utils.entityValidator.OnActiveBridgeRoad;
import ru.tomsknipineft.utils.entityValidator.OnActiveCheck;
import ru.tomsknipineft.utils.entityValidator.RoadGroupSequenceProvider;

/**
 * Автомобильная дорога
 */
@GroupSequenceProvider(RoadGroupSequenceProvider.class)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roads")
public class Road implements OilPad, EntityProject {

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

    @Column(name = "bridge_road_count")
    @NotNull(message = "Количество не заполнено", groups = OnActiveBridgeRoad.class)
    @Positive(message = "Количество не может быть отрицательным", groups = OnActiveBridgeRoad.class)
    private Integer bridgeRoadCount;

    @Column(name = "bridge_road_length")
    @NotNull(message = "Длина моста не заполнена", groups = OnActiveBridgeRoad.class)
    @Positive(message = "Длина моста не может быть отрицательной", groups = OnActiveBridgeRoad.class)
    private Integer bridgeRoadLength;

    //    категория дороги
    @NotNull(message = "Категория не заполнена", groups = OnActiveCheck.class)
    @Size(min = 1, max = 2, message = "Категория в интервале 1-2 символов", groups = OnActiveCheck.class)
    private String category;

    //    протяженность дороги
    @NotNull(message = "Длина не заполнена", groups = OnActiveCheck.class)
    @Positive(message = "Длина не может быть отрицательной", groups = OnActiveCheck.class)
    private Integer length;

    //    этап строительства
    @Min(value = 1, message = "Не может быть меньше 1", groups = OnActiveCheck.class)
    private Integer stage;

    //    необходимые ресурсы, чел/дней
    private Integer resource;

    public Road(String category, Integer length, Integer resource) {
        this.category = category;
        this.length = length;
        this.resource = resource;
    }

    public Integer getResourceBridge(){
        return (bridgeRoadLength/20)*bridgeRoadCount;
    }
}
