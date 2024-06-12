package med.vol.api.shared.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.shared.presentation.AddressDto;

import java.lang.reflect.Field;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    private String street;
    private String neighborhood;
    @Column(name = "zip_code")
    private String zipCode;
    private String city;
    private String state;
    @Column(name = "house_number")
    private String number;
    private String complement;

    public Address(AddressDto data) {
        this.city = data.city();
        this.street = data.street();
        this.neighborhood = data.neighborhood();
        this.zipCode = data.zipCode();
        this.state = data.state();
        this.number = data.number();
        this.complement = data.complement();
    }

    public void updateAddressInfo(AddressDto updateData) {
        Class<?> classAddress = getClass();
        for (Field field : classAddress.getDeclaredFields()) {
            try {
                Field dataField = updateData.getClass().getDeclaredField(field.getName());
                dataField.setAccessible(true);
                Object value = dataField.get(updateData);
                if (value != null) {
                    field.setAccessible(true);
                    field.set(this, value);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Error during access field", e);
            }
        }
    }
}
