package med.vol.api.shared.domain;

import med.vol.api.shared.presentation.AddressDto;
import net.datafaker.Faker;

public class AddressStub {

    private final Faker faker = new Faker();

    public AddressDto createAddressDto() {
        return new AddressDto(
                faker.address().streetAddress() ,
                faker.address().streetName() ,
                faker.address().zipCode().replaceAll("-" , "") ,
                faker.address().city() ,
                faker.address().stateAbbr() ,
                faker.address().buildingNumber() ,
                faker.address().secondaryAddress()
        );
    }
}
