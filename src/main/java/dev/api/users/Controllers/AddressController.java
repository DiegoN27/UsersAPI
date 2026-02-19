package dev.api.users.Controllers;

import dev.api.users.Models.AddressDTO;
import dev.api.users.Services.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@CrossOrigin(origins = "*")

@Tag(name = "Address", description = "Operaciones relacionadas con direcciones")
@RestController
@RequestMapping("/api")
public class AddressController {
    @Autowired
    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(summary = "Crear una direccion")
    @PostMapping("/users/{userId}/address")
    public AddressDTO createAddress(@PathVariable Long userId, @Valid @RequestBody AddressDTO dto) {
        return addressService.createAddress(userId, dto);
    }

    @Operation(summary = "Obtener las direcciones de un usuario")
    @GetMapping("/users/{userId}")
    public List<AddressDTO> getAddressesByUser(@PathVariable Long userId ){
        return addressService.getAddressesByUser(userId);
    }

    @Operation(summary = "Actualizar una direccion")
    @PutMapping("/address/{addressId}")
    public AddressDTO updateAddress(@PathVariable Long addressID,@Valid @RequestBody AddressDTO dto){
        return addressService.updateAddress(addressID,dto);
    }

    @Operation(summary = "Eliminar una direccion")
    @DeleteMapping("/address/{addressId}")
    public String deleteAddress(@PathVariable Long addressId){
        return addressService.deleteAddress(addressId);
    }
}
