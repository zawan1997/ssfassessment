package ssf.tasks.task.Repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class NewsRepo {

    @Autowired
    @Qualifier("redislab")
    private RedisTemplate<String,String> redisTemplate;

    public void save(String listofarticles, String payload) {
        ValueOperations<String,String> valueOp=redisTemplate.opsForValue();
        valueOp.set(listofarticles, payload);
    }

    public Optional<String> get(String listofarticles){
        ValueOperations<String,String> valueOp = redisTemplate.opsForValue();
        String value = valueOp.get(listofarticles);
        if (null==value) {
            return Optional.empty();
        }
        return Optional.of(value);
    }

    public boolean isValidArticle(String listofarticles, String payload) {
        ValueOperations<String,String> valueOp = redisTemplate.opsForValue();
        String listofarticlesStored = valueOp.get(payload);
        if(listofarticles.equals(listofarticlesStored)){
            return true;
        }
        return false;
    }

    
}
