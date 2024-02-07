package drawer.server.domain.bookmark.entity;

import drawer.server.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "collections")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Collection extends BaseTimeEntity {

    @Id
    @Column(name = "collection_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Builder
    private Collection(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static Collection of(String name, String description) {
        return Collection.builder().name(name).description(description).build();
    }

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
