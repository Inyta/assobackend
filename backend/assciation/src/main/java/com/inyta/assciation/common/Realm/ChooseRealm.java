package com.inyta.assciation.common.Realm;

import java.util.Collection;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.realm.Realm;

/**
 * @Author: zhangwei
 * @Date: 2020/8/17 20:19
 */
public class ChooseRealm extends ModularRealmAuthenticator {

  @Override
  protected AuthenticationInfo doMultiRealmAuthentication(Collection<Realm> realms,
      AuthenticationToken token) {
    // 判断getRealms()是否返回为空
    assertRealmsConfigured();
    // 通过supports()方法，匹配对应的Realm
    Realm uniqueRealm = null;
    for (Realm realm : realms) {
      if (realm.supports(token)) {
        uniqueRealm = realm;
        break;
      }
    }
    if (uniqueRealm == null) {
      throw new UnsupportedTokenException();
    }
    return uniqueRealm.getAuthenticationInfo(token);
  }
}
