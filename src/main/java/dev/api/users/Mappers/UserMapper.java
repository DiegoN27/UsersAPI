package dev.api.users.Mappers;
import dev.api.users.Models.Users;
import dev.api.users.Models.UsersDTO;

public class UserMapper {

    public static UsersDTO toDTO(Users user){
        return new UsersDTO(
                user.getId(),
                user.getFirstName(),
                user.getSureName(),
                user.getEmail(),
                user.getAge()
        );
    }

    public static Users toEntity(UsersDTO dto){
        Users user = new Users();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setSureName(dto.getSureName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        return user;
    }

}
