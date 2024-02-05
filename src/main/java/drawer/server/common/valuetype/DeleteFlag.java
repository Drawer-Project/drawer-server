package drawer.server.common.valuetype;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Embeddable
@ToString
@EqualsAndHashCode(of = "isDeleted")
public class DeleteFlag {
    private boolean isDeleted;

    public DeleteFlag() {
        this.isDeleted = false;
    }

    public void softDelete() {
        this.isDeleted = true;
    }
}
