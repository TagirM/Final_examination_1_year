package ru.tomsknipineft.entities.linearObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Мост автодороги
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BridgeRoad {

//    количество мостов
    private Integer count;

//    общая протяженность мостов, м
    private Integer length;

//    расчет ресурсов для проектирования моста
    public Integer getResourceBridge(){
        return (length/20)*count;
    }
}
