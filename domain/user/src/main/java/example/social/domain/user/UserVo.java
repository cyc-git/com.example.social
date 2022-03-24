package example.social.domain.user;

import lombok.Data;

@Data
public class UserVo implements IUserVo {
    private Long id;
    private String name;
    private String account;
    private Long createdAt;
    private Long updatedAt;
}
