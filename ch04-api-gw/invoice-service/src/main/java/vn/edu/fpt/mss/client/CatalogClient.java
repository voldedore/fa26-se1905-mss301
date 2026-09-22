package vn.edu.fpt.mss.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.edu.fpt.mss.dto.client.TrackInfo;

@FeignClient(name = "catalog-service", url = "${app.clients.catalog-url}")
public interface CatalogClient {

    @GetMapping("/api/v1/tracks/{id}")
    TrackInfo getTrack(@PathVariable("id") Integer id);
}