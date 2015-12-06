package pool;

import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;

/**
 * Created by dharada on 2015/12/06.
 */
public class KeyedPooledObjectFactoryImpl implements KeyedPooledObjectFactory {

    @Override
    public PooledObject makeObject(Object key) throws Exception {
        return null;
    }

    @Override
    public void destroyObject(Object key, PooledObject p) throws Exception {

    }

    @Override
    public boolean validateObject(Object key, PooledObject p) {
        return false;
    }

    @Override
    public void activateObject(Object key, PooledObject p) throws Exception {

    }

    @Override
    public void passivateObject(Object key, PooledObject p) throws Exception {

    }
}
