package shift.management.mapper;

import org.mapstruct.Mapper;
import shift.management.entity.User;
import shift.management.model.CreateAccountRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User requestToUser(CreateAccountRequest createAccountRequest);
}
