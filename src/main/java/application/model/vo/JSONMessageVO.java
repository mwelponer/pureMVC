package application.model.vo;

import lombok.Getter;
import lombok.Setter;

public class JSONMessageVO {

    @Getter @Setter
    private int timestamp;
    @Getter @Setter
    private float coordX;
    @Getter @Setter
    private float coordY;
}
