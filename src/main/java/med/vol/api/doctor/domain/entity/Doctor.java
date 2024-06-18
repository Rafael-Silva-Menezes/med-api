package med.vol.api.doctor.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import med.vol.api.doctor.presentation.dto.CreateDoctorDto;
import med.vol.api.doctor.presentation.dto.UpdateDoctorDto;
import med.vol.api.shared.domain.Address;


@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    @Embedded
    private Address address;

    private Boolean active;

    public Doctor(CreateDoctorDto data) {
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.crm = data.crm();
        this.specialization = data.specialization();
        this.address = new Address(data.address());
    }

    public void updateDoctorInfo(UpdateDoctorDto data) {
     if(data.name() != null) this.name = data.name();
     if(data.phone() != null) this.phone = data.phone();
     if (data.address() != null) this.address.updateAddressInfo(data.address());
    }

    public void toBeInactive() {
        this.active = false;
    }
}
