package option;


import com.graduate.mobilekiosk.domain.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OptionRepository extends JpaRepository<Option, Long> {
}
