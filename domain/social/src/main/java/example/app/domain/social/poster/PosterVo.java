package example.app.domain.social.poster;

import lombok.Data;

@Data
public class PosterVo implements IPosterVo {
    private Long id;
    private String name;
    private String account;
    private Integer followerCount;
    private Integer followedCount;
}
