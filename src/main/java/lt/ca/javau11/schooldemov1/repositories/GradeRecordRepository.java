package lt.ca.javau11.schooldemov1.repositories;

import lt.ca.javau11.schooldemov1.entities.GradeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRecordRepository extends JpaRepository<GradeRecord, Long> {
}
