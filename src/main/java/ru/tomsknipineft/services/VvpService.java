package ru.tomsknipineft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tomsknipineft.entities.areaObjects.Vvp;
import ru.tomsknipineft.repositories.VvpRepository;

@Service
@RequiredArgsConstructor
public class VvpService implements EntityProjectService{

    private final VvpRepository vvpRepository;

    /**
     * Поиск в БД количества ресурса необходимого для проектирования
     * @param vvp Временная вертолетная площадка
     * @return количество необходимого ресурса
     */
    public Integer getResourceVvp(Vvp vvp){
        if (vvp.isActive()){
            return vvpRepository.findFirstBySquareGreaterThanEqual(vvp.getSquare()).orElseThrow(()->
                    new RuntimeException("Введено некорректное значение площади " + vvp.getSquare())).getResource();
        }
        return 0;
    }

    /**
     * Получение сущности (Временная вертолетная площадка) из БД
     * @return сущность (Временная вертолетная площадка)
     */
    public Vvp getFirst(){
        return vvpRepository.findById(1L).orElseThrow();
    }
}
