package ru.tomsknipineft.entities.oilPad;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.tomsknipineft.entities.areaObjects.Vec;
import ru.tomsknipineft.entities.areaObjects.Vvp;
import ru.tomsknipineft.entities.linearObjects.Line;
import ru.tomsknipineft.entities.linearObjects.Road;

import java.time.LocalDate;

/**
Объект, который включает в себя все сооружения инженерной подготовки кустовой площадки
 */
@Data
public class DataFormOilPad {

    @NotNull(message = "The code should not be empty")
    @Size(min = 4, max = 10, message = "Code should be between 4 and 10 characters")
    String codeContract;

    @NotNull(message = "The date should not be empty")
    @FutureOrPresent(message = "The date can not be past")
    LocalDate startContract;

    @Valid
    private BackfillWell backfillWell = new BackfillWell();

    @Valid
    private Road road = new Road();

    @Valid
    private Line line = new Line();

    @Valid
    private Mupn mupn = new Mupn();

    @Valid
    private Vec vec = new Vec();

    @Valid
    private Vvp vvp = new Vvp();
}
