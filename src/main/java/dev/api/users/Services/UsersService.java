package dev.api.users.Services;

import dev.api.users.Mappers.UserMapper;
import dev.api.users.Models.Users;
import dev.api.users.Models.UsersDTO;
import dev.api.users.Repository.I_Repo_Address;
import dev.api.users.Repository.I_Repo_Users;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsersService {

    @Autowired
    private I_Repo_Users userRepo;
    private I_Repo_Address addressRepo;


    public List<UsersDTO> serv_Consult(){
        return userRepo.findAll().stream().map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsersDTO serv_findById(Long id){
        Users userByID = userRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("User with id "+id+" not found"));
        return UserMapper.toDTO(userByID);
    }

    public UsersDTO serv_Insert(UsersDTO dto){
        uniqueEmail(dto.getEmail());
        Users user = UserMapper.toEntity(dto);
        Users insert = userRepo.save(user);
        return UserMapper.toDTO(insert);
    }

    public UsersDTO serv_Update(Long id, UsersDTO dto){
        Users user = userRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User with id: " + id + " not found"));
        if(!user.getEmail().equals(dto.getEmail())){
            uniqueEmail(dto.getEmail());
            user.setEmail(dto.getEmail());
        }
        user.setFirstName(dto.getFirstName());
        user.setSureName(dto.getSureName());
        user.setAge(dto.getAge());
        Users updated = userRepo.save(user);
        return UserMapper.toDTO(updated);
    }

    public String serv_Delete(Long id){
        if(userRepo.existsById(id)){
            userRepo.deleteById(id);
            return "User eliminated correctly";
        }else {
            return "User with id: "+id+" not found";
        }
    }

    private void uniqueEmail(String email){
        if(userRepo.existsByEmail(email)){
            throw new RuntimeException("Email already exists");
        }
    }
}
