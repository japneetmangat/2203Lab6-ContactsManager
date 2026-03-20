package Lab6.Exercise3.service;

import Lab6.Exercise3.domain.Contact;
import Lab6.Exercise3.repo.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository repository;

    @Autowired
    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public List<Contact> findAll() {
        return repository.findAll();
    }

    public Contact save (Contact contact) {
        return repository.save(contact);
    }

    public void delete(Contact contact) {
        repository.delete(contact);
    }

    public long count(){
        return repository.count();
    }
}
