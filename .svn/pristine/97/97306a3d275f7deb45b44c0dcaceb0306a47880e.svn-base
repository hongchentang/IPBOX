package com.hcis.ipanther.common.privilege.shiro;

import com.hcis.ipanther.common.privilege.entity.Permission;
import com.hcis.ipanther.common.privilege.service.IPermissionService;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.StringUtils;

public class ChainDefinitionSectionMetaSource
  implements FactoryBean<Ini.Section>
{
  public static final String PREMISSION_STRING = "perms[\"{0}\"]";
  private String filterChainDefinitions;

  @Resource
  private IPermissionService permissionService;

  public String getFilterChainDefinitions()
  {
    return this.filterChainDefinitions;
  }

  public void setFilterChainDefinitions(String filterChainDefinitions)
  {
    StringBuffer fiter = new StringBuffer();
    List list = this.permissionService.queryPermission(new Permission());
    for (Iterator it = list.iterator(); it.hasNext(); ) {
      Permission permission = (Permission)it.next();
      if (!StringUtils.isEmpty(permission.getUrl())) {
        fiter.append(permission.getUrl()).append(" = authc,").append(MessageFormat.format("perms[\"{0}\"]", new Object[] { permission.getCode() }) + "\n");
      }
    }

    this.filterChainDefinitions = (filterChainDefinitions + fiter.toString());
  }

  public Ini.Section getObject()
  {
    Ini ini = new Ini();
    ini.load(this.filterChainDefinitions);
    Ini.Section section = ini.getSection("");
    return section;
  }

  public Class<?> getObjectType() {
    return getClass();
  }

  public boolean isSingleton() {
    return false;
  }
}