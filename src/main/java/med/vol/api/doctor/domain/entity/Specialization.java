package med.vol.api.doctor.domain.entity;

import java.util.Arrays;

public enum Specialization {
    ORTHOPEDICS("orthopedics"),
    CARDIOLOGY("cardiology"),
    GYNECOLOGY("gynecology"),
    DERMATOLOGY("dermatology");

    private String specializationExternal;

    Specialization(String specialization){
        this.specializationExternal = specialization;
    }

    public static Specialization fromString(String text) {
        return Arrays.stream(values())
                .filter(value -> value.specializationExternal.equalsIgnoreCase(text) || value.name().equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No specialization found for: " + text));
    }
}
