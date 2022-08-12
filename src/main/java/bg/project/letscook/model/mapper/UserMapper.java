package bg.project.letscook.model.mapper;

import bg.project.letscook.model.dto.UserRegisterDTO;
import bg.project.letscook.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "active", constant = "true")
    UserEntity userDtoToUserEntity(UserRegisterDTO userRegisterDTO);
}
