package com.example.image_api.imagaa.feign;

import com.example.image_api.client.imagaa.api.ImagaaApi;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "ImagaaFeignClient", url = "https://api.imagga.com/v2", configuration = ImagaaFeignClientConfiguration.class)
public interface ImagaaFeignClient extends ImagaaApi{

}

