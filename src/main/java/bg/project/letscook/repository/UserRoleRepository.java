package bg.project.letscook.repository;

import bg.project.letscook.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<RoleEntity, Long> {
}
