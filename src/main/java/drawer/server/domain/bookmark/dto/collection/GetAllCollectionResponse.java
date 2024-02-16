package drawer.server.domain.bookmark.dto.collection;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetAllCollectionResponse {

    List<Item> collections;

    public static GetAllCollectionResponse from(List<Item> collections) {
        return GetAllCollectionResponse.builder().collections(collections).build();
    }

    @Getter
    @Builder
    public static class Item {

        private String name;

        private String description;

        public static Item from(String name, String description) {
            return Item.builder().name(name).description(description).build();
        }
    }
}
