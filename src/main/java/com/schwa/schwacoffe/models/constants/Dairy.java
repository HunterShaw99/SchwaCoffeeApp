package com.schwa.schwacoffe.models.constants;

public enum Dairy {
    WHOLE("Whole"), SKIM("Skim"), _2PERCENT("2%"),
    ALMOND("Almond"), SOY("Soy"), NONE("None");

    private String dairy;

    public String getDairy() {
        return this.dairy;
    }

    Dairy(String dairy) {
        this.dairy = dairy;
    }
}
