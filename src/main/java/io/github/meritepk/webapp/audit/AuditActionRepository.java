package io.github.meritepk.webapp.audit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditActionRepository extends JpaRepository<AuditAction, Long> {
}
