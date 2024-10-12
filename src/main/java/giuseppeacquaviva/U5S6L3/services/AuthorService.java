package giuseppeacquaviva.U5S6L3.services;

import giuseppeacquaviva.U5S6L3.entities.Author;
import giuseppeacquaviva.U5S6L3.exceptions.BadRequestException;
import giuseppeacquaviva.U5S6L3.exceptions.NotFoundException;
import giuseppeacquaviva.U5S6L3.repositories.AuthorRepository;
import giuseppeacquaviva.U5S6L3.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author save(Author body) {
        authorRepository.findByEmail(body.getEmail()).ifPresent(user -> {
            throw new BadRequestException("L'email " + body.getEmail() + " è già stata utilizzata");
        });
        body.setAvatar("https://ui-avatars.com/api/?name=" + body.getNome() + "+" + body.getCognome());
        return authorRepository.save(body);
    }

    public Page<Author> getAuthors(int page, int size, String sort) {
        return authorRepository.findAll(PageRequest.of(page, size, Sort.by(sort)));
    }

    public Author findById(long id) {
        return authorRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) {
        Author found = this.findById(id);
        authorRepository.delete(found);
    }

    public Author findByIdAndUpdate(long id, Author body) {

        Author found = this.findById(id);
        found.setEmail(body.getEmail());
        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        found.setDataDiNascita(body.getDataDiNascita());
        found.setAvatar(body.getAvatar());
        return authorRepository.save(found);
    }
}
