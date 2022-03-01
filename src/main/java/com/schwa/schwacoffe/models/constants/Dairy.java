package com.schwa.schwacoffe.models.constants;

public enum Dairy {
    WHOLE("Whole"), SKIM("Non-fat"), _2PERCENT("2%"),
    ALMOND("Almond"), SOY("Soy"), NONE("None");

    private String dairy;

    public String getDairy() {
        return this.dairy;
    }

    Dairy(String dairy) {
        this.dairy = dairy;
    }
}
