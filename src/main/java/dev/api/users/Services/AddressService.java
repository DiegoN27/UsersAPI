package dev.api.users.Services;

import dev.api.users.Mappers.AddressMapper;
import dev.api.users.Models.Address;
import dev.api.users.Models.AddressDTO;
import dev.api.users.Models.Users;
import dev.api.users.Repository.I_Repo_Address;
import dev.api.users.Repository.I_Repo_Users;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AddressService {
    @Autowired
    private final I_Repo_Address addressRepo;
    private final I_Repo_Users userRepo;

    public AddressService(I_Repo_Address addressRepo,
                          I_Repo_Users userRepo) {
        this.addressRepo = addressRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public AddressDTO createAddress(Long userId, AddressDTO dto) {

        Users user = userRepo.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User with Id: " + userId + " not found"));

        Address address = AddressMapper.toEntity(dto);

        isMainNull(address);
        hasMainAddress(address, user);

        address.setUser(user);

        try {
            addressRepo.saveAndFlush(address);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("This user already has this address");
        }

        return AddressMapper.toDTO(address);
    }

    public List<AddressDTO> getAddressesByUser(Long userId) {

        Users user = userRepo.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User with id " + userId + " not found"));

        return user.getAddresses()
                .stream()
                .map(AddressMapper::toDTO)
                .toList();
    }

    public String deleteAddress(Long addressId) {

        Address address = addressRepo.findById(addressId)
                .orElseThrow(() ->
                        new RuntimeException("Address with id " + addressId + " not found"));

        addressRepo.delete(address);
        return "Address eliminated correctly";
    }

    @Transactional
    public AddressDTO updateAddress(Long addressId,AddressDTO dto){
        Address address = addressRepo.findById(addressId)
                .orElseThrow(() ->
                        new RuntimeException("Address with id " + addressId + " not found"));


        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setPostalCode(dto.getPostalCode());
        address.setCountry(dto.getCountry());
        address.setIsMain(dto.getIsMain());
        isMainNull(address);
        hasMainAddress(address,address.getUser());

        return AddressMapper.toDTO(address);
    }



    public void isMainNull(Address address){
        if (address.getIsMain()==null){
            address.setIsMain(false);
        }
    }

    public void hasMainAddress(Address address, Users user){
        if (address.getIsMain()==true){
            for (Address current : user.getAddresses()){
                if (!current.equals(address)) {current.setIsMain(false);}
            }
        }
    }


}
