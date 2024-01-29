package drawer.server.common.error;

public interface ErrorCode {

    int getStatus();

    String getMessage();

    String getDetail();
}
