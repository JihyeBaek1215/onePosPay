package onepos;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PayRepository extends PagingAndSortingRepository<Pay, Integer>{

	List<Pay> findByOrderId(Integer orderId);
}
