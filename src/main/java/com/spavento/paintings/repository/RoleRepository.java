package com.spavento.paintings.repository;

import java.util.Optional;

import com.spavento.paintings.models.ERole;
import com.spavento.paintings.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
