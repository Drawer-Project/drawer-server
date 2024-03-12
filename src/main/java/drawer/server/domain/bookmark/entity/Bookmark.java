package drawer.server.domain.bookmark.entity;

import drawer.server.common.entity.BaseTimeEntity;
import drawer.server.common.valuetype.DeleteFlag;
import drawer.server.domain.user.entity.User;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.*;

@Entity
@Getter
@ToString
@Table(name = "bookmarks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark extends BaseTimeEntity {

    @Id
    @Column(name = "bookmark_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, nullable = false, unique = true)
    private String uuid;

    private String url;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "bookmark")
    private List<BookmarkCollection> bookmarkCollection = new ArrayList<>();

    @Embedded private DeleteFlag deleteFlag;

    @Builder
    private Bookmark(String url, String title, User user) {
        this.uuid = UUID.randomUUID().toString();
        this.url = url;
        this.title = title;
        this.user = user;
        this.deleteFlag = new DeleteFlag();
    }

    public static Bookmark of(String url, String title, User user) {
        return Bookmark.builder().url(url).title(title).user(user).build();
    }

    public void addToCollection(BookmarkCollection bookmarkCollection) {
        this.bookmarkCollection.add(bookmarkCollection);
    }

    public void removeFromBookmarkCollection(BookmarkCollection bookmarkCollection) {
        this.bookmarkCollection.remove(bookmarkCollection);
    }

    public void update(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public void delete() {
        this.deleteFlag.softDelete();

        if (!this.bookmarkCollection.isEmpty()) {
            this.bookmarkCollection.forEach(BookmarkCollection::delete);
        }
    }
}
