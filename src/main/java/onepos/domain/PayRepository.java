package onepos.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PayRepository extends PagingAndSortingRepository<Pay, Integer>{

	Optional<Pay> findByOrderId(Integer orderId);
}

