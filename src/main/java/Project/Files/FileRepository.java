package Project.Files;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileModel,Integer> {
    List<FileModel> findFileModelsBySessionId(int sessionId);
    FileModel findFileModelByUniqueName(String uniqueName);
}
