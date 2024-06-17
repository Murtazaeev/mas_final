package mas.project.repository;

import mas.project.model.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtworkRepository extends JpaRepository<Artwork, UUID> {
}
