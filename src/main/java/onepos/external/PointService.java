
package onepos.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="delivery", url="${api.delivery.url}")
public interface PointService {

    @RequestMapping(method= RequestMethod.POST, path="/points")
    public void cancelship(@RequestBody Point point);

}
