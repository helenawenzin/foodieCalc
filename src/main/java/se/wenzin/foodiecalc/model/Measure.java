package se.wenzin.foodiecalc.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Measure {

    LITER("l", "liter"),
    DL("dl", "deciliter"),
    MSK("msk", "matsked"),
    TSK("tsk", "tesked"),
    KRM("krm", "kryddmått"),
    NYPA("nypa", "nypa");

    private final String shortName;
    private final String longName;

}
