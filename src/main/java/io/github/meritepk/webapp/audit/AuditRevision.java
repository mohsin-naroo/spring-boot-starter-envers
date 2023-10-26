package io.github.meritepk.webapp.audit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity
@RevisionEntity(AuditRevisionListener.class)
public class AuditRevision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private Long id;

    private String userId;

    @RevisionTimestamp
    private Long auditTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Long auditTime) {
        this.auditTime = auditTime;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
