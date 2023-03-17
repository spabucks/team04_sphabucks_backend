package sphabucks.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.tag.model.Tag;

import java.util.List;
import java.util.Optional;

public interface ITagRepository extends JpaRepository<Tag, Long> {
    Tag findAllById(Long tagId);
    Optional<Tag> findByName(String name);
}
