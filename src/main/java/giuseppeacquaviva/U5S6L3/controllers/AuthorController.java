package giuseppeacquaviva.U5S6L3.controllers;

import giuseppeacquaviva.U5S6L3.entities.Author;
import giuseppeacquaviva.U5S6L3.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("")
    public Page<Author> getAuthors(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "20") int size,
                                   @RequestParam(defaultValue = "name") String sortBy) {
        return authorService.getAuthors(page, size, sortBy);
    }

    @GetMapping("/{authorId}")
    public Author findById(@PathVariable long authorId) {
        return authorService.findById(authorId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Author saveAuthor(@RequestBody Author body) {
        return authorService.save(body);
    }

    @PutMapping("/{authorId}")
    public Author findByIdAndUpdate(@PathVariable long authorId, @RequestBody Author body) {
        return authorService.findByIdAndUpdate(authorId, body);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable long authorId) {
        authorService.findByIdAndDelete(authorId);
    }

}
