package dev.api.users.Repository;

import dev.api.users.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface I_Repo_Users extends JpaRepository<Users,Long> {
    boolean existsByEmail(String email);
}
