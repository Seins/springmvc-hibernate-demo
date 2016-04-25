package cm.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 类名：cm.entity.AnalysisNote
 * 创建者： CM .
 * 创建时间：2016/4/25
 */
@Entity
@Table(name = "analysis_note", schema = "", catalog = "log_db")
public class AnalysisNote {
    private long id;
    private Timestamp logTime;
    private Timestamp noteTime;
    private String module;
    private String noteContent;

    @Basic
    @Column(name = "note_content", nullable = true, insertable = true, updatable = true, length = 500)
    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "log_time", nullable = true, insertable = true, updatable = true)
    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }

    @Basic
    @Column(name = "note_time", nullable = true, insertable = true, updatable = true)
    public Timestamp getNoteTime() {
        return noteTime;
    }

    public void setNoteTime(Timestamp noteTime) {
        this.noteTime = noteTime;
    }

    @Basic
    @Column(name = "module", nullable = true, insertable = true, updatable = true, length = 50)
    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnalysisNote that = (AnalysisNote) o;

        if (id != that.id) return false;
        if (logTime != null ? !logTime.equals(that.logTime) : that.logTime != null) return false;
        if (noteTime != null ? !noteTime.equals(that.noteTime) : that.noteTime != null) return false;
        if (module != null ? !module.equals(that.module) : that.module != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (logTime != null ? logTime.hashCode() : 0);
        result = 31 * result + (noteTime != null ? noteTime.hashCode() : 0);
        result = 31 * result + (module != null ? module.hashCode() : 0);
        return result;
    }
}
