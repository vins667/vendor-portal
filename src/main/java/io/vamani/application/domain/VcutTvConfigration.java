package io.vamani.application.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vcut_tv_configration")
public class VcutTvConfigration {
    @Id
    @SequenceGenerator(name = "vcutTvConfigrationSeq", sequenceName = "vcut_tv_configration_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "vcutTvConfigrationSeq")
    private Long id;

    @Column(name = "line")
    private String line;

    @Column(name = "screen_name")
    private String screenName;

    @Column(name = "screen_duration")
    private Long screenDuration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Long getScreenDuration() {
        return screenDuration;
    }

    public void setScreenDuration(Long screenDuration) {
        this.screenDuration = screenDuration;
    }

    @Override
    public String toString() {
        return "VcutTvConfigration{" +
            "id=" + id +
            ", line='" + line + '\'' +
            ", screenName='" + screenName + '\'' +
            ", screenDuration=" + screenDuration +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutTvConfigration that = (VcutTvConfigration) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(line, that.line) &&
            Objects.equals(screenName, that.screenName) &&
            Objects.equals(screenDuration, that.screenDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, line, screenName, screenDuration);
    }
}
