package pool;

import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;

/**
 *
 */
public class KeyedPooledObjectFactoryImpl implements KeyedPooledObjectFactory<String, Object> {

    @Override
    public PooledObject<java.lang.Object> makeObject(java.lang.String key) throws Exception {

        System.out.println("test");

        return null;
    }

    @Override
    public void destroyObject(java.lang.String key, PooledObject<java.lang.Object> p) throws Exception {

    }

    @Override
    public boolean validateObject(java.lang.String key, PooledObject<java.lang.Object> p) {
        return false;
    }

    @Override
    public void activateObject(java.lang.String key, PooledObject<java.lang.Object> p) throws Exception {

    }

    @Override
    public void passivateObject(java.lang.String key, PooledObject<java.lang.Object> p) throws Exception {

    }
}
