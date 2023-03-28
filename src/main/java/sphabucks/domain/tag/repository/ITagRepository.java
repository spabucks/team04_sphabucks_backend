package sphabucks.domain.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.domain.tag.model.Tag;

import java.util.Optional;

public interface ITagRepository extends JpaRepository<Tag, Long> {
    Tag findAllById(Long tagId);
    Optional<Tag> findByName(String name);
}
