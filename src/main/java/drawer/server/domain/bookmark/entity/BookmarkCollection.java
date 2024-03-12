package drawer.server.domain.bookmark.entity;

import drawer.server.common.entity.BaseTimeEntity;
import drawer.server.common.valuetype.DeleteFlag;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Table(name = "bookmark_collection")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookmarkCollection extends BaseTimeEntity {

    @Id
    @Column(name = "bookmark_collection_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookmark_id")
    private Bookmark bookmark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @Embedded private DeleteFlag deleteFlag;

    @Builder
    private BookmarkCollection(Bookmark bookmark, Collection collection) {
        this.bookmark = bookmark;
        this.collection = collection;
        this.deleteFlag = new DeleteFlag();
    }

    public static BookmarkCollection of(Bookmark bookmark, Collection collection) {
        BookmarkCollection bookmarkCollection =
                BookmarkCollection.builder().bookmark(bookmark).collection(collection).build();

        bookmark.addToCollection(bookmarkCollection);
        collection.addBookmark(bookmarkCollection);

        return bookmarkCollection;
    }

    public void delete() {
        this.deleteFlag.softDelete();
        this.bookmark.removeFromBookmarkCollection(this);
        this.collection.removeBookmarkCollection(this);
    }
}
