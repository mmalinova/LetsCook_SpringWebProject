package bg.project.letscook.model.mapper;

import bg.project.letscook.model.dto.user.UserProfileDTO;
import bg.project.letscook.model.dto.user.UserRegisterDTO;
import bg.project.letscook.model.entity.CategoryEntity;
import bg.project.letscook.model.entity.UserEntity;
import bg.project.letscook.model.enums.CategoryEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "active", constant = "true")
    UserEntity userDtoToUserEntity(UserRegisterDTO userRegisterDTO);

    UserProfileDTO userEntityToUserProfileDTO(UserEntity userEntity);
}
