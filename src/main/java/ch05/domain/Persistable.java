package ch05.domain;

import java.time.Instant;

@SuppressWarnings("unused")
public interface Persistable {
    Integer getId();

    void setId(Integer id);

    Instant getCreateTimestamp();

    void setCreateTimestamp(Instant instant);
}
