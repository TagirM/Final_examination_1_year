package ru.tomsknipineft.services;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.tomsknipineft.entities.Calendar;
import ru.tomsknipineft.entities.EntityProject;
import ru.tomsknipineft.entities.ObjectType;
import ru.tomsknipineft.entities.areaObjects.Vec;
import ru.tomsknipineft.entities.areaObjects.Vvp;
import ru.tomsknipineft.entities.linearObjects.Line;
import ru.tomsknipineft.entities.linearObjects.Road;
import ru.tomsknipineft.entities.oilPad.BackfillWell;
import ru.tomsknipineft.entities.oilPad.Mupn;
import ru.tomsknipineft.repositories.CalendarRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс с бизнес-логикой расчета сроков календарного плана договора из входных данных
 */

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;

    private final BackfillWellService backfillWellService;

    private final MupnService mupnService;

    private final VecService vecService;

    private final VvpService vvpService;

    private final RoadService roadService;

    private final LineService lineService;

    private static final Logger logger = LogManager.getLogger(CalendarService.class);

    /**
     * Получение всего списка календарных планов (различных этапов строительства) по шифру договора
     * @param code шифр договора
     * @return список календарных планов по различным этапам строительства
     */
    public List<Calendar> getCalendarByCode(String code) {
        return calendarRepository.findCalendarByCodeContract(code)
                .orElseThrow(() -> new RuntimeException("Calendar by code " + code + " is absent"));
    }

    /**
     * Создание календарного плана договора с учетом всех этапов строительства
     * @param getDurationsProject список продолжительности проектирования всех этапов строительтсва по договору
     * @param codeContract шифр договора
     * @param startContract дата начала работ
     */
    public void createCalendar(List<Integer> getDurationsProject, String codeContract, LocalDate startContract) {
        int nextStage = 0;
        for (int i = 0; i < getDurationsProject.size(); i++) {
            Calendar calendar = new Calendar();
            startContract = startContract.plusDays(nextStage);
            int engineeringSurveyDuration = 30;
            int engineeringSurveyReportDuration = engineeringSurveyDuration + 90;
            int agreementEngineeringSurveyDuration = engineeringSurveyReportDuration + 60;
            int workingDuration = getDurationsProject.get(i) + engineeringSurveyReportDuration;
            int projectDuration = workingDuration + 30;
            int estimatesDuration = workingDuration + 30;
            int landDuration = projectDuration + 150;
            int agreementWorkingDuration = workingDuration + 60;
            int agreementProjectDuration = projectDuration + 60;
            int agreementEstimatesDuration = estimatesDuration + 60;
            int examinationDuration = agreementProjectDuration + 90;

            calendar.setCodeContract(codeContract).setStartContract(startContract)
                    .setStage(i+1)
                    .setEngineeringSurvey(startContract.plusDays(engineeringSurveyDuration))
                    .setEngineeringSurveyReport(startContract.plusDays(engineeringSurveyReportDuration))
                    .setAgreementEngineeringSurvey(startContract.plusDays(agreementEngineeringSurveyDuration))
                    .setWorkingFinish(startContract.plusDays(workingDuration))
                    .setEstimatesFinish(startContract.plusDays(estimatesDuration))
                    .setProjectFinish(startContract.plusDays(projectDuration))
                    .setLandFinish(startContract.plusDays(landDuration))
                    .setAgreementWorking(startContract.plusDays(agreementWorkingDuration))
                    .setAgreementProject(startContract.plusDays(agreementProjectDuration))
                    .setAgreementEstimates(startContract.plusDays(agreementEstimatesDuration))
                    .setExamination(startContract.plusDays(examinationDuration));
            System.out.println("Тестовая проверка создания календаря " + calendar);
            if (i==0 && calendarRepository.findCalendarByCodeContract(codeContract).isPresent()){
                calendarRepository.deleteAll(getCalendarByCode(codeContract));
            }
            calendarRepository.save(calendar);
            logger.info("Эта запись будет залогирована");
            nextStage+=engineeringSurveyDuration;
        }
    }

    /**
     * Получение списка сроков проектирования объекта по этапам его строительства
     * @param backfillWell Кустовая площадка
     * @param road Автодорога
     * @param line ЛЭП
     * @param mupn площадка МУПН
     * @param vec ВЭЦ
     * @param vvp Временная вертолетная площадка
     * @return список сроков проектирования объекта по этапам его строительства
     */
    public List<Integer> getDurationOilPad(BackfillWell backfillWell, Road road, Line line, Mupn mupn, Vec vec, Vvp vvp) {
        List<EntityProject> objects = listActiveEntityProject(List.of(backfillWell, road, line, mupn, vec, vvp));

        List<Integer> durationsProject = new ArrayList<>();

        int stage = defineStageProject(objects);
        Map<Integer, Integer> divisionDurationByStage = new HashMap<>();
        for (EntityProject oilPad :
                objects) {
            if (oilPad.getObjectType().equals(ObjectType.AREA)) {
                if (divisionDurationByStage.containsKey(oilPad.getStage())) {
                    divisionDurationByStage.put(oilPad.getStage(), divisionDurationByStage.get(oilPad.getStage()) + resourceStageOilPad(oilPad));
                } else {
                    divisionDurationByStage.put(oilPad.getStage(), resourceStageOilPad(oilPad));
                }
            }
        }
        for (EntityProject oilPad :
                objects) {
            if (oilPad.getObjectType().equals(ObjectType.LINEAR)) {
                if (!divisionDurationByStage.containsKey(oilPad.getStage())) {
                    divisionDurationByStage.put(oilPad.getStage(), resourceStageOilPad(oilPad));
                } else {
                    if (divisionDurationByStage.get(oilPad.getStage()) < resourceStageOilPad(oilPad)) {
                        divisionDurationByStage.put(oilPad.getStage(), resourceStageOilPad(oilPad));
                    }
                }
            }
        }
        System.out.println(divisionDurationByStage);
        for (int i = 1; i <= stage; i++) {
            durationsProject.add(divisionDurationByStage.get(i));
        }
        return durationsProject;
    }

    /**
     * Получение общего количества этапов строительства всего объекта проектирования по договору
     * @param entityProjects сооружение (сущность) объекта проектирования
     * @return общее количество этапов строительства объекта
     */
    public Integer defineStageProject(List<EntityProject> entityProjects) {
        int stage = 0;
        for (EntityProject entity :
                entityProjects) {
            if (entity.getStage() > stage) {
                stage = entity.getStage();
            }
        }
        logger.info("Эта запись будет залогирована тоже");
        return stage;
    }

    /**
     * Получение количества ресурса, необходимого для проектирования сущности (сооружения) всего объекта проектирования
     * @param oilPad сущность объекта кустовой площадки
     * @return количество ресурса, необходимого для проектирования сущности
     */
    public Integer resourceStageOilPad(EntityProject oilPad) {
        int durationStage = 0;
        if (oilPad.getClass() == BackfillWell.class) {
            durationStage += backfillWellService.getResourceBackfillWell((BackfillWell) oilPad);

        } else if (oilPad.getClass() == Road.class) {
            durationStage += roadService.getResourceRoad((Road) oilPad);

        } else if (oilPad.getClass() == Line.class) {
            durationStage += lineService.getResourceLine((Line) oilPad);

        } else if (oilPad.getClass() == Mupn.class) {
            durationStage += mupnService.getResourceMupn((Mupn) oilPad);

        } else if (oilPad.getClass() == Vec.class) {
            durationStage += vecService.getResourceVec((Vec) oilPad);
        } else if (oilPad.getClass() == Vvp.class) {
            durationStage += vvpService.getResourceVvp((Vvp) oilPad);
        }
        return durationStage;
    }

    /**
     * Получение списка только активных сущностей (сооружений) объекта проектирования из представления
     * @param entityProjects сущность (сооружение) объекта проектирования
     * @return список активных сущностей (сооружений)
     */
    public List<EntityProject> listActiveEntityProject(List<EntityProject> entityProjects) {
        List<EntityProject> objects = new ArrayList<>();
        for (EntityProject entity :
                entityProjects) {
            if (entity.isActive()) {
                if (entity.getStage()==null){
                    entity.setStage(1);
                }
                if (entity.getClass() == BackfillWell.class) {
                    entity.setObjectType(backfillWellService.getFirst().getObjectType());
                    objects.add(entity);
                } else if (entity.getClass() == Road.class) {
                    entity.setObjectType(roadService.getFirst().getObjectType());
                    objects.add(entity);
                } else if (entity.getClass() == Line.class) {
                    entity.setObjectType(lineService.getFirst().getObjectType());
                    objects.add(entity);
                } else if (entity.getClass() == Mupn.class) {
                    entity.setObjectType(mupnService.getFirst().getObjectType());
                    objects.add(entity);
                } else if (entity.getClass() == Vec.class) {
                    entity.setObjectType(vecService.getFirst().getObjectType());
                    ((Vec) entity).setPower(vecService.getFirst().getPower());
                    objects.add(entity);
                } else if (entity.getClass() == Vvp.class) {
                    entity.setObjectType(vvpService.getFirst().getObjectType());
                    objects.add(entity);
                }
            }
        }
        return objects;
    }
}
