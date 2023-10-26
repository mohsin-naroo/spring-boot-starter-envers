package io.github.meritepk.webapp.audit;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.github.meritepk.webapp.news.News;

@Service
public class AuditService {

    private final Logger logger = LoggerFactory.getLogger(AuditService.class);

    private final EntityManager entityManager;
    private final AuditActionRepository auditRepository;

    public AuditService(EntityManager entityManager, AuditActionRepository auditRepository) {
        this.entityManager = entityManager;
        this.auditRepository = auditRepository;
    }

    public AuditReader getAuditReader() {
        return AuditReaderFactory.get(entityManager);
    }

    public void processEntityChanged(Class<?> entityClass, String entityName, Serializable entityId,
            RevisionType revisionType, Object revisionEntity) {
        AuditRevision revision = (AuditRevision) revisionEntity;
        logger.debug("entityChanged() - rev: {}, entityName: {}, entityId: {}, revisionType: {}", revision.getId(),
                entityName, entityId, revisionType);
        if (RevisionType.ADD.equals(revisionType)) {
            if (News.class.equals(entityClass)) {
                String entity = entityName.substring(entityName.lastIndexOf('.') + 1);
                audit(entity, (Long) entityId, "Published", revision.getId(),
                        new Timestamp(revision.getAuditTime()).toLocalDateTime());
            }
        } else if (RevisionType.MOD.equals(revisionType)) {
            if (News.class.equals(entityClass)) {
                String entity = entityName.substring(entityName.lastIndexOf('.') + 1);
                List<News> audit = forRevisionsOfEntity(News.class, entityId);
                int size = audit.size();
                if (size > 1) {
                    News current = audit.get(--size);
                    News previous = audit.get(--size);
                    if (!current.getTitle().equals(previous.getTitle())) {
                        audit(entity, (Long) entityId, "Edited", revision.getId(),
                                new Timestamp(revision.getAuditTime()).toLocalDateTime());
                    }
                }
            }
        }
    }

    private void audit(String entity, Long entityId, String action, Long revisionId, LocalDateTime auditTime) {
        logger.debug(":: {} :: {} - {}, {}", entity, action, entityId, revisionId, auditTime);
        AuditAction audit = new AuditAction();
        audit.setEntityName(entity);
        audit.setEntityAction(action);
        audit.setEntityId(entityId);
        audit.setRevisionId(revisionId);
        audit.setAuditTime(auditTime);
        auditRepository.save(audit);
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> forRevisionsOfEntity(Class<T> entityClass, Serializable entityId) {
        AuditQuery auditQuery = getAuditReader().createQuery().forRevisionsOfEntity(News.class, true, true);
        auditQuery.add(AuditEntity.id().eq(entityId));
        return auditQuery.getResultList();
    }
}
