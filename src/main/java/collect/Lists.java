package collect;

import java.util.ArrayList;
import java.util.List;

/**
 * utility of List
 */
public class Lists {

  public static <E> List<E> newArrayList(E... elements) {
    return com.google.common.collect.Lists.newArrayList(elements);
  }


  public static void main(String[] args) {
    List hoge = new ArrayList<>();
    hoge.add("01");
    hoge.add("02");

    System.out.println(hoge.toString());

    List attempt = new ArrayList<>();
    new Lists().setList(hoge, attempt);

    System.out.println(attempt.size());
    System.out.println(attempt.toString());

  }


  private void setList(Object entitlementValueObject, List<String> list) {
    if (isNotNull(entitlementValueObject)) {
      if (entitlementValueObject instanceof String) {
        list.add((String) entitlementValueObject);
      } else if (entitlementValueObject instanceof List) {
        List<String> elist = (List<String>) entitlementValueObject;
        for (String entVal : elist) {
          list.add(entVal);
        }
      }
    }
  }

  private boolean isNotNull(Object obj) {
    return !isNull(obj);
  }

  private boolean isNull(Object obj) {
    return obj == null;
  }

}

