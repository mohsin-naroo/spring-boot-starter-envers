package io.github.meritepk.webapp.audit;

import org.springframework.stereotype.Component;

@Component
public class AuditServiceHolder {

    private static AuditService AUDIT_SERVICE_INSTANCE;

    public AuditServiceHolder(AuditService auditService) {
        AUDIT_SERVICE_INSTANCE = auditService;
    }

    public static AuditService getAuditService() {
        return AUDIT_SERVICE_INSTANCE;
    }
}
