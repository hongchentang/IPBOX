package com.hcis.ipanther.common.privilege.shiro.concurrent;

import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class ConcurrentAccessService
{

  @Resource
  private MemcachedClient memcachedClient;

  @Resource
  private ConcurrentAccessConfig concurrentAccessConfig;
  private String cacheKeyPrefix = "ca_";

  public ConcurrentAccessConfig getConcurrentAccessConfig() {
    return this.concurrentAccessConfig;
  }

  public void setConcurrentAccessConfig(ConcurrentAccessConfig concurrentAccessConfig)
  {
    this.concurrentAccessConfig = concurrentAccessConfig;
  }

  public void setCacheKeyPrefix(String cacheKeyPrefix) {
    this.cacheKeyPrefix = cacheKeyPrefix;
  }

  public void setMemcachedClient(MemcachedClient memcachedClient) {
    this.memcachedClient = memcachedClient;
  }

  public boolean isConcurrentAccessAllowed(HttpServletRequest request) {
    String accountId = ObjectUtils.toString(request.getSession().getAttribute(this.concurrentAccessConfig.getAccountIdKey()));
    String sessionId = request.getSession().getId();
    if (StringUtils.isEmpty(accountId))
      return false;
    try
    {
      Set concurrentUserSet = (Set)this.memcachedClient.get(this.cacheKeyPrefix + accountId);
      if ((concurrentUserSet != null) && (!concurrentUserSet.contains(sessionId)))
        return false;
    }
    catch (TimeoutException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (MemcachedException e) {
      e.printStackTrace();
    }
    return true;
  }

  public void onLoginSuccess(String accountId, HttpServletRequest request) {
    request.getSession().setAttribute(this.concurrentAccessConfig.getAccountIdKey(), accountId);
    String sessionId = request.getSession().getId();
    if (StringUtils.isNotEmpty(accountId))
      try
      {
        Set concurrentUserSet = (Set)this.memcachedClient.get(this.cacheKeyPrefix + accountId);
        if (concurrentUserSet == null) {
          concurrentUserSet = Sets.newHashSet();
        }
        if (concurrentUserSet.size() < this.concurrentAccessConfig.getMaxConcurrentAccess()) {
          concurrentUserSet.add(sessionId);
        }
        else if (this.concurrentAccessConfig.getMaxConcurrentAccess() == 1) {
          concurrentUserSet.clear();
          concurrentUserSet.add(sessionId);
        } else {
          try {
            String removedSessionId = (String)concurrentUserSet.iterator().next();
            if (removedSessionId != null) {
              concurrentUserSet.remove(removedSessionId);
            }
            concurrentUserSet.add(sessionId);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }

        this.memcachedClient.set(this.cacheKeyPrefix + accountId, 1800, concurrentUserSet);
      } catch (TimeoutException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (MemcachedException e) {
        e.printStackTrace();
      }
  }

  public void onLogout(String accountId, String sessionId)
  {
    try
    {
      Set concurrentUserSet = (Set)this.memcachedClient.get(this.cacheKeyPrefix + accountId);
      if (concurrentUserSet != null) {
        concurrentUserSet.remove(sessionId);
        this.memcachedClient.set(this.cacheKeyPrefix + accountId, 1800, concurrentUserSet);
      }
    } catch (TimeoutException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (MemcachedException e) {
      e.printStackTrace();
    }
  }
}