package reflection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzz
 * @date 2019/8/16 15:52
 **/

public class TestReflection {

    public void test() throws InstantiationException, IllegalAccessException {
        List list = create(LinkedList.class, User.class);
        System.out.println(list.size());
    }

    public <C extends List, E> C create(Class<C> collectionClass, Class<E> elementClass)
            throws IllegalAccessException, InstantiationException {
        C collection = collectionClass.newInstance();
        int i = 0;
        while (i++ < 3) {
            E element = elementClass.newInstance();
            collection.add(element);
        }
        return collection;
    }

    public static class User {

        private String name;

        private String account;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }
    }
}
