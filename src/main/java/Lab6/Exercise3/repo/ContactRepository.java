package Lab6.Exercise3.repo;

import Lab6.Exercise3.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {}
