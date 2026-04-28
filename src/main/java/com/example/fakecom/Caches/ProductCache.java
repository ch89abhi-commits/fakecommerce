package  com.example.fakecom.Caches;

import java.time.Duration;
import java.util.Objects;
import  java.util.Optional;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.example.fakecom.schema.ProductSchema;

import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class ProductCache{

    private final StringRedisTemplate stringRedisTemplate;

    private final ObjectMapper objectMapper;

    private static  final String PRODUCT_REDIS_KEY ="Product:"; 

    private static final Duration PRODUCT_REDIS_TTL=Duration.ofMinutes(2);


    public Optional<ProductSchema> getCache(Long id){

        System.out.println("Triggering the cache for the data");

        String data=stringRedisTemplate.opsForValue().get(PRODUCT_REDIS_KEY+id);
        if(Objects.isNull(data)) {
            System.out.println("Cache miss now  database  is hitting for the response");
            return Optional.empty();}

        try{
            ProductSchema ct=objectMapper.readValue(data, ProductSchema.class);
            System.out.println("Cache hit the redis is serving the response");
            return Optional.of(ct);

        }
        catch(Exception e){
           System.out.println("e");
           System.out.println(e.getMessage());
           stringRedisTemplate.delete(PRODUCT_REDIS_KEY+id);
            return Optional.empty();
        }


    }

    public void putCache(Long id,ProductSchema data){
        try {
 
            stringRedisTemplate.opsForValue().set(PRODUCT_REDIS_KEY+id, objectMapper.writeValueAsString(data),PRODUCT_REDIS_TTL);
            System.out.println("Putting data into the cache to get the output the data");
            
        } catch (Exception e) {
            System.out.println("e");
            System.out.println(e.getMessage());
              

        }


    }


    

}