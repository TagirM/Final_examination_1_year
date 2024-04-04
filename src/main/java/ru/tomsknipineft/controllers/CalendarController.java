package ru.tomsknipineft.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.tomsknipineft.entities.Calendar;
import ru.tomsknipineft.entities.oilPad.DataFormOilPad;
import ru.tomsknipineft.services.CalendarService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    private String codeContract;

    private boolean fieldEngineeringSurvey;

    private boolean engineeringSurveyReport;

    private DataFormOilPad dataFormOilPad;

    private static final Logger logger = LogManager.getLogger(CalendarController.class);
    /**
     * Первоначальная страница приложения с выбором типа объекта проектирования
     */
    @GetMapping
    public String index(){
        return "index";
    }

    /**
     * Страница с выбором объекта проектирования кустовой площадки
     */
    @GetMapping("/oil_pad_object")
    public String oilPadObject(){
        return "oil-pad-object";
    }

    /**
     * Страница с вводом данных по инженерной подготовке куста для формирования календарного плана договора
     */
    @GetMapping("/oil_pad_object/backfill_well")
    public String backfillWellPage(Model model){
        model.addAttribute("dataFormOilPad", new DataFormOilPad());
        return "backfill-well";
    }

    /**
     * Страница с вводом данных по обустройству куста для формирования календарного плана договора
     */
    @GetMapping("/oil_pad_object/oil_well_pad")
    public String oilWellPad(Model model){
//        model.addAttribute("dataFormOilPad", new DataFormOilPad());
        return "oil-well-pad";
    }

    /**
     * Страница с вводом данных по реконструкции куста для формирования календарного плана договора
     */
    @GetMapping("/oil_pad_object/reconstruction_oil_well_pad")
    public String reconstructionOilWellPad(Model model){
//        model.addAttribute("dataFormOilPad", new DataFormOilPad());
        return "reconstruction-oil-well-pad";
    }

    /**
     * Получение данных из страницы ввода данных для формирования календарного плана
     * @param dataFormOilPad класс, содержащий основные данные по объекту инженерная подготовка кустовой площадки
     * @return перенаправление на страницу вывода календарного плана договора
     */
    @PostMapping("/oil_pad_object/backfill_well/create")
    public String createCalendar(@Valid @ModelAttribute("dataFormOilPad") DataFormOilPad dataFormOilPad, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "backfill-well";
        }
        List<Integer> durationsProject = calendarService.getDurationOilPad(dataFormOilPad.getBackfillWell(),
                dataFormOilPad.getRoad(), dataFormOilPad.getLine(),
                dataFormOilPad.getMupn(), dataFormOilPad.getVec(), dataFormOilPad.getVvp(), dataFormOilPad.getCableRack(), dataFormOilPad.getVjk());
        LocalDate date = dataFormOilPad.getStartContract();
        this.codeContract = dataFormOilPad.getCodeContract();
        this.fieldEngineeringSurvey = dataFormOilPad.isFieldEngineeringSurvey();
        this.engineeringSurveyReport = dataFormOilPad.isEngineeringSurveyReport();
        if (fieldEngineeringSurvey){
            engineeringSurveyReport = true;
        }
        calendarService.createCalendar(durationsProject, codeContract, date, dataFormOilPad.getHumanFactor(),
                fieldEngineeringSurvey, engineeringSurveyReport, dataFormOilPad.getDrillingRig());
        this.dataFormOilPad = dataFormOilPad;
        return "redirect:/oil_pad_object/backfill_well/calendar";
    }

    /**
     * Страница с выводом календарного плана договора
     */
    @GetMapping("/oil_pad_object/backfill_well/calendar")
    public String resultCalendar(Model model){
        List<Calendar> calendars = calendarService.getCalendarByCode(codeContract);
        logger.info("Календарь найденный по шифру " + codeContract + " - " + calendars);
        model.addAttribute("calendars", calendars);
        model.addAttribute("codeContract", codeContract);
        model.addAttribute("dataFormOilPad", dataFormOilPad);
        model.addAttribute("fieldEngineeringSurvey", fieldEngineeringSurvey);
        model.addAttribute("engineeringSurveyReport", engineeringSurveyReport);
        return "result-calendar";
    }

    /**
     * Страница с выбором объекта проектирования технологической площадки
     */
    @GetMapping("/tech_object")
    public String techObject(){
        return "tech-object";
    }

    /**
     * Страница с выбором объекта проектирования линейного объекта
     */
    @GetMapping("/linear_object")
    public String linearObject(){
        return "linear-object";
    }

    /**
     * Страница с выбором объекта проектирования объекта энергетики
     */
    @GetMapping("/energy_facility")
    public String energyFacility(){
        return "energy-facility";
    }
}

