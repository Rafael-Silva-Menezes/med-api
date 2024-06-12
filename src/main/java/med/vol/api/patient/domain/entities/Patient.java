package med.vol.api.patient.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.shared.domain.Address;
import med.vol.api.patient.presentation.dto.CreatePatientDto;
import med.vol.api.patient.presentation.dto.UpdatePatientDto;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;
    @Embedded
    private Address address;

    private Boolean active;

    public Patient(CreatePatientDto data) {
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.document = data.document();
        this.address = new Address(data.address());
    }

    public void updatePatientInfo(UpdatePatientDto data) {
        if(data.name() != null) this.name = data.name();
        if(data.phone() != null) this.phone = data.phone();
        if (data.address() != null) this.address.updateAddressInfo(data.address());
    }

    public void toBeInactive() {
        this.active = false;
    }
}
