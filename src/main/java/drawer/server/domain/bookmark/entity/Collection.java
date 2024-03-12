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
@Table(name = "collections")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Collection extends BaseTimeEntity {

    @Id
    @Column(name = "collection_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "collection")
    private List<BookmarkCollection> bookmarkCollection = new ArrayList<>();

    @Embedded private DeleteFlag deleteFlag;

    @Builder
    private Collection(String name, String description, User user) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.user = user;
        this.deleteFlag = new DeleteFlag();
    }

    public static Collection of(String name, String description, User user) {
        return Collection.builder().name(name).description(description).user(user).build();
    }

    public void addBookmark(BookmarkCollection bookmarkCollection) {
        this.bookmarkCollection.add(bookmarkCollection);
    }

    public void removeBookmarkCollection(BookmarkCollection bookmarkCollection) {
        this.bookmarkCollection.remove(bookmarkCollection);
    }

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void delete() {
        this.deleteFlag.softDelete();

        if (!this.bookmarkCollection.isEmpty()) {
            this.bookmarkCollection.forEach(BookmarkCollection::delete);
        }
    }
}
