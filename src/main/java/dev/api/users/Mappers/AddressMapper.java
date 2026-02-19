package dev.api.users.Mappers;

import dev.api.users.Models.Address;
import dev.api.users.Models.AddressDTO;

public class AddressMapper {

    public static AddressDTO toDTO(Address address){
        return new AddressDTO(
                address.getId(),
                address.getStreet(),
                address.getCity(),
                address.getPostalCode(),
                address.getCountry(),
                address.getIsMain()
        );
    }

    public static Address toEntity(AddressDTO dto){
        Address address = new Address();
        address.setId(dto.getId());
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setPostalCode(dto.getPostalCode());
        address.setCountry(dto.getCountry());
        address.setIsMain(dto.getIsMain());

        return address;
    }
}
