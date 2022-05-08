package platform.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.business.entity.Snippet;

import java.util.List;
import java.util.Optional;

@Repository
public interface SnippetRepository extends CrudRepository<Snippet, Long> {

    Optional<Snippet> findSnippetById(Long id);
    List<Snippet> findFirst10ByOrderByDateDesc();
}