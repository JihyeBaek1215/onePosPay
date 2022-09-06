package onepos.external;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PointRepository extends PagingAndSortingRepository<Point, String>{

	List<Point> findByCustomerPhoneNumber(String customerPhoneNumber);
}

