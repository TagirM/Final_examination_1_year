package ru.tomsknipineft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tomsknipineft.entities.linearObjects.Road;
import ru.tomsknipineft.repositories.RoadRepository;

@Service
@RequiredArgsConstructor
public class RoadService implements EntityProjectService{

    private final RoadRepository roadRepository;

    /**
     * Поиск в БД количества ресурса необходимого для проектирования
     * @param road Автодорога
     * @return количество необходимого ресурса
     */
    public Integer getResourceRoad(Road road){
        if (road.isActive()){
            Integer durationRoad = roadRepository.findFirstByCategoryAndLengthGreaterThanEqual(road.getCategory(), road.getLength()).orElseThrow(()->
                    new RuntimeException("Введены некорректные значения параметров автодороги " + road.getCategory() + " и " + road.getLength())).getResource();
            if (road.isBridgeExist()){
                return durationRoad + road.getResourceBridge();
            }
            return durationRoad;
        }
        return 0;
    }

    /**
     * Получение сущности (автодорога) из БД
     * @return сущность (автодорога)
     */
    public Road getFirst(){
        return roadRepository.findById(1L).orElseThrow();
    }
}
