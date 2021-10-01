package option;

import com.graduate.mobilekiosk.domain.OptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OptionGroupRepository extends JpaRepository<OptionGroup, Long> {
}
