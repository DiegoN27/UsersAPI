package dev.api.users.Controllers;

import dev.api.users.Models.UsersDTO;
import dev.api.users.Services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@CrossOrigin(origins = "*")

@Tag(name = "Users", description = "Operaciones relacionadas con usuarios")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UsersService service;


    @Operation(summary = "Obtener todos los usuarios")
    @GetMapping
    public List<UsersDTO> listUsers(){
        return service.serv_Consult();
    }


    @Operation(summary = "Obtener usuario por ID")
    @GetMapping("/{id}")
    public UsersDTO findUserbyID(@PathVariable Long id){
        return service.serv_findById(id);
    }

    @Operation(summary = "Crear un usuario")
    @PostMapping
    public UsersDTO insertUser(@Valid @RequestBody UsersDTO dto){
        return service.serv_Insert(dto);
    }

    @Operation(summary = "Actualizar un usuario")
    @PutMapping("/{id}")
    public UsersDTO updateUser(@PathVariable Long id, @Valid @RequestBody UsersDTO dto){
        return service.serv_Update(id, dto);
    }

    @Operation(summary = "Eliminar usuario")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        return service.serv_Delete(id);
    }


}
