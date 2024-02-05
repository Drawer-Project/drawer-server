package drawer.server.domain.bookmark.entity;

import drawer.server.common.entity.BaseTimeEntity;
import drawer.server.common.valuetype.DeleteFlag;
import drawer.server.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "bookmarks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark extends BaseTimeEntity {

    @Id
    @Column(name = "bookmark_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "bookmark")
    private BookmarkCollection bookmarkCollection;

    @Embedded private DeleteFlag deleteFlag;

    @Builder
    private Bookmark(String url, String title, User user, BookmarkCollection bookmarkCollection) {
        this.url = url;
        this.title = title;
        this.user = user;
        this.bookmarkCollection = bookmarkCollection;
        this.deleteFlag = new DeleteFlag();
    }

    public static Bookmark of(String url, String title, User user) {
        return Bookmark.builder().url(url).title(title).user(user).build();
    }

    public void addToCollection(BookmarkCollection bookmarkCollection) {
        this.bookmarkCollection = bookmarkCollection;
    }

    public void update(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public void delete() {
        this.deleteFlag.softDelete();

        if (this.bookmarkCollection != null) {
            this.bookmarkCollection.delete();
        }
    }
}
