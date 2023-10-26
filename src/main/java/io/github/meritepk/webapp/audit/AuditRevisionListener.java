package io.github.meritepk.webapp.audit;

import java.io.Serializable;

import org.hibernate.envers.EntityTrackingRevisionListener;
import org.hibernate.envers.RevisionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditRevisionListener implements EntityTrackingRevisionListener {

    public static final ThreadLocal<AuditRevision> AUDIT = new ThreadLocal<>();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void newRevision(Object revisionEntity) {
        AuditRevision revision = (AuditRevision) revisionEntity;
        revision.setUserId("test"); // SecurityContextHolder.getContext().getAuthentication().getName()
        logger.debug("newRevision() - rev: {}, userId: {}, auditTime: {}", revision.getId(), revision.getUserId(),
                revision.getAuditTime());
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void entityChanged(Class entityClass, String entityName, Serializable entityId, RevisionType revisionType,
            Object revisionEntity) {
        AuditServiceHolder.getAuditService().processEntityChanged(entityClass, entityName, entityId, revisionType,
                revisionEntity);
    }
}
