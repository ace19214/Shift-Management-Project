package shift.management.response;

import lombok.Data;
import shift.management.entity.User;
import shift.management.entity.UserShift;

@Data
public class UserOnShiftResponse {
    UserShift userShift;
    User user;
}
