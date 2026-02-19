package dev.api.users.Repository;

import dev.api.users.Models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface I_Repo_Address extends JpaRepository<Address,Long> {

}
