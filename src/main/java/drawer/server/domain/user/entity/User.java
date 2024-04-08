package drawer.server.domain.user.entity;

import drawer.server.common.entity.BaseTimeEntity;
import drawer.server.domain.user.constant.RoleType;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Long id;

    @Column(updatable = false, nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private RoleType role;

    @Column(nullable = false)
    private String password;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Builder
    private User(RoleType role, String email, String password) {
        this.uuid = UUID.randomUUID().toString();
        this.role = role;
        this.email = email;
        this.password = password;
        this.profileImageUrl = null;
    }

    public static User of(RoleType role, String email, String password) {
        return User.builder().role(role).email(email).password(password).build();
    }

    public void updateProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void updateEmail(String email) {
        this.email = email;
    }
}
